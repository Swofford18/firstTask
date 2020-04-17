package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection c) {
        connection = c;
    }

    public boolean updateUser(User user) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("UPDATE users SET age=" + user.getAge() + ", " +
                    "password='" + user.getPassword() + "' " +
                    "WHERE name='" + user.getName() + "'");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteUser(String name) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users WHERE name='" + name +"'");
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean createUser(User user) {

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO users (age, name, password) VALUES (" +
                    user.getAge() + ", '" +
                    user.getName() + "', '" +
                    user.getPassword() + "')");
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        try(Statement stmt = connection.createStatement()) {

            stmt.execute("SELECT * FROM users");
            ResultSet result = stmt.getResultSet();

            do {
                result.next();
                list.add(new User(
                        result.getLong("id"),
                        result.getInt("age"),
                        result.getString("name"),
                        result.getString("password")
                ));
            } while (!(result.isLast()));
            result.close();
        }
        catch (SQLException e) {
            return null;
        }

        return list;
    }
}
