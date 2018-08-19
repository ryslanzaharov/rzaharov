package ru.rzaharov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Signout{

    @RequestMapping(name = "/signout", method = RequestMethod.GET)
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException   {
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("login", null);
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }
}
