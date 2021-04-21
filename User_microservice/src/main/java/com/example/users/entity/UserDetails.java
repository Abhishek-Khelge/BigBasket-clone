package com.example.users.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 8)
    private Long userId;

    @Column(length = 10, nullable = false)
    private String mobileNo;

    @Column(length = 40, nullable = false)
    private String emailId;

    @Column(length = 5, nullable = false)
    private String userType;

    @Column(length = 50, nullable = false)
    private String password;

    @OneToMany(mappedBy = "userDetails")
    @JsonManagedReference
    private List<AddressDetails> addressDetailsList = new ArrayList<>();

    public UserDetails() {
    }

    public UserDetails(Long userId, String mobileNo, String emailId, String userType, String password) {
        this.userId = userId;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.userType = userType;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressDetails> getAddressDetailsList() {
        return addressDetailsList;
    }

    public void addAddress(AddressDetails address){
        this.addressDetailsList.add(address);
    }

    public void removeAddress(AddressDetails address){
        this.addressDetailsList.remove(address);
    }

}
