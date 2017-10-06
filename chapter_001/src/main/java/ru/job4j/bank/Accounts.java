package ru.job4j.bank;

import java.util.*;

public class Accounts {
    // кол. денег.
    private double value;
    //реквизиты.
    private int requisites;
    private Map<User, List<Accounts>> userAccounts = new HashMap<>();
    private User user;
 //   private List<Accounts> accountsList;

    public Accounts() {

    }

    public Accounts(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Map<User, List<Accounts>> getUserAndAccounts() {
        return this.userAccounts;
    }

    public void addUser(User user) {
        List<Accounts> list = new ArrayList<>();
        userAccounts.put(user, list);
    }//- добавление пользователя.

    public void deleteUser(User user) {
        userAccounts.remove(user);
    } //- удаление пользователя.

    public void addAccountToUser(User user, Accounts account) {
        List<Accounts> accountsList = userAccounts.get(user);
        accountsList.add(account);
        userAccounts.put(user, accountsList);
    } //- добавить счёт пользователю.

    public void deleteAccountFromUser(User user, Accounts account) {
        List<Accounts> accountsList = userAccounts.get(user);
        accountsList.remove(account);
        userAccounts.put(user, accountsList);
    } //- удалить один счёт пользователя.

    public List<Accounts> getUserAccounts (User user) {
       return userAccounts.get(user);
    } //- получить список счетов для пользователя.

    public boolean transferMoney (User srcUser, Accounts srcAccount, User dstUser, Accounts dstAccount, double amount) {
        boolean result = false;
        if (srcUser != null && dstUser != null &&  srcAccount != null && dstAccount != null && srcAccount.value >= amount) {
            List<Accounts> srcAccountList = userAccounts.get(srcUser);
            int srcIndex = srcAccountList.indexOf(srcAccount);
            srcAccount.value = srcAccount.value - amount;
            srcAccountList.set(srcIndex, srcAccount);
            userAccounts.put(srcUser, srcAccountList);
            List<Accounts> dstAccountList = userAccounts.get(dstUser);
            int dstIndex = dstAccountList.indexOf(dstAccount);
            dstAccount.value = dstAccount.value + value;
            dstAccountList.set(dstIndex, dstAccount);
            userAccounts.put(dstUser, dstAccountList);
            result = true;
        }
        return result;
    }
    //- метод для перечисления денег с одного счёта на другой счёт:
   // если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.


}
