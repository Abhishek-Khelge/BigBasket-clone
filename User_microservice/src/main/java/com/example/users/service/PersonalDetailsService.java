package com.example.users.service;

import com.example.users.dto.GenericDto;
import com.example.users.entity.PersonalDetails;
import com.example.users.entity.UserDetails;
import com.example.users.repository.PersonalDetailsRepository;
import com.example.users.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class PersonalDetailsService {

    @Autowired
    PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public GenericDto addpersonalDetails(PersonalDetails personalDetails, Long userId, HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        if (userDetails.isEmpty()){
            response.setStatus(404);
            genericDto.setMessage("User Does Not Exists, Can't add Details");
            return genericDto;
        }
        PersonalDetails addedDetails = personalDetailsRepository.save(personalDetails);
        addedDetails.setUserDetails(userDetails.get());
        personalDetailsRepository.save(addedDetails);
        genericDto.setData(addedDetails);
        genericDto.setMessage("Personal details added");
        response.setStatus(200);
        return genericDto;
    }

    public GenericDto getPersonalDetails(Long userId, HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        if (userDetails.isEmpty()){
            genericDto.setMessage("User Does Not Exist, Can't fetch the details");
            response.setStatus(400);
            return genericDto;
        }
        Optional<PersonalDetails> personalDetails = personalDetailsRepository.findByUserDetails(userDetails.get());
        if (personalDetails.isEmpty()){
            genericDto.setMessage("Personal details not added, Add details to fetch");
            response.setStatus(404);
            return genericDto;
        }
        genericDto.setData(personalDetails.get());
        genericDto.setMessage("Fetched Personal Details Successfully");
        response.setStatus(200);
        return genericDto;
    }

    public GenericDto updateProfile(Long userId, PersonalDetails personalDetails, HttpServletResponse response) {
        GenericDto genericDto = new GenericDto();
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        if (userDetails.isEmpty()){
            genericDto.setMessage("User Does Not Exist, Can't edit personal details");
            response.setStatus(400);
            return genericDto;
        }
        Optional<PersonalDetails> personalDetailsByUserId = personalDetailsRepository.findByUserDetails(userDetails.get());
        if (personalDetailsByUserId.isEmpty()){
            PersonalDetails addedDetails = personalDetailsRepository.save(personalDetails);
            addedDetails.setUserDetails(userDetails.get());
            personalDetailsRepository.save(addedDetails);
            genericDto.setData(personalDetails);
            genericDto.setMessage("Personal details not added, Adding details");
            response.setStatus(201);
            return genericDto;
        }
        PersonalDetails personalDetailsByUserIdObj = personalDetailsByUserId.get();
        personalDetailsByUserIdObj.setFirstName(personalDetails.getFirstName());
        personalDetailsByUserIdObj.setLastName(personalDetails.getLastName());
        personalDetailsByUserIdObj.setSecondaryEmail(personalDetails.getSecondaryEmail());
        personalDetailsByUserIdObj.setDob(personalDetails.getDob());
        personalDetailsByUserIdObj.setLandlineNo(personalDetails.getLandlineNo());
        personalDetailsRepository.save(personalDetailsByUserIdObj);
        genericDto.setData(personalDetailsByUserIdObj);
        genericDto.setMessage("Profile updated");
        response.setStatus(200);
        return genericDto;
    }
}
