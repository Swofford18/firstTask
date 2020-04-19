package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import util.DBHelper;
import java.io.*;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.Properties;

public class UserDaoFactory {
   // public static final String PATH_TO_PROPERTIES = UserDaoFactory.class..getClassLoader().getResourceAsStream("file.txt");


    public UserDAO createDAO() {

        Properties properties = new Properties();

        try (InputStream stream = UserDaoFactory.class.getClassLoader().getResourceAsStream("dao.properties")) {

            properties.load(stream);

            String typeOfDAO = properties.getProperty("daotype");

            switch(typeOfDAO) {
                case "UserHibernateDAO" : {
                    DBHelper dbHelper = DBHelper.getInstance();
                    SessionFactory factory = dbHelper.getSessionFactory();
                    return new UserHibernateDAO(factory.openSession());
                }
                case "UserJdbcDAO" : return new UserJdbcDAO(DBHelper.getInstance().getConnection());
            }

        }
        catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return null;
    }
}
