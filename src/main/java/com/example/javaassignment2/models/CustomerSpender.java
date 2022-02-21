package com.example.javaassignment2.models;

public class CustomerSpender extends Customer {
    private double totalSpending;

    public CustomerSpender(String customerID, String firstName, String lastName, String country, String postalCode, String phone, String email, double totalSpending) {
        super(customerID, firstName, lastName, country, postalCode, phone, email);
        this.totalSpending = totalSpending;
    }

    @Override
    public String toString() {
        return "CustomerSpender{" +
                "totalSpending=" + totalSpending +
                '}';
    }
}
