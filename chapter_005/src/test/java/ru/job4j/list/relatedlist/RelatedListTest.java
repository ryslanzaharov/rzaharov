package ru.job4j.list.relatedlist;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class RelatedListTest {
    @Test
    public void whenAddAndCompareLinkedLists() {
        RelatedList<String> list = new <String>RelatedList();
        list.add("value0");
        list.add("value1");
        list.add("value2");
        list.add("value3");
        list.add("value4");
        list.add("value5");
        list.add("value6");
        assertThat(list.get(2), is("value2"));

    }

}
