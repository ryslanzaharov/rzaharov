package com.platforms.controllers;

import com.platforms.crudrepository.CarDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class Index {

    private final CarDataRepository carDataRepository;

    @Autowired
    public Index( final CarDataRepository carDataRepository) {
        this.carDataRepository = carDataRepository;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showCar() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public void showCars(@RequestParam("mark") String mark, @RequestParam("last") Boolean last, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        if (!mark.substring(5).isEmpty()) {
            writer.append(objectMapper.writeValueAsString(carDataRepository.getByMark(mark.substring(5))));
        }
        else if (last == true)
            writer.append(objectMapper.writeValueAsString(carDataRepository.getLastDay()));
        else
            writer.append(objectMapper.writeValueAsString(carDataRepository.getAll()));
        writer.flush();
    }
}
