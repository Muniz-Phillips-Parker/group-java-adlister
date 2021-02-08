package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    //********************************************************************
//    ***********************  FIND ALL ADS    *************************
//***********************************************************************
    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    //*******************************************************************
//    *******************  FIND ADDS FOR 1 USER    ***********************
//***********************************************************************

    @Override
    public List<Ad> allForUser(User user) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE user_id = ?");
            stmt.setLong(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }
    ///////////////////////////////////////////
//    Created by the MySQLAdsDao from 61 to 71 - from Christian
    ///////////////////////////////////////////
    @Override
    public void deleteAd(Ad ad) {
        PreparedStatement stmt;
//        try {
//            stmt = connection.prepareStatement("DELETE FROM ads WHERE id = ?");
//            stmt.setLong(1, ad.getId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Error deleting ad.", e);
//        }
    }
    ///////////////////////////////////////////
//    Created by the MySQLAdsDao from 61 to 71
    /////////////////////////////////////////

    public Long delete(Long adId) {
        try {
            String deleteQuery = "DELETE FROM ads WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setLong(1, adId);
            ps.executeUpdate();
            return Long.valueOf(2);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad", e);
        }
    }


    @Override
    public Ad getAdById(long id) {
        Ad ad = null;
        try {
            //"AND is_Deleted=0" how to check if it is deleted on 101
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ADS WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ad = new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting Ad id", e);
        }
        return ad;
    }


//***********************************************************************
//    ***********************  INSERT NEW ADD    *************************
//***********************************************************************

    @Override
    public long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }


    //***********************************************************************
//    ***********************  FIND ADD BY TITLE   *************************
//***********************************************************************
    @Override
    public List<Ad> findByTitle(String searched_ad) {
        String query = "SELECT * FROM ads WHERE title like ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, searched_ad);
            return createAdsFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a Ad by Title", e);
        }
    }




    //***********************************************************************
//    ***********************  FIND ADD BY ID    *************************
//***********************************************************************
    public Ad findById(long id) {
        String query = String.format("SELECT * FROM ads WHERE id = %d", id);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return extractAd(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }
//***********************************************************************
//    ***********************   EDIT 1 AD    ****************************
//***********************************************************************

    public void editAd(String newTitle, String newDescription, Long newId){
        String query = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newTitle);
            ps.setString(2, newDescription);
            ps.setLong(3, newId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating ad");
        }
    }



    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

}

