package com.walmart.coding.challenge.model;

/**
 * model that represents a customer who is using this service
 * @author varsha
 */
public class Customer {
    private String email; //email id of the customer

    public Customer(String email){
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
