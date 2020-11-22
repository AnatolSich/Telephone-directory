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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//defining id as column name
    @Column
    private int id;
    //defining name as column name
    @Column
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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