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

/**
 * Add users contacts.
 * @author Руслан .
 * @version 01.
 * @since 01.02.2019.
 */
@Controller
public class AddContacts {

    /**
     * Data repository for CRUD operation.
     */
    private final ContactsDataRepository contactsDataRepository;
    private final UserDataRepository userDataRepository;

    /**
     * @param contactsDataRepository repository for user contacts.
     * @param userDataRepository repository for users data.
     */
    @Autowired
    public AddContacts(final ContactsDataRepository contactsDataRepository, final UserDataRepository userDataRepository) {
        this.contactsDataRepository = contactsDataRepository;
        this.userDataRepository = userDataRepository;
    }

    /**
     * displays a page for adding a contact.
     * @return page addcontacts.
     */
    @RequestMapping(value = "/addcontacts", method = RequestMethod.GET)
    public String showAddContactPage() {
        return "addcontacts";
    }

    /**
     * add contact in phonebook.
     * @param contacts users contact data for add in phonebook.
     */
    @RequestMapping(value = "/addcontacts", method = RequestMethod.POST)
    public void addContact(@RequestBody Contacts contacts) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        contacts.setUser(user);
        contactsDataRepository.save(contacts);

    }
}
