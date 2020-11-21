package com.springAdvanced.Telephonedirectory.model;

import javax.persistence.*;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table(name = "phone_companies")
public class Company {
    //mark id as primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//defining id as column name
    @Column
    private int id;
    //defining name as column name
    @Column
    private String name;
    @Column
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}