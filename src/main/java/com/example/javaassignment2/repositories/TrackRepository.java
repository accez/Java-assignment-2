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

    /**
     * Returns 5 random tracks from db
     * @return {@link List}
     */
    @Override
    public List<Track> selectRandom() {
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM Track ORDER BY random() LIMIT 5;");
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
    /**
     * Returns the name, genre, album and the artist for a specific track
     * @return {@link List} containing any direct or partial matches
     */
    @Override
    public List<Track> getTrackInformation(String trackName) {
        List<Track> tracks = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                                SELECT TrackId,Track.Name as TrackName, Genre.Name as GenreName, Album.Title as Album, Artist.Name as ArtistName FROM Track
                                    inner join Genre on Genre.GenreId = Track.GenreId
                                    inner join Album on Album.AlbumId = Track.AlbumId
                                    inner join Artist on Artist.ArtistId = Album.ArtistId WHERE Track.Name LIKE ?""");

            preparedStatement.setString(1, trackName + "%");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results

            while (resultSet.next()) {
                tracks.add(new Track(
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
        return tracks;
    }


}
