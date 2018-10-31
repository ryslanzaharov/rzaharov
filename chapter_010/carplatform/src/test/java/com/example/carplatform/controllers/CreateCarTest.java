package com.example.carplatform.controllers;

import com.example.carplatform.crudrepository.CarDataRepository;
import com.example.carplatform.crudrepository.UserDataRepository;
import com.example.carplatform.models.*;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
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
@WebMvcTest(CreateCar.class)
public class CreateCarTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarDataRepository service;

    @MockBean
    private UserDataRepository userDataRepository;


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetCarThenPageCar() throws Exception {
        given(
                this.service.getAll()
        ).willReturn(
                new ArrayList<Car>(
                        Lists.newArrayList(new Car("mark", "model", "model", 123,  "model",
                                new Engine( "model", "model", "model"),
                              //  userDataRepository.getUserByLogin("122").orElseThrow(() -> new EntityNotFoundException("122")),
                                new User(),
                                new Condition( "model", 123, 123),
                                "model", new Timestamp(System.currentTimeMillis())))
                )
        );
        this.mvc.perform(
                get("/createCar").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("CreateCar")
        );
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    public void whenPostPersonThenAdd() throws Exception {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        this.mvc.perform(
                post("/createCar")
                .sessionAttr("login", "122")
                .requestAttr("engine", new Engine( "model", "model", "model"))
                .requestAttr("condition", new Condition( "model", 123, 123))
                .requestAttr("cars", new Car("mark", "model", "body_type", 123, "sale", "photo", date))

        ).andExpect(
                status().is3xxRedirection()
        );
        verify(this.service, times(1)).save(new Car("mark", "model", "body_type", 123,  "sale",
                new Engine( "model", "model", "model"),
                  userDataRepository.getUserByLogin("122").orElseThrow(() -> new EntityNotFoundException("122")),
                new Condition( "model", 123, 123),
                "photo", date));
    }
}