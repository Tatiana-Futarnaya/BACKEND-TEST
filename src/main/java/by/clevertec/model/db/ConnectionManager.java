package by.clevertec.model.db;

import by.clevertec.constant.AppConstant;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection connection;
    private static Properties properties;

    static {
        InputStream in = ConnectionManager.class
                .getClassLoader()
                .getResourceAsStream(AppConstant.DB_PROPERTIES_FILE);
        properties = PropertiesManager.load(in);
        try {
            Class.forName(properties.getProperty(AppConstant.DRIVER_PROPERTY));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if(connection==null || connection.isClosed()){
            connection= DriverManager.getConnection(properties.getProperty(AppConstant.URL_PROPERTY),properties);
        }
        return connection;
    }
}
