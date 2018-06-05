package rzaharov.servlets.cop.musiccourt.dao.postgres;

import rzaharov.servlets.cop.musiccourt.dao.FactoryDAO;
import rzaharov.servlets.cop.musiccourt.dao.UserDao;
import rzaharov.servlets.cop.musiccourt.dao.UserRepository;
import rzaharov.servlets.cop.musiccourt.models.Address;
import rzaharov.servlets.cop.musiccourt.models.MusicType;
import rzaharov.servlets.cop.musiccourt.models.Role;
import rzaharov.servlets.cop.musiccourt.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabaseDao implements UserDao{

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();

    private final Connection connection = factoryDAO.getConn();

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        AddressDatabaseDao add = new AddressDatabaseDao();
        try(PreparedStatement getUsers = connection.prepareStatement(UserSql.GET.QUERY)) {
            ResultSet rs = getUsers.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setRole(getRole(rs.getString("login")));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setMusicTypes(getMusicType(user));
                user.setAddress(add.getById(user.getId()));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(Integer id) {
        User user = new User();
        try(PreparedStatement get = connection.prepareStatement(UserSql.GetById.QUERY)) {
            get.setInt(1, id);
            ResultSet rs = get.executeQuery();
            if (rs.next()) {
               user.setId(rs.getInt("id"));
               user.setName(rs.getString("name"));
               user.setSurname(rs.getString("surname"));
                user.setRole_id(rs.getInt("role_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                get.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void add(User model) {
        try(PreparedStatement add = connection.prepareStatement(UserSql.ADD.QUERY)) {
            add.setString(1, model.getName());
            add.setString(2, model.getSurname());
            add.setInt(3, model.getRole_id());
            add.setString(4, model.getLogin());
            add.setString(5, model.getPassword());
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User model) {
        try(PreparedStatement update = connection.prepareStatement(UserSql.UPDATE.QUERY)) {
            update.setString(1, model.getName());
            update.setString(2, model.getSurname());
            update.setInt(3, model.getRole_id());
            update.setString(4, model.getLogin());
            update.setString(5, model.getPassword());
            update.setInt(6, model.getId());
            update.executeUpdate();
            UsersMusicTypesDatabase usersMusicTypesDatabase = new UsersMusicTypesDatabase();
            usersMusicTypesDatabase.deleteUserMusicTypes(model.getId());
            for (MusicType musicType : model.getMusicTypes())
                usersMusicTypesDatabase.addUserMusicTypes(model.getId(), musicType.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement delete = connection.prepareStatement(UserSql.DELETE.QUERY)) {
            delete.setInt(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address getAddress(User user) {
        AddressDatabaseDao addressDao = new AddressDatabaseDao();
        Address address = addressDao.getById(user.getId());
        return address;
    }

    @Override
    public List<MusicType> getMusicType(User user) {
        List<MusicType> list = new ArrayList<>();
        UsersMusicTypesDatabase umtd = new UsersMusicTypesDatabase();
        List<Integer> typeMusId = umtd.getUserMusicTypes(user.getId());
        MusicTypeDatabaseDao mt = new MusicTypeDatabaseDao();
        for (Integer typeId : typeMusId) {
            list.add(mt.getById(typeId));
        }
        return list;
    }

    @Override
    public Role getRole(String login) {
        RoleDatabaseDao roleDao = new RoleDatabaseDao();
        User user = getUser(login);
        Role role = roleDao.getById(user.getRole_id());
        return role;
    }

    @Override
    public Role getRole(Integer role_id) {
        RoleDatabaseDao roleDao = new RoleDatabaseDao();
        Role role = roleDao.getById(role_id);
        return role;
    }

    @Override
    public void addUser(User user, Address address,List<Integer> musicTypes, Role role) {
        add(user);
        AddressDatabaseDao addAddress = new AddressDatabaseDao();
        addAddress.add(address);
        UsersMusicTypesDatabase usersMusicTypesDatabase = new UsersMusicTypesDatabase();
        for (Integer musicType : musicTypes)
            usersMusicTypesDatabase.addUserMusicTypes(getUser(user.getLogin()).getId(), musicType);
    }

    @Override
    public User getUser(Address address, Role role, MusicType musicType) {
        User user = getById(address.getId());
        return user;
    }



    @Override
    public User getUser(String login) {
        User user = new User();
        try(PreparedStatement get = connection.prepareStatement(UserSql.GetByLogin.QUERY)) {
            get.setString(1, login);
            ResultSet rs = get.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole_id(rs.getInt("role_id"));
                user.setRole(getRole(user.getRole_id()));
                user.setAddress(getAddress(user));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(String login) {
        try(PreparedStatement del = connection.prepareStatement(UserSql.DeleteByLogin.QUERY)) {
            del.setString(1, login);
            del.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    enum UserSql {
        GetById("SELECT * FROM users WHERE id = ?"),
        GET("SELECT * FROM users"),
        DELETE("DELETE FROM users WHERE id = ?"),
        ADD("INSERT INTO users(name, surname, role_id, login, password) VALUES(?, ?, ?, ?, ?)"),
        UPDATE("UPDATE users SET name = ?, surname = ?, role_id = ?, login = ?, password = ? WHERE id = ?"),
        GetByLogin("SELECT * FROM users WHERE login = ?"),
        DeleteByLogin("DELETE FROM users WHERE login = ?");

        String QUERY;

        UserSql(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
