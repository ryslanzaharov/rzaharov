package ru.job4j.listinmap;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserConvertTest {
    @Test
    public void whenConvetListInMap() {
        UserConvert userConvert = new UserConvert();
        List<User> list = new ArrayList<>();
        User user = new User(1, "Ivan", "Moscow");
        User user1 =  new User(2, "Petr", "Kiev");
        list.addAll(Arrays.asList(user, user1));
        HashMap<Integer , User> expected = new HashMap<>();
        expected.put(1, user);
        expected.put(2, user1);
        HashMap<Integer, User> result = userConvert.process(list);
        assertThat(result, is(expected));
    }
}
