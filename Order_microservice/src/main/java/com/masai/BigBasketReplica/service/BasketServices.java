package com.masai.BigBasketReplica.service;

import com.masai.BigBasketReplica.Dto.GenericDto;
import com.masai.BigBasketReplica.Dto.ItemDto;
import com.masai.BigBasketReplica.entity.Basket;
import com.masai.BigBasketReplica.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class BasketServices {
    @Autowired
    BasketRepository basketRepository;

    public List<ItemDto> getBasketByUser(Integer userId, HttpServletResponse response) {
        // this will get all the item from basket
        List<Basket> basketList = basketRepository.findAllByUser(userId);
         // find all the details from item table by making a rest call to Item microservice
            //restFull call goes here
        // return the list of ItemDto;
        response.setStatus(200);
        return new ArrayList<ItemDto>();
    }

    public Basket addItemsToBasket(Integer userId,Integer itemId, Integer quantity, HttpServletResponse response)
    {
        Basket basket = new Basket(quantity,userId,itemId);
        response.setStatus(201);
        return basketRepository.save(basket);
    }


    public Basket updateQuantityOfItemInBasket(Integer userId,Integer itemId, Integer quantity,
                                               HttpServletResponse response)
    {
        Basket basket = basketRepository.findBasketByItemAndUser(userId,itemId);
        basket.setQuantity(basket.getQuantity()+quantity);
        response.setStatus(200);
        return basketRepository.save(basket);
    }


    public GenericDto deleteBasketByUser(Integer userId, HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        genericDto.setMessage("Basket deleted");
        basketRepository.deleteByUser(userId);
        response.setStatus(200);
        return genericDto;
    }
}
