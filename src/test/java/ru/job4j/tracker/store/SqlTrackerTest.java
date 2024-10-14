package ru.job4j.tracker.store;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenReplaceName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item origin = new Item("item");
        tracker.add(origin);
        Item fresh = new Item("replace");
        int id = origin.getId();
        tracker.replace(id, fresh);
        assertThat(tracker.findById(id).getName()).isEqualTo(fresh.getName());
        assertThat(tracker.findById(id).getCreated()).isEqualTo(origin.getCreated());
    }

    @Test
    public void whenDelete() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        int id = item.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id)).isNull();
    }

    @Test
    public void whenFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item one = new Item("one");
        Item two = new Item("two");
        Item three = new Item("three");
        List<Item> all = List.of(one, two, three);
        all.forEach(tracker :: add);
        List<Item> founded = tracker.findAll();
        assertThat(founded).hasSize(3);
        assertThat(founded).containsAll(all);
    }

    @Test
    public void whenFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item one = new Item("one");
        Item two = new Item("one");
        Item three = new Item("three");
        List<Item> all = List.of(one, two, three);
        all.forEach(tracker :: add);
        List<Item> founded = tracker.findByName("one");
        assertThat(founded).hasSize(2);
        assertThat(founded).containsAll(List.of(new Item("one"), new Item("one")));
    }

    @Test
    public void whenFindById() {
        SqlTracker tracker = new SqlTracker(connection);
        Item one = new Item("one");
        Item two = new Item("two");
        Item three = new Item("three");
        List<Item> all = List.of(one, two, three);
        all.forEach(tracker :: add);
        assertThat(tracker.findById(one.getId())).isEqualTo(one);
    }
}
