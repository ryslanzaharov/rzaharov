package rzaharov.servlets.servlet.methods;

import rzaharov.servlets.servlet.User;
import rzaharov.servlets.servlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для удаления данных пользователя.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 02.05.18.
 */

public class DeleteController extends HttpServlet {

   private final UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email");
        String role = users.select(req.getSession().getAttribute("login").toString()).getRole();
        if (email.equals(req.getSession().getAttribute("login")) ||
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
        users.delete(req.getParameter("email"));
        if (req.getParameter("email").equals(req.getSession().getAttribute("login")))
            req.getSession().setAttribute("login", null);
    }

}
