package ru.job4j.generic;

import java.util.Arrays;
import java.util.List;

public class UserStore<T extends Base> extends AbstractStore{

    public UserStore(int size) {
        super(size);
    }


}
