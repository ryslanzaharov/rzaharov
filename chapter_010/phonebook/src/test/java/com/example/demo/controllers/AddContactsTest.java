package com.example.demo.controllers;

import com.example.demo.models.Contacts;
import com.example.demo.repository.ContactsDataRepository;
import com.example.demo.repository.UserDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(AddContacts.class)
@TestPropertySource("/application.properties")
public class AddContactsTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "username", roles = {"USER"}, password = "password")
    public void whenAddContactInPhoneBook() throws Exception{
        this.mvc.perform(
                get("/addcontacts").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("addcontacts")
        );
    }

}