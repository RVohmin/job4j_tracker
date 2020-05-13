package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class FindItemByNameAction implements UserAction {
    @Override
    public String name() {
        return "Поиск заявки по имени";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("==== Начинаем поиск заявки по имени ====");
        String name = input.askStr("Введите имя заявки для поиска: ");
        List<Item> finding = tracker.findByName(name);
        if (finding.size() > 0) {
            for (Item item : finding) {
                System.out.println("Найдено " + item.getName() + " " + item.getId());
            }
        } else {
            System.out.println("Ничего не найдено");
        }
        return true;
    }
}
