package ru.rzaharov.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rzaharov.crudrepository.CarDataRepository;
import ru.rzaharov.crudrepository.UserDataRepository;
import ru.rzaharov.database.DBManager;
import ru.rzaharov.repository.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
