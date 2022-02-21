package com.example.javaassignment2.repositories;

import com.example.javaassignment2.models.CustomerCountry;
import com.example.javaassignment2.models.interfaces.CountryInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CountryRepository implements CountryInterface {
    String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    Connection conn = null;

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
}
