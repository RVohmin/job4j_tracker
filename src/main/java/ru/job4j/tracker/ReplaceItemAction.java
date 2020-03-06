package ru.job4j.tracker;

public class ReplaceItemAction implements UserAction {
    @Override
    public String name() {
        return ("Edit item");
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("==== Starting replace Item ====");
        String id = input.askStr("Enter existing ID Item: ");
        String name = input.askStr("Enter name for new Item: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Item successfully replaced");
        } else {
            System.out.println("error");
        }
        return true;
    }
}
