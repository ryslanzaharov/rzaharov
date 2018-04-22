package rzaharov.servlets.crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Сервлет.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 22.04.18.
 */

public class UsersServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();
    private User user;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        this.user = new User();
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        int i = users.insert(user);
        if (i > 0) {
            doGet(req, resp);
            pw.append("user data added");
        }
        else
            pw.append("no user data added");
        pw.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        this.user = new User();
        String oldEmail = req.getParameter("oldEmail");
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        int i = users.update(oldEmail, user);
        if (i > 0) {
            doGet(req, resp);
            pw.append("user data updated");
        }
        else
            pw.append("no user data updated");
        pw.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        User user = users.select(email);
        pw.append(String.format("email - %s, name - %s, login - %s\n", email, user.getName(), user.getLogin(), user.getCreateDate()));
        pw.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email");
        int i = users.delete(email);
        PrintWriter pw = resp.getWriter();
        if (i > 0)
            pw.println("account deleted");
        else
            pw.append("no account deleted");
        pw.flush();
    }
}
