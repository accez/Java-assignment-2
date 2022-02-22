package com.example.javaassignment2.repositories;

import com.example.javaassignment2.models.Customer;
import com.example.javaassignment2.models.CustomerCountry;
import com.example.javaassignment2.models.CustomerGenre;
import com.example.javaassignment2.models.CustomerSpender;
import com.example.javaassignment2.models.interfaces.CustomerInterface;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerRepository implements CustomerInterface {
    String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    Connection conn = null;

    @Override
    public List<Customer> selectAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
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
                                resultSet.getInt("CustomerId"),
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
    public List<CustomerCountry> selectNumberOfCustomersPerCountry() {
        ArrayList<CustomerCountry> customerCountry = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Country, count() as total FROM Customer GROUP BY Country ORDER BY total DESC");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customerCountry.add(new CustomerCountry(
                        resultSet.getString("Country"),
                        resultSet.getInt("total")
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
        return customerCountry;
    }

    @Override
    public List<CustomerSpender> selectAllCustomersOrderByHighestSpender() {
        List<CustomerSpender> customerSpenders = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Customer.CustomerId, FirstName, LastName, Country, SUM(I.Total) as total\n" +
                    "from Customer\n" +
                    "         inner join Invoice I on Customer.CustomerId = I.CustomerId\n" +
                    "group by I.CustomerId\n" +
                    "order by total DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerSpender customerSpender = new CustomerSpender();
                customerSpender.setId(resultSet.getInt("CustomerId"));
                customerSpender.setFirstName(resultSet.getString("FirstName"));
                customerSpender.setLastName(resultSet.getString("LastName"));
                customerSpender.setCountry(resultSet.getString("Country"));
                customerSpender.setTotalSpending(resultSet.getDouble("total"));
                customerSpenders.add(customerSpender);
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
    public Customer selectCustomerById(int id) {
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId LIKE ?");

            preparedStatement.setInt(1, id);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("CustomerId"),
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
                        resultSet.getInt("CustomerId"),
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

            preparedStatement.executeUpdate();
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
        //Varför får vi ingt object tillbaka???
        System.out.println(newCustomer);
        return newCustomer;
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Customer SET FirstName = ?,LastName = ?,Country = ?, PostalCode = ?, Phone = ?, Email = ? WHERE CustomerId like ?");
            setQueryParams(updatedCustomer, preparedStatement);
            preparedStatement.setInt(7, updatedCustomer.getCustomerID());

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
    public CustomerGenre getMostPopularGenreForCustomer(int id) {

        CustomerGenre customerGenre = null;
        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = conn.prepareStatement("""
                        SELECT genreName, firstName, lastName, customerId
                                                                from (
                                                                         SELECT count(*) as total, G.Name as genreName,  Customer.FirstName as firstName, Customer.LastName as lastName, Customer.CustomerId as customerId
                                                                        FROM Customer
                                                                                 inner join Invoice I on Customer.CustomerId = I.CustomerId
                                                                                inner join InvoiceLine IL on I.InvoiceId = IL.InvoiceId
                                                                                  inner join Track T on T.TrackId = IL.TrackId
                                                                                 inner join Genre G on G.GenreId = T.GenreId
                                                                        where I.CustomerId like ?
                                                                         GROUP BY G.Name
                                                                     )
                                                                WHERE total = (SELECT MAX(total)
                                                                               from (SELECT count(*) as total
                                                                                     FROM Customer
                                                                                              inner join Invoice I on Customer.CustomerId = I.CustomerId
                                                                                              inner join InvoiceLine IL on I.InvoiceId = IL.InvoiceId
                                                                                              inner join Track T on T.TrackId = IL.TrackId
                                                                                              inner join Genre G on G.GenreId = T.GenreId
                                                                                     where I.CustomerId like ?
                                                                                     GROUP BY G.Name))""");

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            customerGenre = new CustomerGenre();

            while (resultSet.next()) {

                customerGenre.setId(resultSet.getInt("customerId"));
                customerGenre.setFirstName(resultSet.getString("firstName"));
                customerGenre.setLastName(resultSet.getString("lastName"));
                customerGenre.addGenres(resultSet.getString("genreName"));
            }


            System.out.println(customerGenre);
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex);
        } finally {
            try {
                // Close Connection
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex);
            }
        }
        System.out.println( "rutnr " + customerGenre);
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
