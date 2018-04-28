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
 * @since 22.04.18.
 */

public class Delete extends HttpServlet {

   private final UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        int i = users.delete(req.getParameter("email"));
        pw.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Delete</title>" +
                "</head>" +
                "<body>" +
                "<form action='"+req.getContextPath()+"/list' method='get'>" +
                "<button type='submit'>Back</button>" +
                "</form>" +
                "</body>" +
                "</html>");
        pw.flush();
        if (i > 0)
            pw.println("account deleted");
        else
            pw.append("no account deleted");
        pw.flush();
    }

}
