package ru.job4j.tracker;

public class ShowAllAction implements UserAction {

    @Override
    public String name() {
        return "Показать все заявки";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("==== Показать все заявки ====");
        tracker.findAll();
        return true;
    }
}
