import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.List;
import javax.swing.JList;

/**
 * GUI for the schedule
 * @author bvien
 *
 */
public class ItineraryGUI implements UtilityGUI
{

	private JFrame frame;
	protected JList<String> displayList;

	/**
	 * Create the application.
	 */
	public ItineraryGUI() {
		initialize();
		updateList();
		frame.setVisible(true);
	}
	
	/**
	 * Method to update the GUI
	 */
	@Override
	public void updateList() 
	{
		try
		{
			String today = Main.itinerary.getDay();
			String query = "select * from Activities order by RANDOM()";
			PreparedStatement pst = Main.connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			while(rs.next())
			{
				String day = rs.getString("day");
				if(day.equalsIgnoreCase(today))
				{
					String activity = rs.getString("activity");
					String start = rs.getString("start_time");
					String end = rs.getString("end_time");
				
					Event event = new Event(day, start, end, activity);
					Main.itinerary.add(event);
				}
			}
			Main.itinerary.sort();
			ArrayList<Event> eventList = Main.itinerary.getSchedule();
			
			for(Event event : eventList)
			{
				DLM.addElement(event.getActivity() + "               " +
						event.getStartTime() + " - " + event.getEndTime());
			}
			
			displayList.setModel(DLM);
			pst.close();
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 973, 732);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 68, 526, 572);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 50));
		frame.getContentPane().add(scrollPane);
		
		displayList = new JList<String>();
		displayList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		scrollPane.setViewportView(displayList);
		
		JLabel editScheduleLabel = new JLabel("Edit Schedule");
		editScheduleLabel.setBounds(626, 36, 321, 82);
		editScheduleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));

		frame.getContentPane().add(editScheduleLabel);
		
		JButton newEventButton = new JButton("Add Event");
		newEventButton.setBounds(626, 162, 247, 123);
		newEventButton.setFont(new Font("Tahamo", Font.PLAIN, 30));
		frame.getContentPane().add(newEventButton);
		newEventButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				EventQueue.invokeLater(new Runnable() 
				{
					public void run() 
					{
						try 
						{
							Manager window = new Manager(true);
						} 
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JButton removeButton = new JButton("Remove Event");
		removeButton.setBounds(626, 367, 247, 123);
		removeButton.setFont(new Font("Tahamo", Font.PLAIN, 30));
		frame.getContentPane().add(removeButton);
		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				EventQueue.invokeLater(new Runnable() 
				{
					public void run() 
					{
						try 
						{
							Manager window = new Manager(false);
						} 
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JLabel currentScheduleLabel = new JLabel("Current Schedule");
		currentScheduleLabel.setBounds(131, 0, 526, 60);
		currentScheduleLabel.setFont(new Font("Tahamo", Font.PLAIN, 40));
		frame.getContentPane().add(currentScheduleLabel);
		
		JLabel noteLabel = new JLabel("Note: Military time is used.");
		noteLabel.setBounds(568, 535, 358, 60);
		noteLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
		frame.getContentPane().add(noteLabel);
	}
}
