package rzaharov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStorage {

    private static final Logger log = LoggerFactory.getLogger(UserStorage.class);

    private static final UserStorage instance = new UserStorage();

    private List<User> users = new CopyOnWriteArrayList<>();

    private UserStorage() {
        this.users.add(new User("1","root", "root@root", "root"));
    }

    public static UserStorage getInstance() {
        return instance;
    }

    public void add(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return this.users;
    }

    public boolean isCredentional(String login, String password) {
        boolean exists = false;
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
