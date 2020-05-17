package ru.job4j.tracker;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * job4j_tracker ru.job4j.tracker.SqlTracker
 *
 * @author romanvohmin
 * @since 14.05.2020 09:43
 */
public class SqlTracker implements Store  {
    private Connection cn;

    public void init() {
        try (FileInputStream in = new FileInputStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            Statement st = cn.createStatement();
            String table = "CREATE TABLE IF NOT EXISTS items (item_id serial primary key, item_name varchar(100))";
            st.executeUpdate(table);
            st.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = cn.prepareStatement("INSERT INTO items (item_name) VALUES (?)")) {
            ps.setString(1, item.getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean rsl = false;
        try (PreparedStatement ps = cn.prepareStatement("UPDATE items SET item_name = ? WHERE item_id = ?")) {
            ps.setString(1, item.getName());
            ps.setInt(2, Integer.parseInt(id));
            int rows = ps.executeUpdate();
            rsl = rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        try (PreparedStatement ps = cn.prepareStatement("DELETE FROM items WHERE item_id = ?")) {
            ps.setInt(1, Integer.parseInt(id));
            int rows = ps.executeUpdate();
            rsl = rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM items");
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String name = resultSet.getString("item_name");
                Item item = new Item(name);
                item.setId(String.valueOf(id));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM items WHERE item_name LIKE ?")) {
            st.setString(1, "%" + key + "%");
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String name = resultSet.getString("item_name");
                Item item = new Item(name);
                item.setId(String.valueOf(id));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public Item findById(String id) {
        Item rsl = null;
        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM items WHERE item_id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                int itemId = resultSet.getInt("item_id");
                String name = resultSet.getString("item_name");
                rsl = new Item(name);
                rsl.setId(String.valueOf(itemId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(rsl);
        return rsl;
    }
}
