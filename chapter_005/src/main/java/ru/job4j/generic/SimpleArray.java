package ru.job4j.generic;

import java.util.Arrays;

public class SimpleArray<T> {

    private Object[] objects;
    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public Object[] getObjects() {
        return objects;
    }

    public void add(T value) {
        if (this.index < objects.length)
        this.objects[index++] = value;
        else
            throw new ArrayIndexOutOfBoundsException(String.format("error in add"));
    }

    public void update(int position, T value) {
        if (position < objects.length && position >= 0)
        this.objects[position] = value;
        else
            throw new ArrayIndexOutOfBoundsException(String.format("error in update"));
    }

    public Object[] delete(int position) {
        Object [] objects = new Object[index - 1];
        if (position < index) {
            System.arraycopy(this.objects, 0, objects, 0, position );
            System.arraycopy(this.objects, position + 1, objects, position, this.index - (position + 1));

        }
        else
            throw new ArrayIndexOutOfBoundsException(String.format("error in delete"));
        this.objects = objects;
        return objects;
    }

    public T get(int position) {
        if (position < this.objects.length)
        return (T) this.objects[position];
        else
            throw new ArrayIndexOutOfBoundsException(String.format("error in get"));
    }

}
