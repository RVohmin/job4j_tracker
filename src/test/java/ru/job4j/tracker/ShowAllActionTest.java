package ru.job4j.tracker;

import net.sf.saxon.functions.StringJoin;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShowAllActionTest {
    @Test
    public void whenCheckOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store memTracker = new MemTracker();
        Item item = new Item("fix bug");
        memTracker.add(item);
        ShowAllAction act = new ShowAllAction();
        act.execute(new StubInput(new String[] {}), memTracker);
        String expect = "==== Показать все заявки ===="
                + System.lineSeparator()
                + item.getId() + " " + item.getName()
                + System.lineSeparator();
        assertEquals(expect.trim(), new String(out.toByteArray()).trim());
//        System.setOut(def);
    }
}
