package com.springAdvanced.Telephonedirectory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table(name = "phone_numbers")
@Builder
@AllArgsConstructor
public class PhoneNumber {

    public PhoneNumber() {
    }

    //mark id as primary key

    @Id
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}