package rzaharov.servlets.servlet.methods;

import rzaharov.servlets.servlet.User;
import rzaharov.servlets.servlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервдет для получения данных с бд.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 02.05.18.
 */

public class GetController extends HttpServlet {

  private final UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("users", UserStore.UserStoreSingleton.INSTANCE.getInstance().select());
    if (req.getSession().getAttribute("login") != null)
    req.setAttribute("role", users.select(req.getSession().getAttribute("login").toString()).getRole());
    req.getRequestDispatcher("/WEB-INF/views/servletjsp/ListView.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.sendRedirect(String.format("%s/", req.getContextPath()));
  }
}
