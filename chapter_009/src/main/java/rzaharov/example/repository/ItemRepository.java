package rzaharov.example.repository;

import org.hibernate.Session;
import rzaharov.example.database.DBManager;
import rzaharov.example.models.Item;

import java.util.List;

/**
 * @author rzaharov
 * @version 0.1
 * @since 07.06.2018
 */

public class ItemRepository extends CommonRepository<Item> {

    private static final ItemRepository instance = new ItemRepository();
    private DBManager dbManager;
    private ItemRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public static ItemRepository getInstance() {
        return instance;
    }

    public void add(Item item) {
        super.execute(Session::save, item);
    }

    public void update(Item item) {
        super.execute(Session::update, item);
    }

    public void delete(Item item) {
        super.execute(Session::delete, item);
    }

    public Item getById(Integer id) {
        return super.getById(session -> session.get(Item.class, id));
    }

    public List<Item> getAll() {
        return super.getAll(session -> session.createQuery("from Item").list());
    }
}
