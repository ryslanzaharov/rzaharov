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

public class Edit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager.getInstance().buildSessionFactry();
        Item item = ItemRepository.getItemRepository().getById(Integer.parseInt(req.getParameter("id")));
        item.setDone(Boolean.parseBoolean(req.getParameter("done")));
        ItemRepository.getItemRepository().update(item);
        DBManager.getInstance().closeSessionFactory();
    }
}
