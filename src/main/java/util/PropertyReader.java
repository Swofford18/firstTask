package util;

import dao.UserDaoFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties read() {

        Properties prop = new Properties();
        try (InputStream stream = UserDaoFactory.class.getClassLoader().getResourceAsStream("dao.properties")) {

            prop.load(stream);
            return prop;
        }
        catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return null;
    }
}
