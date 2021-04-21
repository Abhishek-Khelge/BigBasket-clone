package com.example.users.service;

import com.example.users.dto.GenericDto;
import com.example.users.entity.AddressDetails;
import com.example.users.entity.UserDetails;
import com.example.users.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;


    public GenericDto registerUser(UserDetails userDetails, HttpServletResponse responseStatus) {
        Optional<UserDetails> user = userDetailsRepository.findByEmailIdOrMobileNo(userDetails.getEmailId(), userDetails.getMobileNo());
        GenericDto genericDto = new GenericDto();
        if (user.isPresent()){
            userDetailsRepository.save(userDetails);
            genericDto.setData(null);
            genericDto.setMessage("User Already Exists");
            responseStatus.setStatus(409);
            return genericDto;
        }
        else {
            userDetailsRepository.save(userDetails);
            genericDto.setData(userDetails);
            genericDto.setMessage("Registered");
            responseStatus.setStatus(201);
            return genericDto;
        }
    }

    public GenericDto loginUserWithEmail(UserDetails userDetails, HttpServletResponse responseStatus){
        GenericDto genericDto = new GenericDto();
        if (userDetails.getEmailId() == null){
            responseStatus.setStatus(400);
            genericDto.setMessage("Please enter Email to login");
            return genericDto;
        }
        Optional<UserDetails> user = userDetailsRepository.findByEmailId(userDetails.getEmailId());
        if (user.isPresent()){
            if(user.get().getPassword().equals(userDetails.getPassword())){
                responseStatus.setStatus(200);
                genericDto.setMessage("Succesfully logged in");
                return genericDto;
            }
            else {
                responseStatus.setStatus(403);
                genericDto.setMessage("Password Incorrect");
                return genericDto;
            }
        }
        else {
            responseStatus.setStatus(404);
            genericDto.setMessage("User Does not exists, Register to login");
            return genericDto;
        }
    }
    public GenericDto loginUserWithMobileNo(UserDetails userDetails, HttpServletResponse responseStatus) {
        GenericDto genericDto = new GenericDto();
        if (userDetails.getMobileNo() == null) {
            responseStatus.setStatus(400);
            genericDto.setMessage("Please enter Mobile Number to login");
            return genericDto;
        }
        Optional<UserDetails> user = userDetailsRepository.findByMobileNo(userDetails.getMobileNo());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(userDetails.getPassword())) {
                responseStatus.setStatus(200);
                genericDto.setMessage("Succesfully logged in");
                return genericDto;
            } else {
                responseStatus.setStatus(403);
                genericDto.setMessage("Password Incorrect");
                return genericDto;
            }
        }
        else {
            responseStatus.setStatus(404);
            genericDto.setMessage("User Does not exists, Register to login");
            return genericDto;
        }
    }

    public GenericDto getUserDetails(Long userId, HttpServletResponse responseStatus){
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        GenericDto genericDto = new GenericDto();
        if (userDetails.isPresent()){
            responseStatus.setStatus(200);
            genericDto.setData(userDetails);
            genericDto.setMessage("User Found");
            return genericDto;
        }
        else {
            responseStatus.setStatus(404);
            genericDto.setData(false);
            genericDto.setMessage("User Not Found");
            return genericDto;
        }
    }

    public GenericDto changePassword(String password, Long userId,HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        if (userDetails.isEmpty()){
            genericDto.setMessage("User Not Found");
            response.setStatus(404);
            return genericDto;
        }
        UserDetails userDetails1 = userDetails.get();
        userDetails1.setPassword(password);
        userDetailsRepository.save(userDetails1);
        response.setStatus(200);
        genericDto.setMessage("Passwod changed successfully");
        return genericDto;
    }

    public GenericDto getAllAddresses(Long userId,HttpServletResponse response) {
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        GenericDto genericDto = new GenericDto();
        if (userDetails.isEmpty()){
            genericDto.setMessage("User Does Not Exists");
            response.setStatus(404);
            return genericDto;
        }
        List<AddressDetails> addressDetailsList = userDetails.get().getAddressDetailsList();
        response.setStatus(200);
        genericDto.setMessage("Fetched Address Details Successfully");
        genericDto.setData(addressDetailsList);
        return genericDto;
    }

    public GenericDto getAllUsers(Long userId,HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        GenericDto genericDto1 = new GenericDto();
        genericDto = checkUserType(userId,response);
        if (genericDto.getData().equals("ADMIN")) {
            List<UserDetails> userDetailsList = userDetailsRepository.findAll();
            genericDto1.setData(userDetailsList);
            genericDto1.setMessage("Fetched all the users data successfully");
            response.setStatus(200);
            return genericDto1;
        }
        response.setStatus(403);
        genericDto1.setMessage("User dont have access to view other user details");
        return genericDto1;
    }


    public GenericDto checkUserType(Long userId, HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        if (userDetails.isEmpty()){
            response.setStatus(404);
            genericDto.setMessage("User Not found");
            return genericDto;
        }
        genericDto.setData(userDetails.get().getUserType());
        return genericDto;
    }

    public GenericDto deleteUser(Long userId, HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        GenericDto genericDto1 = new GenericDto();
        genericDto = checkUserType(userId,response);
        if (genericDto.getData().equals("ADMIN")) {
            Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
            if (userDetails.isEmpty()){
                response.setStatus(404);
                genericDto1.setMessage("User Not found");
                return genericDto1;
            }
            userDetailsRepository.delete(userDetails.get());
            response.setStatus(200);
            genericDto1.setMessage("User deleted");
            return genericDto1;
        }
        response.setStatus(403);
        genericDto1.setMessage("User dont have access to delete other User");
        return genericDto1;
    }
}
