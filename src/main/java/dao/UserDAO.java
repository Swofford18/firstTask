package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    boolean updateUser(User user);
    boolean createUser(User user);
    boolean deleteUser(String name);
    List<User> getAllUsers();
    boolean validate(String name, String password);
    String getRoleByName(String name);
}
