package com.masai.BigBasketReplica.controller;

import com.masai.BigBasketReplica.Dto.AddressDTO;
import com.masai.BigBasketReplica.Dto.GenericDto;
import com.masai.BigBasketReplica.Dto.ItemDto;
import com.masai.BigBasketReplica.service.OrdersService;
import com.sun.tools.javac.jvm.Gen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class OrdersController {
    @Autowired
    OrdersService ordersService;
 // /orders/{userId}


    @GetMapping("/order/{userId}")
//    public List<AddressDTO> getAllAddress(@PathVariable("userId") Integer userId){
//        return ordersService.getAllAddress(userId);
//    }


    //Afsan-------
   @PostMapping("/order/{userId}/address/{addressId}")
   public GenericDto placeOrder(@PathVariable("userId") Integer userId,
                                   @PathVariable("addressId") Integer addressId, HttpServletResponse response){
        return ordersService.placeOrder(userId,addressId,response);
   }



   @DeleteMapping("/order/{orderId}/cancelorder")
   public GenericDto cancelOrder(@PathVariable("orderId") Integer orderId){
        return ordersService.cancelOrder(orderId);
   }

}
