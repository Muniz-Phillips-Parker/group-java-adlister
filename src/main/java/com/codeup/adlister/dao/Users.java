package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);

//    added this so if email is duplicated on the registerServlet, user will be forced to use a different email.
    User findByEmail(String email);

}
