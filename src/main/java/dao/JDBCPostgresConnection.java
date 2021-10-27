package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCPostgresConnection {
    //  Database credentials
    public static void main(String[] args) {


        Connection connection = null;

        Properties prop = new Properties();
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String host = prop.getProperty("serverName");
        String port = prop.getProperty("port");
        String database = prop.getProperty("database");


        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/catCRUD", "" + user + "", "" + password + "");
          /*  connection1 = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:"
                            + prop.getProperty("serverName")
                            + ":"
                            + prop.getProperty("port") + "/"
                            + prop.getProperty("database")
                    , prop.getProperty("user")
                    , prop.getProperty("password"));

            */
            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection FAIL");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


}
