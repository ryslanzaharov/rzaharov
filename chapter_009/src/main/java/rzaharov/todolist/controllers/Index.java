package rzaharov.todolist.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import rzaharov.todolist.database.DBManager;
import rzaharov.todolist.repository.ItemRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println(req.getParameter("showdone"));
        boolean done = Boolean.valueOf(req.getParameter("done"));
        System.out.println(done);
        DBManager.getInstance().buildSessionFactry();
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        writer.append(objectMapper.writeValueAsString(ItemRepository.getItemRepository().getAll(done)));
        writer.flush();
        DBManager.getInstance().closeSessionFactory();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
