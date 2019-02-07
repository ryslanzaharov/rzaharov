package com.example.demo.controllers;

import com.example.demo.models.Contacts;
import com.example.demo.models.User;
import com.example.demo.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * Registration users.
 * @author Руслан .
 * @version 01.
 * @since 01.02.2019.
 */
@Controller
public class UsersPhoneBook {

    /**
     * Data repository for CRUD operation.
     */
    private final UserDataRepository userDataRepository;

    /**
     * * Constructor for initialize the repository.
     * @param userDataRepository repository for users data.
     */
    @Autowired
    public UsersPhoneBook(final  UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    /**
     * Shows added contacts from phonebook.
     * @param model contains users contacts.
     * @param filter conatins a label to filter when displaying contacts.
     * @return page index.
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getPhones(ModelMap model, @RequestParam(value = "filter", required = false) String filter) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        List<Contacts> contacts = user.getContacts();
        if (filter != null) {
            if (filter.equals("firstname")) {
                contacts.sort((o1, o2) -> o1.getFirstname().compareTo(o2.getFirstname()));
            } else if (filter.equals("lastname")) {
               contacts.sort(((o1, o2) -> o1.getLastname().compareTo(o2.getLastname())));
            } else if (filter.equals("telephone_number")) {
                contacts.sort(((o1, o2) -> o1.getTelephone_number().compareTo(o2.getTelephone_number())));
            }
        }
        model.addAttribute("contacts", contacts);
        return "index";
    }
}
