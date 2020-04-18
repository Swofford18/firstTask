package service;

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

    private SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public boolean updateUser(User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).updateUser(user);
    }

    public boolean deleteUser(String name) {
        return new UserHibernateDAO(sessionFactory.openSession()).deleteUser(name);
    }

    public boolean createUser(User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).createUser(user);
    }

    public List<User> getAllUsers() {
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsers();
    }

    private static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserJdbcDAO getUserJdbcDAO() {
        return new UserJdbcDAO(getConnection());
    }
}
