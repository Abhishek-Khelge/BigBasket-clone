package com.example.users.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class AddressDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 8)
    private Long addressId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String addressType;
    private String houseName;
    private String apartmentName;
    private String streetDetail;
    private String landmark;
    private String pincode;
    private String areaDetail;
    private String city;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private UserDetails userDetails;

    public AddressDetails() {
    }

    public AddressDetails(Long addressId, String firstName, String lastName, String mobile, String addressType, String houseName, String apartmentName, String streetDetail, String landmark, String pincode, String areaDetail, String city) {
        this.addressId = addressId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.addressType = addressType;
        this.houseName = houseName;
        this.apartmentName = apartmentName;
        this.streetDetail = streetDetail;
        this.landmark = landmark;
        this.pincode = pincode;
        this.areaDetail = areaDetail;
        this.city = city;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getStreetDetail() {
        return streetDetail;
    }

    public void setStreetDetail(String streetDetail) {
        this.streetDetail = streetDetail;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAreaDetail() {
        return areaDetail;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
