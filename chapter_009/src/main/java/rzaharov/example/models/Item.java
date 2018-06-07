package rzaharov.example.models;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author rzaharov
 * @version 0.1
 * @since 07.06.2018
 *
 * Describe item model in system.
 */

public class Item {

    private int id;
    private String itemName;
    private Timestamp created;

    private User author;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
