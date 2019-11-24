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
			
			String url = "jdbc:ucanaccess://" + connectionObj.getPath(); // gets the connection instance
			
			con = DriverManager.getConnection(url + connectionObj.getUserName() + connectionObj.getPassword());
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