package com.example.javaassignment2.models.interfaces;

import com.example.javaassignment2.models.CustomerCountry;


import java.util.List;

public interface CountryInterface {
    List<CustomerCountry> selectNumberOfCustomersPerCountry();
}
