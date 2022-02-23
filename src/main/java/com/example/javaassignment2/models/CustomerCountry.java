package com.example.javaassignment2.models;

public class CustomerCountry {

    private String country;
    private double customers;

    public CustomerCountry(String country, int customers) {
        this.country = country;
        this.customers = customers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

}
