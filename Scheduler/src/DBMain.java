
import java.sql.*;
import javax.swing.*;

/**
 * Database to store info on activities
 * @author bvien
 *
 */
public class DBMain {
	
	Connection con = null;
	
	/*public static void main(String[] args) throws Exception {
		ConnectorDb();
	}*/
	
	public static Connection connectorDb()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\bvien\\workspace\\Scheduler\\SchedulerDB.sqlite");
			JOptionPane.showMessageDialog(null, "Connection Established");
			return con;
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}