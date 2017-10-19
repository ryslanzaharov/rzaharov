package ru.job4j.generic;

import org.omg.CORBA.BAD_CONTEXT;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStore<T extends Base>  implements Store<T>  {

    private SimpleArray store;

    public AbstractStore(int size) {
        this.store = new SimpleArray(size);
    }
    @Override
    public T add(T model) {
        store.add(model);
        return model;
    }

    @Override
    public T update(T model) {
        store.update(model.getId(), model);
        return model;
    }

    @Override
    public boolean delete(String id) {
        store.delete(Integer.parseInt(id));
        return true;
    }

    @Override
    public T get(int id) {
        return (T)store.get(id);
    }

    @Override
    public List getAll() {
        return Arrays.asList(store.getObjects());
    }
}
