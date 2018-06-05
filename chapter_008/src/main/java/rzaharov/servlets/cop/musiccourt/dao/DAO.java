package rzaharov.servlets.cop.musiccourt.dao;

import rzaharov.servlets.cop.musiccourt.models.Model;

import java.util.List;

public interface DAO<T extends Model> {

    public List<T> getAll();

    public T getById(Integer id);

    public void add(T model);

    public void update(T model);

    public void delete(Integer id);

}
