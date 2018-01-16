package ru.job4j.multithreading.users;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

     Map<Integer, User> storage = new HashMap<>();

    public boolean add (User user) {
        boolean isAdd = false;
        if (!storage.containsKey(user.getId())) {
            storage.put(user.getId(), user);
            isAdd = true;
        }
        return isAdd;
    }

    public boolean update(User user) {
        boolean isUpdate = false;
        if (storage.containsKey(user.getId())) {
            User prevUser = storage.get(user.getId());
            user.setAmount(user.getAmount() + prevUser.getAmount());
            storage.put(user.getId(), user);
            isUpdate = true;
        }
        return isUpdate;
    }

    public boolean delete(User user) {
        boolean isDelete = false;
        if (storage.containsKey(user.getId())) {
            storage.remove(user.getId());
            isDelete = true;
        }
        return isDelete;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        boolean isTransfer = false;
        if (storage.containsKey(fromId) && storage.containsKey(toId)) {
            User from = storage.get(fromId);
            User to = storage.get(toId);
            if (from.getAmount() > amount) {
                from.setAmount(from.getAmount() - amount);
                storage.put(fromId, from);
                to.setAmount(to.getAmount() + amount);
                storage.put(toId, to);
                isTransfer = true;
            }
        }
        return isTransfer;
    }
}
