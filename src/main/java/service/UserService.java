package service;

import dao.UserDaoFactory;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public String getRoleByName(String name) {
        return new UserDaoFactory().createDAO().getRoleByName(name);
    }

    public boolean validate(String name, String password) {
        return new UserDaoFactory().createDAO().validate(name, password);
    }

    public boolean updateUser(User user) {
        return new UserDaoFactory().createDAO().updateUser(user);
    }

    public boolean deleteUser(String name) {
        return new UserDaoFactory().createDAO().deleteUser(name);
    }

    public boolean createUser(User user) {
        return new UserDaoFactory().createDAO().createUser(user);
    }

    public List<User> getAllUsers() {
        return new UserDaoFactory().createDAO().getAllUsers();
    }
}
