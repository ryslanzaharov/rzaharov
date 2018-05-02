package rzaharov.servlets;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SigninControllerTest {

    @Test
    public void whenSignIn() throws ServletException, IOException {
        SigninController controller = new SigninController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(new MockHttpSession());
        when(request.getParameter("login")).thenReturn("root");
        when(request.getParameter("password")).thenReturn("root");

        controller.doPost(request, response);

        assertThat(request.getSession().getAttribute("login"), is("root"));

    }

}