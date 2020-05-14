package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindByNameActionTest {
    @Test
    public void whenFindByName() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        MemTracker memTracker = new MemTracker();
        Item item = new Item("fix bug");
        memTracker.add(item);
        FindItemByNameAction act = new FindItemByNameAction();
        act.execute(new StubInput(new String[] {item.getName()}), memTracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("==== Начинаем поиск заявки по имени ====")
                .add("Найдено " + item.getName() + " " + item.getId())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
