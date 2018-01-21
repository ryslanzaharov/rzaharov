package ru.job4j.multithreading.monitoresynchronizy;

import org.junit.Test;
import ru.job4j.multithreading.monitoresynchronizy.searchdirect.FilesApp;

import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParallerSearchTest {
    @Test
    public void whenWordSearchInFiles() {
        final String path = "D:\\dir\\";
        final String word = "con";
        List<String> res = new ArrayList<>();
        List<String> ext = new ArrayList<>(
                Arrays.asList(
                        "txt", "xml"
                )
        );
        ParallerSearch parallerSearch = new ParallerSearch(path, word, ext);
        try {
            res = (parallerSearch.result(path));
            Thread.sleep(100);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        for (String s : res)
            System.out.println(s);
    }

}