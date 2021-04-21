package com.example.users.service;

import com.example.users.dto.GenericDto;
import com.example.users.entity.AddressDetails;
import com.example.users.entity.UserDetails;
import com.example.users.repository.AddressDetailsRepository;
import com.example.users.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class AddressDetailsService {

    @Autowired
    AddressDetailsRepository addressDetailsRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public GenericDto addAddress(AddressDetails addressDetails, Long userId, HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        if (userDetails.isEmpty()){
            genericDto.setMessage("User Not Found to add Address");
            response.setStatus(404);
            return genericDto;
        }
        UserDetails userDetails1 = userDetails.get();
        AddressDetails addedAddressDetails = addressDetailsRepository.save(addressDetails);
        userDetails1.addAddress(addedAddressDetails);
        addedAddressDetails.setUserDetails(userDetails1);
        addressDetailsRepository.save(addedAddressDetails);
        genericDto.setMessage("Address Details added");
        genericDto.setData(userDetails1);
        response.setStatus(200);
        return genericDto;
    }
}
