package rzaharov.servlets.cop.musiccourt.dao;

import rzaharov.servlets.cop.musiccourt.models.Address;

public interface AddressDao extends DAO<Address> {

    public Address getUser(String address);
}
