package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HbmTrackerTest {
    @Test //метод add
    public void whenAddNewItemThenTrackerHasSameItem() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("test1");
        hbmTracker.add(item);
        Item result = hbmTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }
    @Test // проверяем метод поиска findAll
    public void whenAdd5ItemsAndUniqueIDForEachThenTrue() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test4");
        Item item4 = new Item("test2");
        Item item5 = new Item("test2");
        hbmTracker.add(item1);
        hbmTracker.add(item2);
        hbmTracker.add(item3);
        hbmTracker.add(item4);
        hbmTracker.add(item5);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        expected.add(item4);
        expected.add(item5);
        List<Item> result = hbmTracker.findAll();
        assertThat(expected, is(result));
    }
    @Test // проверяем метод поиска по имени
    public void whenAdd5ItemsAnd3SameNamesThenTrackerHas3ItemBySameName() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test4");
        Item item4 = new Item("test2");
        Item item5 = new Item("test2");
        hbmTracker.add(item1);
        hbmTracker.add(item2);
        hbmTracker.add(item3);
        hbmTracker.add(item4);
        hbmTracker.add(item5);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item4);
        expected.add(item5);
        List<Item> result = hbmTracker.findByName("test2");
        assertThat(expected, is(result));
    }

    @Test // проверяем метод findById
    public void whenAdd5ItemsAddThenTheyAllInTracker() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test4");
        Item item4 = new Item("test2");
        Item item5 = new Item("test2");
        hbmTracker.add(item1);
        hbmTracker.add(item2);
        hbmTracker.add(item3);
        hbmTracker.add(item4);
        hbmTracker.add(item5);
        Item result = hbmTracker.findById(item5.getId());
        assertThat(item5, is(result));
    }
    @Test // метод replace
    public void whenReplace() {
        HbmTracker hbmTracker = new HbmTracker();
        Item bug = new Item("Bug");
        bug = hbmTracker.add(bug);
        Integer id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        hbmTracker.replace(id, bugWithDesc);
        assertThat(hbmTracker.findById(id).getName(), is("Bug with description"));
    }
    @Test // тест delete
    public void whenDelete() {
        HbmTracker hbmTracker = new HbmTracker();
        Item bug = new Item("Bug");
        hbmTracker.add(bug);
        Integer id = bug.getId();
        hbmTracker.delete(id);
        assertNull(hbmTracker.findById(id));
    }
}
