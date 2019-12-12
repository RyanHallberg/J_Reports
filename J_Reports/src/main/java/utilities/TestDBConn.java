package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

import org.J_Reports.model.Datasource;


public class TestDBConn {

   public static boolean checkDBConnection(Datasource connectionObj){

      boolean good = true;

      // instantiate the database connection
		Connection con = null;
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); // load the MS Access driver
			
			String url = "jdbc:ucanaccess://" + connectionObj.getConnection_string(); // gets the connection instance
			
         if(connectionObj.getUsername()!= null && connectionObj.getPassword() != null)
         con = DriverManager.getConnection(url + connectionObj.getUsername() + connectionObj.getPassword());
         else
         con = DriverManager.getConnection(url);
		}
		catch (Exception e)
		{
			good = false;
			// return error here
			System.out.println("Error: " + e);
			
		}
   
		return good;
   }

}