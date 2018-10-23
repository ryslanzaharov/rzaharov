package com.platforms.controllers;

import com.platforms.crudrepository.UserDataRepository;
import com.platforms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
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
    public void addUser(@ModelAttribute User user) {
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userDataRepository.save(user);
    }
}
