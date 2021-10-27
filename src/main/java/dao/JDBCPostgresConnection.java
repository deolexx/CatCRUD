package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCPostgresConnection {

    public  static Connection getConnection() {


        Connection connection = null;
        //FileInputStream fis;
        Properties prop = new Properties();
        /*
        try {
            fis = new FileInputStream("src/main/resources/database.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String host = prop.getProperty("serverName");
        String port = prop.getProperty("port");
        String database = prop.getProperty("database");
        */
        String user="postgres";
        String password="2033724";
        String host = "localhost";
        String port = "5432";
        String database ="catCRUD";
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://"
                            + host
                            + ":"
                            + port + "/"
                            + database
                    , user
                    , password);


            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection FAIL");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
