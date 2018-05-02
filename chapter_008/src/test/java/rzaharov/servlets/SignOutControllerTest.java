package rzaharov.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SignOutControllerTest {

    @Test
    public void whenSignOut() throws ServletException, IOException {
        SignOutController controller = new SignOutController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(new MockHttpSession());
        when(request.getParameter("login")).thenReturn("root");
        when(request.getParameter("password")).thenReturn("root");
        controller.doPost(request, response);
        assertEquals(request.getSession().getAttribute("login"), null);
    }

}