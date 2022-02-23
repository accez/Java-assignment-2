package com.example.javaassignment2.repositories;

import com.example.javaassignment2.models.Customer;
import com.example.javaassignment2.models.Genre;
import com.example.javaassignment2.models.Track;
import com.example.javaassignment2.models.interfaces.GenreInterface;
import com.example.javaassignment2.models.interfaces.TrackInterface;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackRepository implements TrackInterface {

    String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    Connection conn = null;
    @Override
    public List<Track> selectRandom() {
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select * from Track order by random() limit 5;");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                tracks.add(
                        new Track(resultSet.getInt("TrackId"),
                                resultSet.getString("Name"),
                                resultSet.getString("Composer")
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
        return tracks;
    }

    @Override
    public List<Track> getTrackInformation(String trackName) {
        List <Track> track = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                                                 select TrackId,Track.Name as TrackName, G.Name as GenreName, A.Title as Album, A2.Name as ArtistName from Track
                                                 inner join Genre G on G.GenreId = Track.GenreId
                                             inner join Album A on A.AlbumId = Track.AlbumId
                                             inner join Artist A2 on A2.ArtistId = A.ArtistId WHERE Track.Name LIKE ?""");

            preparedStatement.setString(1, trackName + "%");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results

            while (resultSet.next()) {
                track.add(new Track(
                        resultSet.getInt("TrackId"),
                        resultSet.getString("TrackName"),
                        resultSet.getString("ArtistName"),
                        resultSet.getString("Album"),
                        resultSet.getString("GenreName")
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
        return track;
    }


}
