package ru.job4j.tracker;

//import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemTracker implements Store {
    /**
     * Массив для хранения заявок.
     */
    private List<Item> items = new ArrayList<>();

    @Override
    public void init() {
    }

    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId((int) generateId());
        items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки. Так как у заявки нет уникальности полей, имени и описание. Для
     * идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private long generateId() {
        Random rm = new Random();
        return rm.nextInt() + System.currentTimeMillis();
    }

    /**
     * Метод возвращает все элементы массива items урезая его длину (без null элементов items)
     *
     * @return все элементы массива items без null элементов items
     */
    public List<Item> findAll() {
//        for (Item item : items) {
//            System.out.printf("%s %s %n", item.getId(), item.getName());
//        }
        return items;
    }

    /**
     * @param key - имя элемента массива Items
     *
     * @return - Items[] contains all elements with name match key
     */
    public List<Item> findByName(String key) {
        List<Item> listWithKey = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                listWithKey.add(item);
            }
        }
        return listWithKey;
    }

    /**
     * @param id id
     *
     * @return element of Item[] with search id
     */
    public Item findById(Integer id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    private int findIndexByID(Integer id) {
        int index = 0;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * @param id   id
     * @param item item
     */
    public boolean replace(Integer id, Item item) {
        int index = findIndexByID(id);
        if (index != -1) {
            items.set(index, item);
            items.get(index).setId(id);
        }
        return index != -1;
    }

    /**
     * Метод удаления элемента из массива
     *
     * @param id id
     */
    public boolean delete(Integer id) {
        int index = findIndexByID(id);
        if (index != -1) {
            items.remove(index);
        }
        return index != -1;
    }


    @Override
    public void close() {
    }
}
