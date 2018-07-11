package com.isa.usersengine.com.isa.usersengine.servlets;

import com.isa.usersengine.com.isa.userengine.cdi.RandomUserCDIApplicationDao;
import com.isa.usersengine.com.isa.userengine.cdi.RandomUserCDIRequestDao;
import com.isa.usersengine.com.isa.userengine.cdi.RandomUserCDISessionDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("RandomUserServlet")
public class RandomUserServlet extends HttpServlet {


    @Inject
    private
    RandomUserCDIRequestDao randomUserCDIRequestDao;

    @Inject
    private
    RandomUserCDISessionDao randomUserCDISessionDao;

    @Inject
    private
    RandomUserCDIApplicationDao randomUserCDIApplicationDao;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        writer.println("Request " + randomUserCDIRequestDao.getRandomUser());
        writer.println("Session " + randomUserCDISessionDao.getRandomUser());
        writer.println("Application " + randomUserCDIApplicationDao.getRandomUser());
        writer.close();
    }
}

