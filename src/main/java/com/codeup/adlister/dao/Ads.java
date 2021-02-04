package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    long insert(Ad ad);

    // insert a new ad and return the new ad's id
    List<Ad>  findByTitle(String searched_ad);

}
