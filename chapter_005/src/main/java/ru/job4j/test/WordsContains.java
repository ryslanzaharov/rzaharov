package ru.job4j.test;

import java.util.*;

public class WordsContains {

    public boolean containsLetter(String word1, String word2) {
        boolean sameLetters = false;
        if (word1.length() == word2.length()) {
            Map<String, Integer> map = new HashMap<>();
            String [] w1 = word1.split("");
            for (String key : w1) {
                Integer val = map.putIfAbsent(key, 1);
                if (val != null)
                    map.put(key, map.get(key) + 1);
            }
            String [] w2 = word2.split("");
            for (String let : w2) {
                if (map.get(let) != null && map.get(let) > 1) {
                    map.put(let, map.get(let) - 1);
                }
                else if (map.get(let) == 1)
                    map.remove(let);
            }
            System.out.println(map);
            if (map.isEmpty())
                sameLetters = true;
        }
                return sameLetters;

    }

}
