package ru.job4j.polymorphism;

public class StartUi {
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDID = "4";
    private static final String FINDNAME = "5";
    private static final String EXIT = "6";

    private Input input;
    private Tracker tracker;

    public StartUi(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        for (int i = 0; i < input.getAnswers().length; i++) {
            if (input.getAnswers()[i].equals(ADD)) {
                tracker.add(new Item(input.getAnswers()[++i], input.getAnswers()[++i]));
            } else if (input.getAnswers()[i].equals(SHOW)) {
                tracker.getAll();
            } else if (input.getAnswers()[i].equals(EDIT)) {
                tracker.edit(Integer.parseInt(input.getAnswers()[++i]), input.getAnswers()[++i], input.getAnswers()[++i]);
            } else if (input.getAnswers()[i].equals(DELETE)) {
                tracker.delete(Integer.parseInt(input.getAnswers()[++i]));
            } else if (input.getAnswers()[i].equals(FINDID)) {
                tracker.findById(input.getAnswers()[++i]);
            } else if (input.getAnswers()[i].equals(FINDNAME)) {
                tracker.findByName(input.getAnswers()[++i]);
            } else if (input.getAnswers()[i].equals(EXIT)) {
                return;
            }
        }
    }

}
