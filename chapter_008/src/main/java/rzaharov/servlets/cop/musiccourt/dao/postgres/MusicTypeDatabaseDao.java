package rzaharov.servlets.cop.musiccourt.dao.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rzaharov.servlets.cop.musiccourt.dao.MusicTypeDao;
import rzaharov.servlets.cop.musiccourt.models.MusicType;
import rzaharov.servlets.cop.musiccourt.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicTypeDatabaseDao implements MusicTypeDao {

    private static final Logger Log = LoggerFactory.getLogger(MusicTypeDatabaseDao.class);

    private final FactoryDAO factoryDAO = FactoryDAO.Singleton.INSTANCE.getInstance();

    private final Connection connection = factoryDAO.getConn();

    @Override
    public List<MusicType> getAll() {
        return null;
    }

    @Override
    public MusicType getById(Integer id) {
        MusicType musicType = new MusicType();
        try(PreparedStatement getById = connection.prepareCall(MusicTypeSql.GetById.QUERY)) {
            getById.setInt(1, id);
            try(ResultSet rs = getById.executeQuery()) {
                if (rs.next()) {
                    musicType.setName(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return musicType;
    }

    @Override
    public void add(MusicType model) {
        try(PreparedStatement add  = connection.prepareStatement(MusicTypeSql.ADD.QUERY)) {
            add.setString(1, model.getName());
            add.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(MusicType model) {
        try(PreparedStatement update  = connection.prepareStatement(MusicTypeSql.UPDATE.QUERY)) {
            update.setString(1, model.getName());
            update.setInt(2, model.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement delete  = connection.prepareStatement(MusicTypeSql.DELETE.QUERY)) {
            delete.setInt(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Integer> getUsersId(String musicType) {
        List<Integer> users = new ArrayList<>();
        try(PreparedStatement getUsers = connection.prepareStatement(MusicTypeSql.GetByName.QUERY)) {
            getUsers.setString(1, musicType);
           try(ResultSet rs = getUsers.executeQuery()) {
               while (rs.next()) {
                   User user = new User();
                   user.setId(rs.getInt("id"));
                   user.setName(rs.getString("name"));
               }
           }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return users;
    }

    enum MusicTypeSql {
        GetById("SELECT * FROM music_type WHERE id = ?"),
        GetByName("SELECT * FROM music_type WHERE name = ?"),
        DELETE("DELETE FROM music_type WHERE id = ?"),
        ADD("INSERT INTO music_type(name) VALUES(?)"),
        UPDATE("UPDATE music_type SET name = ? WHERE id = ?");

        String QUERY;

        MusicTypeSql(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
