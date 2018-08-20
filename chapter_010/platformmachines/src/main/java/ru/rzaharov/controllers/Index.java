package ru.rzaharov.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public void showCars(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager.getInstance().buildSessionFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        if (!req.getParameter("mark").substring(5).isEmpty() || Boolean.valueOf(req.getParameter("last")) == true) {
            writer.append(objectMapper.writeValueAsString(CarRepository.getInstance().getByMark(req.getParameter("mark").substring(5), Boolean.valueOf(req.getParameter("last")))));
        }
        else
            writer.append(objectMapper.writeValueAsString(CarRepository.getInstance().getAll()));
        writer.flush();
    }
}
