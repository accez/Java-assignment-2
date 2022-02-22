package com.example.javaassignment2.controllers;

import com.example.javaassignment2.models.Customer;
import com.example.javaassignment2.models.CustomerGenre;
import com.example.javaassignment2.models.CustomerSpender;
import com.example.javaassignment2.models.interfaces.CustomerInterface;
import com.example.javaassignment2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerInterface customerInterface;

    @GetMapping("/customer")
    public List<Customer> selectAllCustomers(){
        return customerInterface.selectAllCustomers();
    }
//    @GetMapping("/customer/")
//    public List<Customer> selectAllCustomersLimitAndOffset(int limit, int offset){
//        return
//    }
//
//    public List<CustomerSpender>selectAllCustomersOrderByHighestSpender(){
//        return
//    }
//
//    public Customer selectCustomerById(String id){
//        return
//    }
//    public Customer selectCustomerByName(String firstName, String lastName){
//        return
//    }
//    public Customer addNewCustomer(Customer newCustomer){
//        return
//    }
//    public Customer updateCustomer(Customer updatedCustomer){
//        return
//    }
//
//    public CustomerGenre getMostPopularGenreForCustomer(Customer customer){
//        return
//    }



}
