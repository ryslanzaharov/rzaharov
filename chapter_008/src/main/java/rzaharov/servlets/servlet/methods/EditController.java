package rzaharov.servlets.servlet.methods;

import rzaharov.servlets.servlet.User;
import rzaharov.servlets.servlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Сервлет для изменения данных пользователя.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 29.04.18.
 */

public class EditController extends HttpServlet{

   private final UserStore users = UserStore.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users.select(req.getParameter("email")));
        String email = req.getParameter("email");
        String role = users.select(req.getSession().getAttribute("login").toString()).getRole();
        if (email.equals(req.getSession().getAttribute("login")) ||
                role.equals("Admin")) {
            req.setAttribute("role", role);
            req.getRequestDispatcher("/WEB-INF/views/servletjsp/EditView.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "No access rights!");
            req.getRequestDispatcher("/WEB-INF/views/servletjsp/").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.user = new User();
        String oldEmail = req.getParameter("oldEmail");
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("role"));
        users.update(oldEmail, user);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
