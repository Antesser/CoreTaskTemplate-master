package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getMySQLConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE user1 (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age SMALLINT NOT NULL, PRIMARY KEY (id))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Table with such name already exists.");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE user1");
        } catch (SQLException e) {
            System.out.println("Table with such name doesn't exist.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO user1 (name, lastName, age) VALUES (?, ?, ?)")) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM user1 WHERE id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user1");
             ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM user1")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM user1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}