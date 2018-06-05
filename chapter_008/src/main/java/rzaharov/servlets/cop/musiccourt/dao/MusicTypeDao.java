package rzaharov.servlets.cop.musiccourt.dao;

import rzaharov.servlets.cop.musiccourt.models.MusicType;
import rzaharov.servlets.cop.musiccourt.models.User;

import java.util.List;

public interface MusicTypeDao extends DAO<MusicType> {

    public List<Integer> getUsersId(String musicType);
}
