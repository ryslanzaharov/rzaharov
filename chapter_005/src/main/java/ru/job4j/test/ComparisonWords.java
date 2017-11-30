package ru.job4j.test;

import com.sun.deploy.util.ArrayUtil;

import java.util.*;

public class ComparisonWords {

    public boolean containsLetter(String word1, String word2) {
        boolean sameLetters = true;
        if (word1.length() == word2.length()) {
            String[] array = word1.split("");
            List<String> letters = new ArrayList<>(
                    Arrays.asList(array)
            );
            for (String let : word2.split("")) {
                if (letters.contains(let))
                    letters.remove(let);
                else
                    sameLetters = false;
            }
        }
        else
            sameLetters = false;
        return sameLetters;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ComparisonWords that = (ComparisonWords) o;
//        return words != null ? words.equals(that.words) : that.words == null;
//    }
//
//
//    @Override
//    public int hashCode() {
//        char[] arrays = this.words.toCharArray();
//        int result = 0;
//        for (int i = 0; i < arrays.length; i++) {
//            result += (31 * (arrays[i] - 96) * i) % arrays.length;
//        }
//        return result;
//    }
}
