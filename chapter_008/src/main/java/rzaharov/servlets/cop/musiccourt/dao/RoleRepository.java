package rzaharov.servlets.cop.musiccourt.dao;

import rzaharov.servlets.cop.musiccourt.models.Model;
import rzaharov.servlets.cop.musiccourt.models.Role;

import java.util.List;

public interface RoleRepository<T extends Model> {

    public List<T> getUser(Role role);
}
