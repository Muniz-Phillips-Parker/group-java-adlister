//package com.codeup.adlister.controllers;
//
//import com.codeup.adlister.dao.DaoFactory;
//import com.codeup.adlister.models.Ad;
//import com.codeup.adlister.models.User;
//
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/delete/*")
//public class DeleteAdServlet extends HttpServlet {
////    Ad ad;
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String selectedAd = request.getPathInfo();
//        try {
//            long id = Long.parseLong(request.getParameter("id"));
//           Ad ad = DaoFactory.getAdsDao().findById(id);
//            request.setAttribute("id", id);
//            request.setAttribute("ad", ad);
//            request.getRequestDispatcher("/WEB-INF/ads/DeleteAd.jsp").forward(request, response);
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        User user = (User) request.getSession().getAttribute("user");
//        Ad ad = (Ad) request.getSession().getAttribute("ad");
//
//        DaoFactory.getAdsDao().deleteAd(ad);
//        response.sendRedirect("/profile");
//    }
//}

package com.codeup.adlister.controllers;


import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/ads/delete")
public class DeleteAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Why grab a query string and substring?
        String query = request.getQueryString();
        Long id = Long.valueOf(query.substring(3));
        DaoFactory.getAdsDao().delete(id);
        response.sendRedirect("/profile");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }


}
