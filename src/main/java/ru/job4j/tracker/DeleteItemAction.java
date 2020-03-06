package ru.job4j.tracker;

public class DeleteItemAction implements UserAction {
    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("==== Starting delete Item ====");
        String id = input.askStr("Enter existing ID Item: ");
        if (tracker.delete(id)) {
            System.out.println("Item successfully deleted");
        } else {
            System.out.println("error");
        }
        return true;
    }
}
