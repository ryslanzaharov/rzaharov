package rzaharov.servlets.cop.musiccourt.dao.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rzaharov.servlets.cop.musiccourt.dao.RoleDao;
import rzaharov.servlets.cop.musiccourt.models.Role;
import rzaharov.servlets.cop.musiccourt.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDatabaseDao implements RoleDao {

    private static final Logger Log = LoggerFactory.getLogger(RoleDatabaseDao.class);

    private final FactoryDAO factoryDAO = FactoryDAO.Singleton.INSTANCE.getInstance();

    private final Connection connection = factoryDAO.getConn();

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try(PreparedStatement get = connection.prepareStatement(RoleSql.GET.QUERY)) {
            try(ResultSet rs = get.executeQuery()) {
                while (rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        Role role = new Role();
        try(PreparedStatement getById = connection.prepareStatement(RoleSql.GetById.QUERY)) {
            getById.setInt(1, id);
            try(ResultSet rs = getById.executeQuery()) {
                if (rs.next()) {
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                }
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return role;
    }

    @Override
    public void add(Role model) {
        try(PreparedStatement add = connection.prepareStatement(RoleSql.ADD.QUERY)) {
            add.setString(1, model.getName());
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Role model) {
        try(PreparedStatement update = connection.prepareStatement(RoleSql.UPDATE.QUERY)) {
            update.setString(1, model.getName());
            update.setInt(2, model.getId());
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement delete = connection.prepareStatement(RoleSql.DELETE.QUERY)) {
            delete.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getUser(Role role) {
        List<User> list = new ArrayList<>();
        try(PreparedStatement getUser = connection.prepareStatement(RoleSql.GetUser.QUERY)) {
            getUser.setInt(1, role.getId());
            try(ResultSet rs = getUser.executeQuery()) {
                UserDatabaseDao udd = new UserDatabaseDao();
                while (rs.next()) {
                    list.add(udd.getById(rs.getInt("id")));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return list;
    }

    enum RoleSql {
        GetById("SELECT * FROM roles WHERE id = ?"),
        GET("SELECT * FROM roles"),
        DELETE("DELETE FROM roles WHERE id = ?"),
        ADD("INSERT INTO roles(name) VALUES(?)"),
        UPDATE("UPDATE roles SET name = ? WHERE id = ?"),
        GetUser("SELECT * FROM users WHERE role_id = ?");

        String QUERY;

        RoleSql(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
