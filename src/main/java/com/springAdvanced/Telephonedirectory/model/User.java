package com.springAdvanced.Telephonedirectory.model;

import javax.persistence.*;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table(name = "users")
public class User {
    //mark id as primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//defining id as column name
    @Column
    private int id;
    //defining name as column name
    @Column
    private String first_name;
    @Column
    private String last_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}