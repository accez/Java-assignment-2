package com.example.javaassignment2.models.interfaces;


import com.example.javaassignment2.models.Customer;
import com.example.javaassignment2.models.CustomerCountry;
import com.example.javaassignment2.models.CustomerGenre;
import com.example.javaassignment2.models.CustomerSpender;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerInterface {

    List<Customer> selectAllCustomers();
    List<Customer> selectAllCustomersLimitAndOffset(int limit, int offset);
    List<CustomerCountry> selectNumberOfCustomersPerCountry();

    List<CustomerSpender>selectAllCustomersOrderByHighestSpender();

    Customer selectCustomerById(int id);
    Customer selectCustomerByName(String firstName, String lastName);
    Customer addNewCustomer(Customer newCustomer);
    Customer updateCustomer(Customer updatedCustomer);

    CustomerGenre getMostPopularGenreForCustomer(int id);

}
