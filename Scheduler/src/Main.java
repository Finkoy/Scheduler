import java.awt.EventQueue;
import java.sql.Connection;

/**
 * Runs the program and starts GUI related components
 * @author bvien
 *
 */
public class Main 
{
	public static Itinerary itinerary;
	public static Connection connection;
	protected static ItineraryGUI window;
	
	public static void main(String[] args) 
	{
		
		itinerary = new Itinerary();
		connection = DBMain.connectorDb();
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try 
				{
					window = new ItineraryGUI();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
