package ru.job4j.bank;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.*;

public class AccountsTest {
    @Test
    public void whenAddUser() {
        Accounts account = new Accounts();
        User user = new User("Ivan", 881002357);
        User user1 = new User("Egor", 881002351);
        User user2 = new User("Roma", 881002352);
        List<Accounts> list = new ArrayList<>();
        List<Accounts> list1 = new ArrayList<>();
        List<Accounts> list2 = new ArrayList<>();
        account.addUser(user);
        account.addUser(user1);
        account.addUser(user2);
        Map<User, List<Accounts>> result = account.getUserAndAccounts();
        Map<User, List<Accounts>> expected = new HashMap<>();
        expected.put(user,list);
        expected.put(user1,list1);
        expected.put(user2,list2);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteUser() {
        Accounts account = new Accounts();
        User user = new User("Ivan", 881002357);
        User user1 = new User("Egor", 881002351);
        User user2 = new User("Roma", 881002352);
        List<Accounts> list = new ArrayList<>();
        List<Accounts> list1 = new ArrayList<>();
        List<Accounts> list2 = new ArrayList<>();
        account.addUser(user);
        account.addUser(user1);
        account.addUser(user2);
        account.deleteUser(user2);
        Map<User, List<Accounts>> result = account.getUserAndAccounts();
        Map<User, List<Accounts>> expected = new HashMap<>();
        expected.put(user,list);
        expected.put(user1,list1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddAccountToUser() {
        Accounts account = new Accounts();
        Accounts accounts = new Accounts(11111, 2340956);
        Accounts accounts1 = new Accounts(22222, 2340952);
        Accounts accounts2 = new Accounts(33333, 2340953);
        User user = new User("Ivan", 881002357);
        User user1 = new User("Egor", 881002351);
        User user2 = new User("Roma", 881002352);
        List<Accounts> list = new ArrayList<>(
                Arrays.asList(accounts)
        );
        List<Accounts> list1 = new ArrayList<>(
                Arrays.asList(accounts1)
        );
        List<Accounts> list2 = new ArrayList<>(
                Arrays.asList(accounts2)
        );
        account.addUser(user);
        account.addUser(user1);
        account.addUser(user2);
        account.addAccountToUser(user, accounts);
        account.addAccountToUser(user1, accounts1);
        account.addAccountToUser(user2, accounts2);
        Map<User, List<Accounts>> result = account.getUserAndAccounts();
        Map<User, List<Accounts>> expected = new HashMap<>();
        expected.put(user,list);
        expected.put(user1,list1);
        expected.put(user2,list2);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        Accounts account = new Accounts();
        Accounts accounts = new Accounts(11111, 2340956);
        Accounts accounts1 = new Accounts(22222, 2340952);
        User user = new User("Ivan", 881002357);
        User user1 = new User("Egor", 881002351);
        List<Accounts> list = new ArrayList<>(
                Arrays.asList(accounts, accounts1)
        );
        List<Accounts> list1 = new ArrayList<>(
                Arrays.asList(accounts1)
        );
        account.addUser(user);
        account.addUser(user1);
        account.addAccountToUser(user, accounts);
        account.addAccountToUser(user1, accounts1);
        account.deleteAccountFromUser(user, accounts1);
        Map<User, List<Accounts>> result = account.getUserAndAccounts();
        Map<User, List<Accounts>> expected = new HashMap<>();
        list.remove(accounts1);
        expected.put(user,list);
        expected.put(user1,list1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenGetUserAccounts() {
        Accounts account = new Accounts();
        Accounts accounts = new Accounts(11111, 2340956);
        Accounts accounts1 = new Accounts(22222, 2340952);
        User user = new User("Ivan", 881002357);
        User user1 = new User("Egor", 881002351);
        account.addUser(user);
        account.addUser(user1);
        account.addAccountToUser(user, accounts);
        account.addAccountToUser(user1, accounts1);
        List<Accounts> result = account.getUserAccounts(user);
        List<Accounts> expected = new ArrayList<>(
                Arrays.asList(accounts)
        );
        assertThat(result, is(expected));
    }

    @Test
    public void whenTransferMoney() {
        Accounts account = new Accounts();
        Accounts accounts = new Accounts(11111, 2340956);
        Accounts accounts1 = new Accounts(22222, 2340952);
        Accounts accounts2 = new Accounts(33333, 2340953);
        User user = new User("Ivan", 881002357);
        User user1 = new User("Egor", 881002351);
        User user2 = new User("Roma", 881002352);
        account.addUser(user);
        account.addUser(user1);
        account.addUser(user2);
        account.addAccountToUser(user, accounts);
        account.addAccountToUser(user1, accounts1);
        account.addAccountToUser(user2, accounts2);
        boolean result = account.transferMoney(user, accounts, user1, accounts1, 111);
        assertThat(result, is(true));
    }
}
