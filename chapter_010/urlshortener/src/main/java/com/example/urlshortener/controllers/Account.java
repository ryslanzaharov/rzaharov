package com.example.urlshortener.controllers;

import com.example.urlshortener.models.User;
import com.example.urlshortener.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Account {

    private final UserDataRepository userDataRepository;

    @Autowired
    public Account(final UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET,
             produces = "application/json")
    public String accountPage() {
        return "account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String accountRegistration(@ModelAttribute User user, @RequestParam("username") String username,
                                      @RequestParam("password") String password) {

        return "registration";
    }
}
