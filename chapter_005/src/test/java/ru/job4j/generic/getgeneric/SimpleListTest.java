package ru.job4j.generic.getgeneric;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleListTest {

    @Test
    public void whenGetType() {
        Stack simpleList = new Stack(4);
        simpleList.add("test");
       String s =  simpleList.get(0);
        assertThat(s, is("test"));
    }

}