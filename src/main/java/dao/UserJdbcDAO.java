package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO(Connection c) {
        connection = c;
    }

    @Override
    public String getRoleByName(String name) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT FROM users WHERE name='" + name +"'");
            ResultSet result = stmt.getResultSet();
            result.next();
            String role = result.getString("role");

            return role;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validate(String name, String password) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT FROM users WHERE name='" + name +"'");
            ResultSet result = stmt.getResultSet();
            result.next();
            String pass = result.getString("password");

            return pass.equals(password);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("UPDATE users SET age=" + user.getAge() + ", " +
                    "password='" + user.getPassword() + "', " +
                    "role='" + user.getRole() + "' " +
                    "WHERE name='" + user.getName() + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String name) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users WHERE name='" + name +"'");
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createUser(User user) {

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO users (age, name, password, role) VALUES (" +
                    user.getAge() + ", '" +
                    user.getName() + "', '" +
                    user.getPassword() + "', '"+
                    user.getPassword() + "')");
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return null;
        }

        return list;
    }
}
