package rzaharov.servlets.cop.musiccourt.dao.postgres;

import rzaharov.servlets.cop.musiccourt.dao.FactoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersMusicTypesDatabase {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();

    private final Connection connection = factoryDAO.getConn();

    public void addUserMusicTypes(Integer user_id, Integer music_type_id) {
        try(PreparedStatement add = connection.prepareStatement(UsersMusicTypesSql.ADD.QUERY)) {
            add.setInt(1, user_id);
            add.setInt(2, music_type_id);
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getUserMusicTypes(Integer user_id) {
        List<Integer> list = new ArrayList<>();
        try(PreparedStatement get = connection.prepareStatement(UsersMusicTypesSql.GetById.QUERY)) {
            get.setInt(1, user_id);
            ResultSet rs = get.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("music_type_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateUserMusicTypes(Integer user_id, Integer music_type_id) {
        try(PreparedStatement update = connection.prepareStatement(UsersMusicTypesSql.ADD.QUERY)) {
            PreparedStatement delete = connection.prepareStatement(UsersMusicTypesSql.DELETE.QUERY);
            delete.executeUpdate();
            update.setInt(1, music_type_id);
            update.setInt(2, user_id);
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserMusicTypes(Integer user_id) {
        try(PreparedStatement delete = connection.prepareStatement(UsersMusicTypesSql.DELETE.QUERY)) {
            delete.setInt(1, user_id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    enum UsersMusicTypesSql {
        GetById("SELECT * FROM users_music_types WHERE user_id = ?"),
        DELETE("DELETE FROM users_music_types WHERE user_id = ?"),
        ADD("INSERT INTO users_music_types(user_id, music_type_id) VALUES(?, ?)"),
        UPDATE("UPDATE users_music_types SET music_type_id = ? WHERE user_id = ?");

        String QUERY;

        UsersMusicTypesSql(String QUERY) {
            this.QUERY = QUERY;
        }
        }
}
