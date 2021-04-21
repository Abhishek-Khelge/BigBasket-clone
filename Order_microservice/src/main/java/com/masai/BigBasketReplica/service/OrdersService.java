package com.masai.BigBasketReplica.service;

import com.masai.BigBasketReplica.Dto.GenericDto;
import com.masai.BigBasketReplica.Dto.ItemDto;

import com.masai.BigBasketReplica.entity.Orders;
import com.masai.BigBasketReplica.entity.OrdersDetails;
import com.masai.BigBasketReplica.repository.OrderDetailsRepository;
import com.masai.BigBasketReplica.repository.OrderRepository;
import org.apache.catalina.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BasketServices basketServices;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    RestTemplate restTemplate;

//    public List<AddressDTO> getAllAddress(Integer userId) {
//        ResponseEntity<AddressDTO> responseEntity=restTemplate.getForEntity("http://localhost:8090/user",
//                AddressDTO);
//        return null;
//    }
    //        order id created
//        in the param, we will sent the selected address by user
//        move all the items from the basket to orderDetails table and connect to order id  return order completed and payment done.
    public GenericDto placeOrder(Integer userId, Integer addressId, HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        genericDto.setMessage("order placed ");
        List<ItemDto> itemDtoList =  basketServices.getBasketByUser(userId,response);
        Double totalPrice = 0.0d;
        for (ItemDto itemDto : itemDtoList){
            totalPrice += itemDto.getTotalPrice();
        }

        Orders order = new Orders();
        order.setUserId(userId);
        order.setPlacedDate(LocalDate.now());
        order.setPlacedTime(LocalTime.now());
        //2019-01-21 20:44:06.567    12:20:37.468441
        order.setDeliveryAddress(addressId);
        order.setTotalPrice(totalPrice);
        order.setDeliveredStatus("order processed");
        order.setModeOfPay("Cash on delivery");
        order.setPaymentStatus("yet to be paid");

        Orders savedOrder = orderRepository.save(order);
        for(ItemDto itemDto : itemDtoList){
            OrdersDetails ordersDetails = new OrdersDetails();
            ordersDetails.setOrders(savedOrder);
            ordersDetails.setTotalPrice(itemDto.getTotalPrice());
            ordersDetails.setItemName(itemDto.getItemName());
            ordersDetails.setItemId(itemDto.getItemId());
            ordersDetails.setItemQty(itemDto.getItemQty());
            ordersDetails.setUnitPrice(itemDto.getUnitPrice());
            orderDetailsRepository.save(ordersDetails);
            savedOrder.setOrderDetailList(ordersDetails);
        }
        basketServices.deleteBasketByUser(userId,response);

        return genericDto;
    }


    public GenericDto cancelOrder(Integer orderId) {
        GenericDto genericDto = new GenericDto();
        orderRepository.deleteById(orderId);
        genericDto.setMessage("order deleted");
        orderDetailsRepository.deleteById(orderId);
        return genericDto;

    }
}
