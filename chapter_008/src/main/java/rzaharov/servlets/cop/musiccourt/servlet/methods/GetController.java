package rzaharov.servlets.cop.musiccourt.servlet.methods;

import rzaharov.servlets.cop.musiccourt.dao.postgres.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class GetController extends HttpServlet {

    private final UserDatabaseDao udd = new UserDatabaseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", udd.getAll());
        req.setAttribute("musictypes", udd.getAll());
        if (req.getSession().getAttribute("login") != null)
            req.setAttribute("role", udd.getRole(req.getSession().getAttribute("login").toString()).getName());
        req.getRequestDispatcher("/WEB-INF/views/musiccourt/UsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
