package com.example.carplatform.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.carplatform.crudrepository.UserDataRepository;
import com.example.carplatform.models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Controller
public class CreateUser extends HttpServlet {

    private final UserDataRepository userDataRepository;

    @Autowired
    public CreateUser(final UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public String showCreatePage(){
        return "CreateUser";
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, HttpServletRequest req) {
        user = (User) (req.getAttribute("user"));
        //user.setCreated(new Timestamp(System.currentTimeMillis()));
        userDataRepository.save(user);
        return "redirect:CreateUser";
    }
}
