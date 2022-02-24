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

    /**
     * REST endpoint returning all customers from db
     * @return {@link List} of customer
     */
    @GetMapping("customer")
    public List<Customer> selectAllCustomers() {
        return customerInterface.selectAllCustomers();
    }

    /**
     * REST endpoint returning a list containing specified number of customer from specific start index
     * @param limit int - number of customer that will be returned
     * @param offset int - start index
     * @return {@link List} of customers
     */
    @GetMapping("/customer/limitAndOffset")
    public List<Customer> selectAllCustomersLimitAndOffset(@RequestParam int limit, @RequestParam int offset) {
        return customerInterface.selectAllCustomersLimitAndOffset(limit, offset);
    }

    /**
     * REST endpoint returning all customers from db order by the highest spender descending
     * @return {@link List} of {@link CustomerSpender}
     */
    @GetMapping("customer/order/highestSpender")
    public List<CustomerSpender> selectAllCustomersOrderByHighestSpender() {
        return customerInterface.selectAllCustomersOrderByHighestSpender();
    }

    /**
     * REST endpoint returning one customer by id
     * @param customerId - customer id - int
     * @return {@link Customer}
     */
    @GetMapping("customer/{customerId}")
    public Customer selectCustomerById(@PathVariable int customerId) {
        return customerInterface.selectCustomerById(customerId);
    }
    /**
     * REST endpoint returning one customer by first name and last name
     * @param firstName - String value representing the customers first name
     * @param lastName - String value representing the customers last name
     * @return {@link Customer}
     */
    @GetMapping("customer/name")
    public Customer selectCustomerByName(@RequestParam String firstName, @RequestParam String lastName) {
        return customerInterface.selectCustomerByName(firstName, lastName);
    }
    /**
     * REST endpoint for adding a new customer to the db
     * @param newCustomer {@link Customer} to be inserted in to the db
     * @return the added customer or null if error occur
     */
    @PostMapping("customer/add")
    public Customer addNewCustomer(@RequestBody Customer newCustomer) {
        return customerInterface.addNewCustomer(newCustomer);
    }
    /**
     * REST endpoint for updating all fields in an existing user
     * @param updatedCustomer {@link Customer} containing the new information
     * @return the updated user
     */
    @PostMapping("/customer/update/{customerId}")
    public Customer updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable int customerId) {
        updatedCustomer.setCustomerID(customerId);
        return customerInterface.updateCustomer(updatedCustomer);
    }

    /**
     * REST endpoint for returning a customers most popular genre
     * @param customerId - int
     * @return {@link CustomerGenre}
     */
    @GetMapping("/customer/{customerId}/popular/genre")
    public CustomerGenre getMostPopularGenreForCustomer(@PathVariable int customerId) {
        return customerInterface.getMostPopularGenreForCustomer(customerId);
    }
    /**
     * REST endpoint that returns a list {@link CustomerCountry} containing information with how many customers each country has
     * @return {@link List} of customerCountry
     */
    @GetMapping("customer/totalPerCountry")
    public List<CustomerCountry> selectNumberOfCustomersPerCountry() {
        return customerInterface.selectNumberOfCustomersPerCountry();
    }
}