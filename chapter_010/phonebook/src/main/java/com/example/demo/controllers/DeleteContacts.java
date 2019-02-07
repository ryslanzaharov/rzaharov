package com.example.demo.controllers;

import com.example.demo.models.Contacts;
import com.example.demo.models.User;
import com.example.demo.repository.ContactsDataRepository;
import com.example.demo.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Delete users contacts.
 * @author Руслан .
 * @version 01.
 * @since 01.02.2019.
 */
@Controller
public class DeleteContacts {

    /**
     * Data repository for CRUD operation.
     */
    private final UserDataRepository userDataRepository;
    private final ContactsDataRepository contactsDataRepository;

    /**
     * @param contactsDataRepository repository for user contacts.
     * @param userDataRepository repository for users data.
     */
    @Autowired
    public DeleteContacts(final UserDataRepository userDataRepository, final ContactsDataRepository contactsDataRepository) {
        this.userDataRepository = userDataRepository;
        this.contactsDataRepository = contactsDataRepository;
    }

    /**
     * Shows added contacts from phonebook.
     * @param model get user contacts.
     * @return deletecontacts page.
     */
    @RequestMapping(value = "/deletecontacts", method = RequestMethod.GET)
    public String getContacts(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        List<Contacts> contacts = user.getContacts();
        model.addAttribute("contacts", contacts);
        return "deletecontacts";
    }


    /**
     * Removes added contacts from phonebook.
     * @param contact
     * @return deletecontacts page.
     */
    @RequestMapping(value = "/deletecontacts", method = RequestMethod.POST)
    public String removeContact(@ModelAttribute Contacts contact) {
        contactsDataRepository.delete(contact);
        return "redirect:deletecontacts";
    }
}
