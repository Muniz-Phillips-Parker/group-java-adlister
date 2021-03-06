package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
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


    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    public User findById(long id) {
        String query = "SELECT * FROM users WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }


    @Override
    public void updateUsername(String newUsername, String userId) {
        String query ="Update users SET username = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, newUsername);
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error updating profile", e);
        }
    }


    @Override
    public void updateEmail(String newEmail, String userId) {
        String query = "UPDATE users SET email = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, newEmail);
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error updating profile", e);
        }
    }

    @Override
    public void updatePhone(String newPhone, String userId) {
        String query = "UPDATE users SET phoneNumber = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, newPhone);
            stmt.setString(2, userId);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error updating phone number", e);
        }
    }


    @Override
    public void updatePassword(String newPassword, String userId) {
        String query = "UPDATE users SET password = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, newPassword);
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error updating profile", e);
        }
    }

    @Override
    public void destroy(long userId) {
        try {
            String deleteQuery = "DELETE FROM ads WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad", e);
        }
    }


    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password, phoneNumber) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getPhoneNumber());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("phoneNumber"),
                rs.getString("password")
        );
    }

}
