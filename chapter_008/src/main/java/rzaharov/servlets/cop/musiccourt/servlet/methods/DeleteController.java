package rzaharov.servlets.cop.musiccourt.servlet.methods;

import rzaharov.servlets.cop.musiccourt.dao.postgres.UserDatabaseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для удаления данных пользователя.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 02.05.18.
 */

public class DeleteController extends HttpServlet {

    private final UserDatabaseDao udd = new UserDatabaseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String role = udd.getRole(req.getSession().getAttribute("login").toString()).getName();
        if (login.equals(req.getSession().getAttribute("login")) ||
                role.equals("Admin")) {
            doPost(req, resp);
        }
        else {
            req.setAttribute("error", "No access rights!");
        }
        req.getRequestDispatcher(String.format("%s/", req.getContextPath())).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        udd.delete(req.getParameter("login"));
        if (req.getParameter("login").equals(req.getSession().getAttribute("login")))
            req.getSession().setAttribute("login", null);
    }

}
