package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 */
public class JDBCPostgresConnection {
    private static String user;
    private static String password;
    private static String host;
    private static String port;
    private static String database;

    /**
     * Setups DB connection configured in getProperties();
     *
     * @return - returns connection with database
     */

    public static Connection getConnection()  {
        Connection connection = null;
        getProperties();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                        "jdbc:postgresql://"
                                + host
                                + ":"
                                + port + "/"
                                + database
                        , user
                        , password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    private static void getProperties() {
        try (InputStream input = JDBCPostgresConnection.class.
                getClassLoader().
                getResourceAsStream("database.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");}
            //load a properties file from class path, inside static method
            prop.load(input);
            //bind props with reserved strings
            host = prop.getProperty("serverName");
            port = prop.getProperty("port");
            database = prop.getProperty("database");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
