package com.example.javaassignment2.models.interfaces;


import com.example.javaassignment2.models.Customer;
import com.example.javaassignment2.models.CustomerGenre;
import com.example.javaassignment2.models.CustomerSpender;

import java.util.List;

public interface CustomerInterface {

    List<Customer> selectAllCustomers();
    List<Customer> selectAllCustomersLimitAndOffset(int limit, int offset);

    List<CustomerSpender>selectAllCustomersOrderByHighestSpender();

    Customer selectCustomerById(String id);
    Customer selectCustomerByName(String firstName, String lastName);
    Customer addNewCustomer(Customer newCustomer);
    Customer updateCustomer(Customer updatedCustomer);

    CustomerGenre getMostPopularGenreForCustomer(String id);

}
