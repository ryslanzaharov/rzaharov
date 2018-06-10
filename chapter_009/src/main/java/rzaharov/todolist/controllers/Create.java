package rzaharov.todolist.controllers;

import rzaharov.todolist.database.DBManager;
import rzaharov.todolist.models.Item;
import rzaharov.todolist.repository.ItemRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class Create extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager.getInstance().buildSessionFactry();
        Item item = new Item();
        item.setDesc(req.getParameter("desc"));
        item.setCreationTime(new Timestamp(System.currentTimeMillis()));
        ItemRepository.getItemRepository().add(item);
        DBManager.getInstance().closeSessionFactory();
    }
}
