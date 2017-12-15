package ru.job4j.test;

public class Words {

    private String word;
    public Words(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Words words = (Words) o;

        return word != null ? word.equals(words.word) : words.word == null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        if (word != null) {
        char[] ch = word.toCharArray();
        int hash = word.charAt(0) - 96;
        int arraySize = ch.length;
        for (int i = 0; i < ch.length; i++) {
            result += ((ch[i] - 96) * 27) % arraySize;
        }}
        return result;
    }
}
