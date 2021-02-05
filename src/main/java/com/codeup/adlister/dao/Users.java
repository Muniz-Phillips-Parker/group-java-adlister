package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);
    User findById(long id);
  
  
    //    added this so if email is duplicated on the registerServlet, user will be forced to use a different email.
    User findByEmail(String email);


    void updateUsername(String newUsername, String userId);

    void updateEmail(String newEmail, String userId);

    void updatePhone(String newPhone, String userId);

    void updatePassword(String newPassword, String userId);

    void destroy(long userId);

}
