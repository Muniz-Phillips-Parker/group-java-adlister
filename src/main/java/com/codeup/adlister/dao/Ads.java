package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    long insert(Ad ad);

    // insert a new ad and return the new ad's id
    List<Ad>  findByTitle(String searched_ad);




    Ad findById(long id);

    public void editAd(String title, String description, Long newId);

    public List<Ad> allForUser(User user);

    //This is the void element associated with the DeleteAdServlet
    Ad getAdById(long id);
    void deleteAd(Ad ad);
    Long delete(Long adId);



}
