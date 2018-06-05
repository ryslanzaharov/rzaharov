package rzaharov.servlets.cop.musiccourt.dao;

import rzaharov.servlets.cop.musiccourt.models.Role;
import rzaharov.servlets.cop.musiccourt.models.User;

import java.util.List;

public interface RoleDao  extends DAO<Role> {

    public List<User> getUser(Role role);
}
