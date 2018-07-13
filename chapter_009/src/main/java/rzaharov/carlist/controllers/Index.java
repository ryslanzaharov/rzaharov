package rzaharov.carlist.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.repository.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Index extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // DBManager.getInstance().getSession();
        DBManager.getInstance().buildSessionFactory();
        resp.setContentType("text/html");
        System.out.println(req.getParameter("mark") + "marlk");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        writer.append(objectMapper.writeValueAsString(CarRepository.getInstance().getAll()));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
