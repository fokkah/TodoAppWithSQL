package fokka.se.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLconnection {

        private static  final String url = "jdbc:mysql://localhost:3306/todoit";
        private static  final String user = "root";
        private static  final String password = "admin";


          public static Connection getConnection() throws SQLException {
              Connection connection = null;
              connection = DriverManager.getConnection(url, user, password);

              return connection;
          }
        //


}
