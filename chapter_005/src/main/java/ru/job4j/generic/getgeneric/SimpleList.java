package ru.job4j.generic.getgeneric;

import java.lang.reflect.ParameterizedType;

public class SimpleList<T> {

    Object[] objects;
    int index = 0;

    public SimpleList(int size) {
        this.objects = new Object[size];
        Class<T> t = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            T value = t.newInstance();
            System.out.println("string"+value);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <K> K print(K value) {
        System.out.println(value);
        return value;
    }

    public void add(T value) {
        this.objects[index++] = value;
    }

    public T get(int pos) {
        return (T)objects[pos];
    }


}
