package ru.rzaharov.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void showCars(@RequestParam("mark") String mark, @RequestParam("last") Boolean last, HttpServletResponse resp) throws ServletException, IOException {
        DBManager.getInstance().buildSessionFactory();
        System.out.println(mark.substring(5));
        System.out.println(last);
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        if (!mark.substring(5).isEmpty() || last == true) {
            writer.append(objectMapper.writeValueAsString(CarRepository.getInstance().getByMark(mark.substring(5), last)));
        }
        else
            writer.append(objectMapper.writeValueAsString(CarRepository.getInstance().getAll()));
        writer.flush();
    }
}
