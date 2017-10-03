package ru.job4j.CollectionsFramework;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;
/**
 * Test.
 *
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @since 03.10.18.
 */
public class CollectionsTimeTest {
    @Test
    public void timeAddingItemsArrayList() {
        CollectionsTime collectionsTime = new CollectionsTime();

        ArrayList<String> arrayList = new ArrayList<>();
        long arrayAdd = collectionsTime.add(arrayList, 300000);
        long arrayRemove = collectionsTime.delete(arrayList, 200000);
        LinkedList<String> linkedList = new LinkedList<>();
        long linkedAdd = collectionsTime.add(linkedList, 300000);
        long linkedRemove = collectionsTime.delete(linkedList, 200000);
        TreeSet<String> treeSet = new TreeSet<>();
        long treeAdd = collectionsTime.add(treeSet, 300000);
        long treeRemove = collectionsTime.delete(treeSet, 200000);
        System.out.println(String.format("arrayAdd = %s,arrayRemove = %s,linkedAdd = %s,linkedRemove = %s,treeAdd = %s,treeRemove = %s",
                arrayAdd, arrayRemove,linkedAdd, linkedRemove,treeAdd, treeRemove));
    }

}
