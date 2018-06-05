package rzaharov.servlets.cop.musiccourt.dao.postgres;

import rzaharov.servlets.cop.musiccourt.dao.AddressDao;
import rzaharov.servlets.cop.musiccourt.dao.FactoryDAO;
import rzaharov.servlets.cop.musiccourt.models.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDatabaseDao implements AddressDao {

    /**
     * Connection of database.
     */
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();

    private final Connection connection = factoryDAO.getConn();

    @Override
    public List<Address> getAll() {
        List<Address> addresses = new ArrayList<>();
        Address address;
        try(PreparedStatement select = connection.prepareStatement(AddressSql.GET.QUERY)) {
            ResultSet rs = select.executeQuery();
            while(rs.next()) {
                address = new Address();
                address.setId(rs.getInt("id"));
                address.setCity(rs.getString("city"));
                address.setDistrict(rs.getString("district"));
                address.setStreet(rs.getString("street"));
                address.setHouse(rs.getString("house"));
                address.setApartment(rs.getInt("apartment"));
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    @Override
    public Address getById(Integer id) {
        Address address = new Address();
        try(PreparedStatement select = connection.prepareStatement(AddressSql.GetById.QUERY)) {
            select.setInt(1, id);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
              //  address = new Address();
                address.setId(rs.getInt("id"));
                address.setCity(rs.getString("city"));
                address.setDistrict(rs.getString("district"));
                address.setStreet(rs.getString("street"));
                address.setHouse(rs.getString("house"));
                address.setApartment(rs.getInt("apartment"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void add(Address model) {
        try(PreparedStatement add = connection.prepareStatement(AddressSql.ADD.QUERY)) {
            add.setString(1, model.getCity());
            add.setString(2, model.getDistrict());
            add.setString(3, model.getStreet());
            add.setString(4, model.getHouse());
            add.setInt(5, model.getApartment());
            add.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Address model) {
        try(PreparedStatement update = connection.prepareStatement(AddressSql.UPDATE.QUERY)) {
            update.setString(1, model.getCity());
            update.setString(2, model.getDistrict());
            update.setString(3, model.getStreet());
            update.setString(4, model.getHouse());
            update.setInt(5, model.getApartment());
            update.setInt(6, model.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement delete = connection.prepareStatement(AddressSql.DELETE.QUERY)) {
            delete.setInt(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Address getUser(String address) {
        return null;
    }

    enum AddressSql {
        GetById("SELECT * FROM address WHERE id = ?"),
        GET("SELECT * FROM address"),
        DELETE("DELETE FROM address WHERE id = ?"),
        ADD("INSERT INTO address(city, district, street, house, apartment) VALUES(?, ?, ?, ?, ?)"),
        UPDATE("UPDATE address SET city = ?, district = ?, street = ?, house = ?, apartment = ? WHERE id = ?");

        String QUERY;

        AddressSql(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
