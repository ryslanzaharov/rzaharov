package ru.job4j.set;

import java.util.Arrays;

/* Простая реализация HashSet'а методом открытой адресации для интов */
   class OpenHashSet {
    int FREE = Integer.MIN_VALUE;
    int size;
    int[] keys;

    /* Конструктор */
    OpenHashSet(int size) {
        this.size = Math.max(3 * size / 2, size) + 1;
        keys = new int[this.size];
        Arrays.fill(keys, FREE);
    }

    /* Добавляет элемент в множество */
    boolean add(int x) {
        for (int i = index(hash(x)); ; i++) {
            System.out.println(i);
            if (i == size) i = 0;
            if (keys[i] == x) return false;
            if (keys[i] == FREE) {
                keys[i] = x;
                return true;
            }
        }
    }

    /* Проверяет, содержится ли x в множестве */
    boolean contains(int x) {
        for (int i = index(hash(x)); ; i++) {
            if (i == size) i = 0;
            if (keys[i] == x) return true;
            if (keys[i] == FREE) return false;
        }
    }

    /* хэш-функция (для других типов следует изменить) */
    int hash(int x) {
        int xs = (x >> 15) ^ x;
        System.out.println("hash "+xs);
        return xs;
    }

    /* возвращает отступ для данного значения хэш-функции */
    int index(int hash) {
        System.out.println(hash);
        int x = Math.abs(hash) % size;
        System.out.println(x);
        return x;
    }

    @Override
    public String toString() {
        return "OpenHashSet{" +
                "FREE=" + FREE +
                ", size=" + size +
                ", keys=" + Arrays.toString(keys) +
                '}';
    }

    public static void main(String[] args) {
        OpenHashSet openHashSet = new OpenHashSet(12);
//        openHashSet.add(0);
//        openHashSet.add(1);
//        openHashSet.add(3);
//        openHashSet.add(12);
//        openHashSet.add(2);
        openHashSet.add(156535425);
        System.out.println(openHashSet);
        for (int i = 0; i < openHashSet.keys.length; i++) {
            System.out.println(i+"--"+ openHashSet.keys[i]);
        }
    }
}
