package ru.rzaharov.repository;

import org.hibernate.Session;
import ru.rzaharov.database.DBManager;
import ru.rzaharov.models.Engine;

import java.util.List;

public class EngineRepository extends CommonRepository<Engine> {

    private DBManager dbManager;
    private static final EngineRepository instance = new EngineRepository();

    private EngineRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public static EngineRepository getInstance() {
        return instance;
    }

    public void add(Engine engine) {
        super.execute(Session::save, engine);
    }

    public void update(Engine engine) {
        super.execute(Session::update, engine);
    }

    public void delete(Engine engine) {
        super.execute(Session::delete, engine);
    }

    public Engine getById(int id) {
        return super.getById(session -> session.get(Engine.class, id));
    }

    public List<Engine> getAll() {
        return super.getAll(session -> session.createQuery("from Engine").list());
    }
}
