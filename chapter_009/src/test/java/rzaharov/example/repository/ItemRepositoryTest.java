package rzaharov.example.repository;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import rzaharov.example.database.DBManager;
import rzaharov.example.models.Item;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemRepositoryTest {

    @BeforeClass
    public static void setUp() {
        DBManager.getInstance().buildSessionFactory();
    }

    @Test
    public void whenAddNewUser() {
        ItemRepository itemRepository = ItemRepository.getInstance();
        Item item = new Item();
        item.setItemName("itemName");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        itemRepository.add(item);
        assertThat(item.getId() != 0, is(true));
    }

    @Test
    public void whenUpdateUserAndGetById() {
        ItemRepository itemRepository = ItemRepository.getInstance();
        Item item = new Item();
        item.setItemName("itemName");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        itemRepository.add(item);
        assertThat(item.getId() != 0, is(true));
        item.setItemName("updateName");
        itemRepository.update(item);
        assertThat(item.getItemName(), is(itemRepository.getById(item.getId()).getItemName()));
    }

    @Test
    public void delete() {
        ItemRepository itemRepository = ItemRepository.getInstance();
        Item item = new Item();
        item.setItemName("itemName");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        itemRepository.add(item);
        assertThat(item.getId() != 0, is(true));
        itemRepository.delete(item);
        assertThat(itemRepository.getById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenGetAllUsers() {
        List<Item> users = ItemRepository.getInstance().getAll();
        assertThat(users.size() > 0, is(true));
    }

    @AfterClass
    public static void tearDown() {
        DBManager.getInstance().closeSessionFactory();
    }


}