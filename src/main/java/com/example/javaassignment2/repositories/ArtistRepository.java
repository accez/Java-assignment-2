package com.example.javaassignment2.repositories;

import com.example.javaassignment2.models.Artist;
import com.example.javaassignment2.models.interfaces.ArtistInterface;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistRepository implements ArtistInterface {

    String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    Connection conn = null;
    @Override
    public List<Artist> selectRandom() {
        ArrayList<Artist> artists = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select * from Artist order by random() limit 5;");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                artists.add(
                        new Artist(resultSet.getInt("ArtistId"),
                                resultSet.getString("Name")
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
        return artists;
    }
}
