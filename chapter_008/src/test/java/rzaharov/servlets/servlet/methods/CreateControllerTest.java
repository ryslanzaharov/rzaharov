package rzaharov.servlets.servlet.methods;

import org.junit.Test;
import rzaharov.servlets.servlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateControllerTest {

    @Test
    public void whenCreateNewUserAccount() throws ServletException, IOException {
        UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();
        CreateController controller = new CreateController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("email")).thenReturn("userEmail");
        when(request.getParameter("name")).thenReturn("userName");
        when(request.getParameter("login")).thenReturn("userLogin");
        when(request.getParameter("password")).thenReturn("userPassword");
        when(request.getParameter("role")).thenReturn("User");

        controller.doPost(request, response);
        assertThat(users.select("userEmail").getEmail(), is("userEmail"));
    }

}