package ru.job4j.multithreading.users;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("storage")
    private final Map<Integer, User> storage = new HashMap<>();

    public boolean add (User user) {
        synchronized(storage) {
            boolean isAdd = false;
            if (!storage.containsKey(user.getId())) {
                storage.put(user.getId(), user);
                isAdd = true;
            }
            return isAdd;
        }
    }

    public boolean update(User user) {
        synchronized (storage) {
            boolean isUpdate = false;
            if (storage.containsKey(user.getId())) {
                User prevUser = storage.get(user.getId());
                prevUser.setAmount(user.getAmount() + prevUser.getAmount());
                storage.put(prevUser.getId(), prevUser);
                isUpdate = true;
            }
            return isUpdate;
        }
    }

    public boolean delete(User user) {
        synchronized (storage) {
            boolean isDelete = false;
            if (storage.containsKey(user.getId())) {
                storage.remove(user.getId());
                isDelete = true;
            }
            return isDelete;
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (storage) {
            boolean isTransfer = false;
            if (storage.containsKey(fromId) && storage.containsKey(toId)) {
                User from = storage.get(fromId);
                User to = storage.get(toId);
                if (from.getAmount() >= amount) {
                    synchronized (from) {
                        synchronized (to) {
                            from.setAmount(from.getAmount() - amount);
                            storage.put(fromId, from);
                            to.setAmount(to.getAmount() + amount);
                            storage.put(toId, to);
                            isTransfer = true;
                        }
                    }
                }

            }
            return isTransfer;
        }
    }
}
