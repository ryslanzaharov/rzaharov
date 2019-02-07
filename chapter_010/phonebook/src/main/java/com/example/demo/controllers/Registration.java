package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repository.RoleDataRepository;
import com.example.demo.repository.UserDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

/**
 * Registration users.
 * @author Руслан .
 * @version 01.
 * @since 01.02.2019.
 */
@Controller
public class Registration {

    private static final Logger Log = LoggerFactory.getLogger(Registration.class);

    /**
     * Data repository for CRUD operation.
     */
    private final UserDataRepository userDataRepository;
    private final RoleDataRepository roleDataRepository;

    /**
     * Constructor for initialize the repository.
     * @param userDataRepository repository for users data.
     * @param roleDataRepository repository for users role data.
     */
    @Autowired
    public Registration(final UserDataRepository userDataRepository, final RoleDataRepository roleDataRepository) {
        this.userDataRepository = userDataRepository;
        this.roleDataRepository = roleDataRepository;
    }

    /**
     * Shows pages for registration.
     * @return page registration.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String pageRegistration() {
        return "registration";
    }

    /**
     * Registration users.
     * @param user data for registration.
     * @param model error information.
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody String userRegistration(@RequestBody User user, ModelMap model) {
        String account = null;
        User userDB = null;
        try {
            userDB = userDataRepository.getUserByLogin(user.getLogin()).orElseThrow(() -> new EntityNotFoundException(user.getLogin()));

        } catch (EntityNotFoundException e) {
            Log.error(e.getMessage(), e);
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
