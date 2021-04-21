package com.example.users.controller;

import com.example.users.dto.GenericDto;
import com.example.users.entity.PersonalDetails;
import com.example.users.service.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PersonalDetailsController {

    @Autowired
    PersonalDetailsService personalDetailsService;

    @PostMapping("/user/register/personal-details/{userId}")
    public GenericDto addpersonalDetails(@RequestBody PersonalDetails personalDetails, @PathVariable("userId") Long userId, HttpServletResponse response){
        GenericDto genericDto = personalDetailsService.addpersonalDetails(personalDetails,userId,response);
        return genericDto;
    }

    @GetMapping("/user/{userId}/personal-details")
    public GenericDto getPersonalDetails(@PathVariable("userId") Long userId, HttpServletResponse response){
        GenericDto genericDto = personalDetailsService.getPersonalDetails(userId,response);
        return genericDto;
    }

    @PutMapping("/user/profile/{userId}/edit")
    public GenericDto updateProfile(@PathVariable("userId") Long userId, @RequestBody PersonalDetails personalDetails, HttpServletResponse response){
        GenericDto genericDto = personalDetailsService.updateProfile(userId,personalDetails,response);
        return genericDto;
    }
}
