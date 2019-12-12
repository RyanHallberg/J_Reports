package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppDBConnection {

   static String user = "root";
   static String password = "root";

   public static Connection getConnection(){

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/j_reports", user, password);
         return conn;

      } catch ( ClassNotFoundException e) {
         
         // TODO: handle exception
         e.printStackTrace();
         return null;
      } catch (SQLException e) {
         
         e.printStackTrace();
         return null;
      }
      
      
   }
}
