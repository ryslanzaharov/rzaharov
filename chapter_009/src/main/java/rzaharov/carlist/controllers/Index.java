package rzaharov.carlist.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.models.Car;
import rzaharov.carlist.repository.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager.getInstance().buildSessionFactory();
        resp.setContentType("text/html");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        if (!req.getParameter("mark").substring(5).isEmpty() || Boolean.valueOf(req.getParameter("last")) == true) {
            writer.append(objectMapper.writeValueAsString(CarRepository.getInstance().getByMark(req.getParameter("mark").substring(5), Boolean.valueOf(req.getParameter("last")))));
        }
        else
            writer.append(objectMapper.writeValueAsString(CarRepository.getInstance().getAll()));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
