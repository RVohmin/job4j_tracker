package ru.job4j.tracker;

import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {
    public Connection init() {
        try (FileInputStream in = new FileInputStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddThen1() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Peter"));
            assertThat(tracker.findByName("Peter").size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReplace() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("Peter"));
            String id = item.getId();
            tracker.replace(id, new Item("Alex"));
            assertThat(tracker.findByName("Alex").size(), is(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("Peter"));
            String id = item.getId();
            tracker.delete(id);
            assertNull(tracker.findById(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("Peter"));
            assertThat(tracker.findAll().size(), is(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByName() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("Peter"));
            assertEquals(tracker.findByName(item.getName()), List.of(item));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("Peter"));
            String id = item.getId();
            assertEquals(tracker.findById(id), item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}