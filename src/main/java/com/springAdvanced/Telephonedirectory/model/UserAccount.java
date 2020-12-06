package com.springAdvanced.Telephonedirectory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "user_accounts")
//mark as composite primary key
@IdClass(AccountId.class)
@Builder
@AllArgsConstructor
public class UserAccount implements Serializable {

    public UserAccount() {
    }
    //mark as primary key
    @Id
    @Column(name = "user_id")
    private Long userId;
    //mark as primary key
    @Id
    @Column(name = "company_id")
    private Long companyId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false, insertable = false, updatable = false)
    private Company company;

    @Column
    private BigDecimal amount;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}