package rzaharov.servlets.cop.musiccourt.servlet;

import rzaharov.servlets.cop.musiccourt.dao.postgres.FactoryDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInController extends HttpServlet {

    private final FactoryDAO factoryDAO = FactoryDAO.Singleton.INSTANCE.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (factoryDAO.isCredentional(login, password)) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentional invalid");
            doGet(req, resp);
        }
    }
}
