package com.example.kish.gendir.model;

import java.util.Date;

/**
 * @author rost.
 */

public class Payment {

    private String Id;

    private String name;

    private String operationType;


    private Date date;


    private Customer customer;


    private Bank bank;


    private ContrAgent contrAgent;

    private Float summ;

    private String status;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public ContrAgent getContrAgent() {
        return contrAgent;
    }

    public void setContrAgent(ContrAgent contrAgent) {
        this.contrAgent = contrAgent;
    }

    public Float getSumm() {
        return summ;
    }

    public void setSumm(Float summ) {
        this.summ = summ;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
