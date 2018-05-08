package rzaharov.servlets;

import rzaharov.servlets.servlet.UserStore;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 02.05.18.
 */

public class AuthFilter implements Filter {

    private final UserStore users = UserStore.getInstance();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/signin") || request.getRequestURI().contains("/create")){
            chain.doFilter(req, resp);
        }
        else {
            HttpSession session = request.getSession();
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    ((HttpServletResponse) resp).sendRedirect(String.format("%s/signin", request.getContextPath()));
                    return;
                }
            }
            chain.doFilter(req, resp);
        }
    }


    @Override
    public void destroy() {

    }
}
