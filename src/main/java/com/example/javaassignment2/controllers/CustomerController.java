package com.example.javaassignment2.controllers;

import com.example.javaassignment2.models.Customer;
import com.example.javaassignment2.models.CustomerCountry;
import com.example.javaassignment2.models.CustomerGenre;
import com.example.javaassignment2.models.CustomerSpender;
import com.example.javaassignment2.models.interfaces.CustomerInterface;
import com.example.javaassignment2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CustomerController {
    @Autowired
    CustomerInterface customerInterface;

    @GetMapping("customer")
    public List<Customer> selectAllCustomers() {
        return customerInterface.selectAllCustomers();
    }

    @GetMapping("/customer/limitAndOffset")
    public List<Customer> selectAllCustomersLimitAndOffset(@RequestParam int limit, @RequestParam int offset) {
        return customerInterface.selectAllCustomersLimitAndOffset(limit, offset);
    }

    //
    @GetMapping("customer/order/highestSpender")
    public List<CustomerSpender> selectAllCustomersOrderByHighestSpender() {
        return customerInterface.selectAllCustomersOrderByHighestSpender();
    }

    //
    @GetMapping("customer/{customerId}")
    public Customer selectCustomerById(@PathVariable int customerId) {
        return customerInterface.selectCustomerById(customerId);
    }

    @GetMapping("customer/name")
    public Customer selectCustomerByName(@RequestParam String firstName, @RequestParam String lastName) {


        System.out.println(firstName + " " + lastName);
        return customerInterface.selectCustomerByName(firstName, lastName);
    }

    @PostMapping("customer/add")
    public Customer addNewCustomer(@RequestBody Customer newCustomer) {
        return customerInterface.addNewCustomer(newCustomer);
    }

    @PostMapping("/customer/update/{customerId}")
    public Customer updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable int customerId) {
        updatedCustomer.setCustomerID(customerId);
        return customerInterface.updateCustomer(updatedCustomer);
    }

    @GetMapping("/customer/{customerId}/popular/genre")
    public CustomerGenre getMostPopularGenreForCustomer(@PathVariable int customerId) {
        return customerInterface.getMostPopularGenreForCustomer(customerId);
    }

    @GetMapping("customer/totalPerCountry")
    public List<CustomerCountry> selectNumberOfCustomersPerCountry() {
        return customerInterface.selectNumberOfCustomersPerCountry();
    }
}