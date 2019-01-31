package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.RoleDataRepository;
import com.example.demo.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class Registration {

    private final UserDataRepository userDataRepository;
    private final RoleDataRepository roleDataRepository;

    @Autowired
    public Registration(final UserDataRepository userDataRepository, final RoleDataRepository roleDataRepository) {
        this.userDataRepository = userDataRepository;
        this.roleDataRepository = roleDataRepository;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String pageRegistration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody String userRegistration(@RequestBody User user, ModelMap model) {
        String account = null;
        User userDB = null;
        try {
            userDB = userDataRepository.getUserByLogin(user.getLogin()).orElseThrow(() -> new EntityNotFoundException(user.getLogin()));

        } catch (EntityNotFoundException e) {

        }
        if (userDB == null) {
            user.setRole(roleDataRepository.findOne(1));
            userDataRepository.save(user);
            account = "Your account is opened.";
        }
        else {
            model.addAttribute("account", "error");
            account = "Your account is not opened, change your login";
        }
        return account;
    }
}
