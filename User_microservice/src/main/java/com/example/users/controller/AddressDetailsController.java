package com.example.users.controller;

import com.example.users.dto.GenericDto;
import com.example.users.entity.AddressDetails;
import com.example.users.service.AddressDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AddressDetailsController {

    @Autowired
    AddressDetailsService addressDetailsService;

    @PostMapping("/user/{userId}/add-address")
    public GenericDto addAddress(@RequestBody AddressDetails addressDetails, @PathVariable("userId") Long userId, HttpServletResponse response){
        GenericDto genericDto = addressDetailsService.addAddress(addressDetails,userId,response);
        return genericDto;
    }

}
