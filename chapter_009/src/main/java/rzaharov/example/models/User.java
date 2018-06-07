package rzaharov.example.models;

import java.sql.Timestamp;

/**
 * @author rzaharov
 * @version 0.1
 * @since 07.06.2018
 *
 * Describe user model in system.
 */

public class User {

    private int id;
    private String login;
    private String password;
    private Timestamp created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
