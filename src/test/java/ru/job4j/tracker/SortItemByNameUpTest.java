package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortItemByNameUpTest {
    @Test
    public void sortItemByNameUpTest() {
        List<Item> newList = new ArrayList<>();
        Item item5 = new Item("Anton");
        Item item1 = new Item("Kent");
        Item item2 = new Item("Cesar");
        Item item3 = new Item("Duke");
        Item item4 = new Item("Evgen");
        newList.add(item1);
        newList.add(item2);
        newList.add(item3);
        newList.add(item4);
        newList.add(item5);
        newList.sort(new SortItemByNameUp());
        assertThat(Arrays.asList(item5, item2, item3, item4, item1), is(newList));
    }
}
