package rzaharov.servlets.cop.musiccourt.dao;

import rzaharov.servlets.cop.musiccourt.models.Address;
import rzaharov.servlets.cop.musiccourt.models.MusicType;
import rzaharov.servlets.cop.musiccourt.models.Role;
import rzaharov.servlets.cop.musiccourt.models.User;

import java.util.List;

public interface UserDao  extends DAO<User> {

    public Address getAddress(User user);

    public List<MusicType> getMusicType(User user);

    public Role getRole(String login);

    public Role getRole(Integer role_id);

    public void addUser(User user, Address address, List<Integer> musicTypes, Role role);

    public User getUser(Address address, Role role, MusicType musicType);

    public User getUser(String login);

    public void delete(String login);
}
