package com.example.demo.controllers;

import com.example.demo.models.Contacts;
import com.example.demo.models.User;
import com.example.demo.repository.ContactsDataRepository;
import com.example.demo.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;

@Controller
public class AddContacts {

    private final ContactsDataRepository contactsDataRepository;
    private final UserDataRepository userDataRepository;

    @Autowired
    public AddContacts(final ContactsDataRepository contactsDataRepository, final UserDataRepository userDataRepository) {
        this.contactsDataRepository = contactsDataRepository;
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "/addcontacts", method = RequestMethod.GET)
    public String getCreateNumberPage() {
        return "addcontacts";
    }

    @RequestMapping(value = "/addcontacts", method = RequestMethod.POST)
    public void addNumber(@RequestBody Contacts contacts) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        contacts.setUser(user);
        contactsDataRepository.save(contacts);

    }
}
