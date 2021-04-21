package com.example.users.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId"})})
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persDetails_seq")
    @SequenceGenerator(name = "persDetails_seq", sequenceName = "persDetails_seq", allocationSize = 8)
    private Long persDetailsId;
    private String firstName;
    private String lastName;
    private String secondaryEmail;
    private String dob;
    private String landlineNo;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    UserDetails userDetails;

    public PersonalDetails() {
    }

    public PersonalDetails(Long persDetailsId, String firstName, String lastName, String secondaryEmail, String dob, String landlineNo) {
        this.persDetailsId = persDetailsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondaryEmail = secondaryEmail;
        this.dob = dob;
        this.landlineNo = landlineNo;
    }

    public Long getPersDetailsId() {
        return persDetailsId;
    }

    public void setPersDetailsId(Long persDetailsId) {
        this.persDetailsId = persDetailsId;
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

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLandlineNo() {
        return landlineNo;
    }

    public void setLandlineNo(String landlineNo) {
        this.landlineNo = landlineNo;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
