package ru.job4j.machine;

public class Menu {
    private UserAct[] userActs = new UserAct[4];

    public Menu() {

    }

    public void fillActions() {
        userActs[1] = this.new AddCoins("Add new coins", 1);
        userActs[2] = this.new Buy("Buy", 2);
        userActs[3] = this.new Exit("Exit", 3);

    }

    public void select(int key, Money money, Input input) {
        this.userActs[key].execute(input, money);
    }
    public void show() {
        for (UserAct userAct : userActs) {
            if (userAct != null)
                System.out.println(userAct.info());
        }
    }

    private class AddCoins extends BaseAct {

        public AddCoins(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Money money) {
            money.addCoins(input);
        }
    }
    private class Buy extends BaseAct {

        public Buy(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Money money) {
            money.buy(Integer.parseInt(input.ask("Add the summ:")));
        }
    }
    private class Exit extends BaseAct {

        public Exit(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Money money) {
            System.exit(0);
        }
    }

}
