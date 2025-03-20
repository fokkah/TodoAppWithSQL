package fokka.se.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLconnection {

        Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoit", "fokka", "admin");

        } catch (SQLException e) {
            System.out.println("Error while connecting to server");
        }
    }

}
