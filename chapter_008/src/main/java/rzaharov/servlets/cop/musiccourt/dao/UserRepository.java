package rzaharov.servlets.cop.musiccourt.dao;

import rzaharov.servlets.cop.musiccourt.models.*;

import java.util.List;

public interface UserRepository<T extends Model> {

    public Address getAddress(User user);

    public List<T> getMusicType(User user);

    public Role getRole(User user);

    public List<T> addUser(User user, Address address, MusicType musicType, Role role);

    public User getUser(Address address, Role role, MusicType musicType);

}
