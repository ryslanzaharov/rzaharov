package rzaharov.servlets.servlet.methods;

import org.junit.Test;
import rzaharov.servlets.servlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditControllerTest {

    @Test
    public void whenUpdateUsersData() throws ServletException, IOException {
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

        EditController controller = new EditController();
        HttpServletRequest reqEdit = mock(HttpServletRequest.class);
        HttpServletResponse respEdit  = mock(HttpServletResponse.class);
        when(reqEdit.getParameter("oldEmail")).thenReturn("userEmail");
        when(reqEdit.getParameter("email")).thenReturn("userE");
        when(reqEdit.getParameter("name")).thenReturn("userN");
        when(reqEdit.getParameter("login")).thenReturn("userL");
        when(reqEdit.getParameter("password")).thenReturn("userP");
        when(reqEdit.getParameter("role")).thenReturn("Admin");
        controller.doPost(reqEdit, respEdit);

        assertThat(users.select("userE").getEmail(), is("userE"));
        assertThat(users.select("userE").getName(), is("userN"));
        assertThat(users.select("userE").getLogin(), is("userL"));
        assertThat(users.select("userE").getPassword(), is("userP"));
        assertThat(users.select("userE").getRole(), is("Admin"));
    }

}