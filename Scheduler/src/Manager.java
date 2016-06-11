import java.awt.EventQueue;
import java.awt.Window;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;

/**
 * Class that manages interactions between the database
 * @author bvien
 *
 */
public class Manager {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Manager(boolean add) 
	{
		initialize(add);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean add) 
	{
		if(add)
		{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						AddEventGUI window = new AddEventGUI();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else if(!add)
		{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						RemoveEventGUI window = new RemoveEventGUI();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}


