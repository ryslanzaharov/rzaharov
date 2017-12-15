package ru.job4j.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Words {

    private String word;
    public Words(String word) {
        this.word = word;
    }

    public boolean containsLetter(String word) {
        boolean sameLetters = true;
                if (this.word.length() == word.length()) {
                        String[] array = this.word.split("");
                        List<String> letters = new ArrayList<>(
                                        Arrays.asList(array)
                                        );
                        for (String let : word.split("")) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Words words = (Words) o;

        return word != null ? containsLetter(word) : words.word == null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        if (word != null) {
        char[] ch = word.toCharArray();
        int arraySize = ch.length;
        for (int i = 0; i < ch.length; i++) {
            result += ch[i];
            result += ((ch[i] - 96) * 27) % arraySize;
        }
        //   result = result % arraySize;
        }

        return result;
    }
}
