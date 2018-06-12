package rzaharov.carlist.repository;

import org.hibernate.Session;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.models.Condition;

import java.util.List;

public class ConditionRepository extends CommonRepository<Condition> {

    private DBManager dbManager;
    public static final ConditionRepository instance = new ConditionRepository();

    public ConditionRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public ConditionRepository getInstance() {
        return instance;
    }

    public void add(Condition condition) {
        super.execute(Session::save, condition);
    }

    public void update(Condition condition) {
        super.execute(Session::update, condition);
    }

    public void delete(Condition condition) {
        super.execute(Session::delete, condition);
    }

    public Condition getById(int id) {
        return super.getById(session -> session.get(Condition.class, id));
    }

    public List<Condition> getAll() {
        return super.getAll(session -> session.createQuery("from Condition").list());
    }
}
