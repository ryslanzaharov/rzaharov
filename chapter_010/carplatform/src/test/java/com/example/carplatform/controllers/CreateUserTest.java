package com.example.carplatform.controllers;

import com.example.carplatform.crudrepository.CarDataRepository;
import com.example.carplatform.crudrepository.UserDataRepository;
import com.example.carplatform.models.Car;
import com.example.carplatform.models.Condition;
import com.example.carplatform.models.Engine;
import com.example.carplatform.models.User;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(CreateUser.class)
public class CreateUserTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserDataRepository service;


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetCarThenPageCar() throws Exception {
        given(
                this.service.getAll()
        ).willReturn(
                new ArrayList<User>(
                        Lists.newArrayList(new User("user", "password"))
                )
        );
        this.mvc.perform(
                get("/createuser").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("CreateUser")
        );
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    public void whenPostPersonThenAdd() throws Exception {
        this.mvc.perform(
                post("/createuser")
                        .requestAttr("user", new User("user", "password"))
        ).andExpect(
                status().is3xxRedirection()
        );
        verify(this.service, times(1)).save(new User("user", "password"));
    }
}