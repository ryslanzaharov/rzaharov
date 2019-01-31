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

@Controller
public class DeleteContacts {

    private final UserDataRepository userDataRepository;
    private final ContactsDataRepository contactsDataRepository;

    @Autowired
    public DeleteContacts(final UserDataRepository userDataRepository, final ContactsDataRepository contactsDataRepository) {
        this.userDataRepository = userDataRepository;
        this.contactsDataRepository = contactsDataRepository;
    }

    @RequestMapping(value = "/deletecontacts", method = RequestMethod.GET)
    public String getContacts(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        List<Contacts> contacts = user.getContacts();
        model.addAttribute("contacts", contacts);
        return "deletecontacts";
    }


    @RequestMapping(value = "/deletecontacts", method = RequestMethod.POST)
    public String removeContact(@ModelAttribute Contacts contact) {
        System.out.println(contact);
        contactsDataRepository.delete(contact);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
//        contact.setUser(user);
//        contactsDataRepository.save(contact);
        return "redirect:deletecontacts";
    }
}
