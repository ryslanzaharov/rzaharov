package com.example.urlshortener.controllers;

import com.example.urlshortener.models.Url;
import com.example.urlshortener.models.User;
import com.example.urlshortener.generate.Generator;
import com.example.urlshortener.repository.UserDataRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RegisterAccount {

    private final UserDataRepository userDataRepository;

    @Autowired
    public RegisterAccount(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "registerAccount", method = RequestMethod.GET)
    public String showRegisterAccountPage() {
        return "registerAccount";
    }

    @RequestMapping(value = "registerAccount", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String registerAccount(@RequestParam(value = "error", required = false) String error,
                           @RequestBody User user,
                           Model model) {
        Generator passwordGenerator = new Generator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String password = "";
        if (!user.getUsername().equals("") && !compareUserName(user.getUsername())) {
            password = passwordGenerator.generate(8);
            user.setPassword(password);
            user.setEnabled(true);
            user.setRole(1);
            userDataRepository.save(user);
        }

        return password;
    }

    public boolean compareUserName(String username) {
        boolean equally = false;
        List<User> users = userDataRepository.getAll();
        for (User us : users) {
            if (us.getUsername().equals(username)) {
                equally = true;
            }
        }
        return equally;
    }
}
