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

@Controller
public class UsersPhoneBook {

    @Autowired
    private final UserDataRepository userDataRepository;

    public UsersPhoneBook(final  UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getPhones(ModelMap model, @RequestParam(value = "filter", required = false) String filter) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        List<Contacts> contacts = user.getContacts();
        if (filter != null) {
            if (filter.equals("firstname")) {
                Collections.sort(contacts, new Comparator<Contacts>() {
                    @Override
                    public int compare(Contacts o1, Contacts o2) {
                        return o1.getFirstname().compareTo(o2.getFirstname());
                    }
                });
            } else if (filter.equals("lastname")) {
                Collections.sort(contacts, new Comparator<Contacts>() {
                    @Override
                    public int compare(Contacts o1, Contacts o2) {
                        return o1.getLastname().compareTo(o2.getLastname());
                    }
                });
            } else if (filter.equals("telephone_number")) {
                Collections.sort(contacts, new Comparator<Contacts>() {
                    @Override
                    public int compare(Contacts o1, Contacts o2) {
                        return o1.getTelephone_number().compareTo(o2.getTelephone_number());
                    }
                });
            }
        }
        model.addAttribute("contacts", contacts);
        return "index";
    }
}
