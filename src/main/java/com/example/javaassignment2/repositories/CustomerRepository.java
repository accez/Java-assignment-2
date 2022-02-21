package com.example.javaassignment2.repositories;

import com.example.javaassignment2.models.Customer;
import com.example.javaassignment2.models.CustomerGenre;
import com.example.javaassignment2.models.CustomerSpender;
import com.example.javaassignment2.models.interfaces.CustomerInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements CustomerInterface {
    String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    Connection conn = null;

    @Override
    public List<Customer> selectAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country, PostalCode, Phone, Email FROM Customer");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")

                        ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // Close Connection
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return customers;
    }

    @Override
    public List<Customer> selectAllCustomersLimitAndOffset(int limit, int offset) {
        return new ArrayList<>(selectAllCustomers().subList(offset, offset + limit));
    }

    @Override
    public List<CustomerSpender> selectAllCustomersOrderByHighestSpender() {
        List<CustomerSpender> customerSpenders = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Customer.CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email, SUM(I.Total) as total\n" +
                    "from Customer\n" +
                    "         inner join Invoice I on Customer.CustomerId = I.CustomerId\n" +
                    "group by I.CustomerId\n" +
                    "order by total DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerSpenders.add(new CustomerSpender(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        resultSet.getDouble("total")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // Close Connection
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return customerSpenders;
    }

    @Override
    public Customer selectCustomerById(String id) {
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId LIKE ?");

            preparedStatement.setString(1, id);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")

                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // Close Connection
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return customer;
    }

    @Override
    public Customer selectCustomerByName(String firstName, String lastName) {
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country, PostalCode, Phone, Email FROM Customer WHERE (FirstName = ? AND LastName = ?)");

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")

                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // Close Connection
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return customer;
    }

    @Override
    public Customer addNewCustomer(Customer newCustomer) {
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Customer(FirstName,LastName,Country, PostalCode, Phone, Email) VALUES(?, ?, ?, ?,?,?)");
            setQueryParams(newCustomer, preparedStatement);

            int rowsEffected = preparedStatement.executeUpdate();

            System.out.println(rowsEffected);
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex);
        } finally {
            try {
                // Close Connection
                conn.close();
                return null;
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex);
            }
        }
        return newCustomer;
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Customer SET FirstName = ?,LastName = ?,Country = ?, PostalCode = ?, Phone = ?, Email = ? WHERE CustomerId like ?");
            setQueryParams(updatedCustomer, preparedStatement);
            preparedStatement.setString(7, updatedCustomer.getCustomerID());

            int rowsEffected = preparedStatement.executeUpdate();
            System.out.println(rowsEffected);
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex);
        } finally {
            try {
                // Close Connection
                conn.close();
                return null;
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex);
            }
        }
        return updatedCustomer;
    }

    @Override
    public CustomerGenre getMostPopularGenreForCustomer(Customer customer) {

        CustomerGenre customerGenre = new CustomerGenre(customer);
        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT testname \n" +
                    "from (\n" +
                    "         SELECT count(*) as total, G.Name as testname, Customer.*\n" +
                    "         FROM Customer\n" +
                    "                  inner join Invoice I on Customer.CustomerId = I.CustomerId\n" +
                    "                  inner join InvoiceLine IL on I.InvoiceId = IL.InvoiceId\n" +
                    "                  inner join Track T on T.TrackId = IL.TrackId\n" +
                    "                  inner join Genre G on G.GenreId = T.GenreId\n" +
                    "         where I.CustomerId like ? \n" +
                    "         GROUP BY G.Name\n" +
                    "     )\n" +
                    "WHERE total = (SELECT min(total)\n" +
                    "               from (SELECT count(*) as total\n" +
                    "                     FROM Customer\n" +
                    "                              inner join Invoice I on Customer.CustomerId = I.CustomerId\n" +
                    "                              inner join InvoiceLine IL on I.InvoiceId = IL.InvoiceId\n" +
                    "                              inner join Track T on T.TrackId = IL.TrackId\n" +
                    "                              inner join Genre G on G.GenreId = T.GenreId\n" +
                    "                     where I.CustomerId like ?\n" +
                    "                     GROUP BY G.Name));");

            preparedStatement.setString(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getCustomerID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerGenre.addGenres(resultSet.getString("testname"));
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex);
        } finally {
            try {
                // Close Connection
                conn.close();
                return null;
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex);
            }
        }
        return customerGenre;
    }

    private void setQueryParams(Customer updatedCustomer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, updatedCustomer.getFirstName());
        preparedStatement.setString(2, updatedCustomer.getLastName());
        preparedStatement.setString(3, updatedCustomer.getCountry());
        preparedStatement.setString(4, updatedCustomer.getPostalCode());
        preparedStatement.setString(5, updatedCustomer.getPhone());
        preparedStatement.setString(6, updatedCustomer.getEmail());
    }
}
