package ru.job4j.tracker;

public class FindItemByIdAction implements UserAction {
    @Override
    public String name() {
        return "Find item by Id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("==== Starting finding Item by ID ====");
        String id = input.askStr("Enter ID Item for find: ");
        Item finding = tracker.findById(id);
        if (finding != null) {
            System.out.println("Finding " + finding.getName() + " " + finding.getId());
        } else {
            System.out.println("Nothing finding");
        }
        return true;
    }
}
