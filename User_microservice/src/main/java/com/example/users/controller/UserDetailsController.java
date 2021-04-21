package com.example.users.controller;

import com.example.users.dto.GenericDto;
import com.example.users.entity.AddressDetails;
import com.example.users.entity.UserDetails;
import com.example.users.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/user/register")
    public GenericDto registerUser(@RequestBody UserDetails userDetails, HttpServletResponse responseStatus){
        return userDetailsService.registerUser(userDetails,responseStatus);
    }

    @PostMapping("/user/login/email")
    public GenericDto loginUserWithEmail(@RequestBody UserDetails userDetails, HttpServletResponse responseStatus){
        return userDetailsService.loginUserWithEmail(userDetails,responseStatus);
    }

    @PostMapping("/user/login/mobileNo")
    public GenericDto loginUserWithMobileNo(@RequestBody UserDetails userDetails, HttpServletResponse responseStatus){
        return userDetailsService.loginUserWithMobileNo(userDetails,responseStatus);
    }

    @GetMapping("/user/{userId}")
    public GenericDto getUserDetails(@PathVariable("userId") Long userId, HttpServletResponse responseStatus){
        return userDetailsService.getUserDetails(userId,responseStatus);
    }

    @PutMapping("/user/change-password/{userId}")
    public  GenericDto changePassword(@RequestBody String password, @PathVariable("userId") Long userId, HttpServletResponse response){
        return userDetailsService.changePassword(password,userId,response);
    }

    @GetMapping("/user/my-addresses/{userId}")
    public GenericDto getAllAddresses(@PathVariable("userId") Long userId, HttpServletResponse response){
        return userDetailsService.getAllAddresses(userId,response);
    }

    @GetMapping("/admin/get-all-users")
    public GenericDto getAllUsers(@RequestBody Long userId, HttpServletResponse response){
        return userDetailsService.getAllUsers(userId,response);
    }

    @GetMapping("/checkUserType/{userId")
    public GenericDto checkUserType(@PathVariable("userId") Long userId, HttpServletResponse response){
        return userDetailsService.checkUserType(userId,response);
    }

    @DeleteMapping("/admin/delete-user/{userId")
    public GenericDto deleteUser(@PathVariable("userId") Long userId, HttpServletResponse response){
        return userDetailsService.deleteUser(userId,response);
    }

}
