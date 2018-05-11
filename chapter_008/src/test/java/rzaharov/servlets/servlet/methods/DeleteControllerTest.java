package rzaharov.servlets.servlet.methods;

import org.junit.Test;
import rzaharov.servlets.MockHttpSession;
import rzaharov.servlets.servlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteControllerTest {

    @Test
    public void whenDeleteUserData() throws ServletException, IOException {
        UserStore users = UserStore.getInstance();

        CreateController controllerCreate = new CreateController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("email")).thenReturn("userEmail");
        when(request.getParameter("name")).thenReturn("userName");
        when(request.getParameter("login")).thenReturn("userLogin");
        when(request.getParameter("password")).thenReturn("userPassword");
        when(request.getParameter("role")).thenReturn("User");
        controllerCreate.doPost(request, response);
        assertThat(users.select("userEmail").getEmail(), is("userEmail"));

        DeleteController controllerDelete = new DeleteController();

        HttpServletRequest reqDel = mock(HttpServletRequest.class);
        HttpServletResponse respDel = mock(HttpServletResponse.class);
        when(reqDel.getParameter("email")).thenReturn("userEmail");
        when(reqDel.getParameter("role")).thenReturn("User");
        when(reqDel.getSession()).thenReturn(new MockHttpSession());
        controllerDelete.doPost(reqDel, respDel);

        assertEquals(users.select("userEmail").getEmail(), null);
    }

}