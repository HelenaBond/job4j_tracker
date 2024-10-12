package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection connection;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    private void init() {
        try (InputStream input = SqlTracker.class.getClassLoader()
                .getResourceAsStream("db/liquibase.properties")) {
            Properties config = new Properties();
            config.load(input);
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

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        var sql = "INSERT INTO items (\"name\", created) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            var timestamp = Timestamp.valueOf(item.getCreated());
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.execute();
            try (var resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        var sql = "UPDATE items SET name = ? WHERE id = ?";
        boolean result = false;
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void delete(int id) {
        var sql = "DELETE FROM items WHERE id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        var sql = "SELECT * FROM items";
        List<Item> itemList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);
            itemList = getList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        String sql = "SELECT * FROM items WHERE name = ?";
        List<Item> itemList = new ArrayList<>();
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, key);
            var resultSet = preparedStatement.executeQuery();
            itemList = getList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        var sql = "SELECT * FROM items WHERE id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            item = getItem(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    private List<Item> getList(ResultSet resultSet) throws SQLException {
        List<Item> itemList = new ArrayList<>();
        while (resultSet.next()) {
            itemList.add(getItem(resultSet));
        }
        return itemList;
    }

    private Item getItem(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getTimestamp("created").toLocalDateTime()
        );
    }
}
