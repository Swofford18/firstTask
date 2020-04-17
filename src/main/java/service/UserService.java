package service;

import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

public class UserService {

    public boolean updateUser(User user) {
        return getUserDAO().updateUser(user);
    }

    public boolean deleteUser(String name) {
        return getUserDAO().deleteUser(name);
    }

    public boolean createUser(User user) {
        return getUserDAO().createUser(user);
    }

    public List<User> getAllUsers() {
        return getUserDAO().getAllUsers();
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

    private static UserDAO getUserDAO() {
        return new UserDAO(getConnection());
    }
}
