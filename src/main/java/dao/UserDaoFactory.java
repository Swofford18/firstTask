package dao;

import org.hibernate.SessionFactory;
import util.DBHelper;
import util.PropertyReader;
import java.util.Properties;

public class UserDaoFactory {

    public UserDAO createDAO() {

        Properties properties = PropertyReader.read();

        String typeOfDAO = properties.getProperty("daotype");

        switch(typeOfDAO) {
            case "UserHibernateDAO" : {
                DBHelper dbHelper = DBHelper.getInstance();
                SessionFactory factory = dbHelper.getSessionFactory();
                return new UserHibernateDAO(factory.openSession());
            }
            case "UserJdbcDAO" : return new UserJdbcDAO(DBHelper.getInstance().getConnection());
        }
        return null;
    }
}
