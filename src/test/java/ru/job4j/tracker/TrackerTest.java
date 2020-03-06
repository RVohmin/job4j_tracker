package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;


public class TrackerTest {

    /**
     * тест проверяет добавление в массив tracker, использует поиск по Id
     */
    @Test //метод add
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }
    @Test // проверяем метод поиска findAll
    public void whenAdd5ItemsAndUniqueIDForEachThenTrue() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test4");
        Item item4 = new Item("test2");
        Item item5 = new Item("test2");

        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        expected.add(item4);
        expected.add(item5);

        List<Item> result = tracker.findAll();
        assertThat(expected, is(result));
    }
    @Test // проверяем метод поиска по имени
    public void whenAdd5ItemsAnd3SameNamesThenTrackerHas3ItemBySameName() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test4");
        Item item4 = new Item("test2");
        Item item5 = new Item("test2");

        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item4);
        expected.add(item5);
        List<Item> result = tracker.findByName("test2");
        assertThat(expected, is(result));
    }

    @Test // проверяем метод findById
    public void whenAdd5ItemsAddThenTheyAllInTracker() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test4");
        Item item4 = new Item("test2");
        Item item5 = new Item("test2");

        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        Item result = tracker.findById(item5.getId());
        assertThat(item5, is(result));
    }

    @Test // метод replace
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test // тест delete
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }
}
