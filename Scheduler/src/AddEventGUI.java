import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

/**
 * GUI for adding events
 * @author bvien
 *
 */
public class AddEventGUI implements UtilityGUI{

	protected JFrame frame;
	private JTextField dayTextField;
	private JTextField startTimeTextField;
	private JTextField endTimeTextField;
	private JTextField activityTextField;

	/**
	 * Create the application.
	 */
	public AddEventGUI() 
	{
		initialize();
	}
	
	@Override
	public void updateList()
	{
		DefaultListModel<String> DLM = new DefaultListModel<String>();
		ArrayList<Event> eventList = Main.itinerary.getSchedule();
		for(Event event : eventList)
		{
			DLM.addElement(event.getActivity() + "               " +
					event.getStartTime() + " - " + event.getEndTime());
		}
		Main.window.displayList.setModel(DLM);
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 985, 774);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Schedule an Event");
		title.setBounds(246, 21, 428, 74);
		title.setFont(new Font("Tahoma", Font.PLAIN, 50));
		frame.getContentPane().add(title);
		
		JLabel endTimeLabel = new JLabel("End Time:");
		endTimeLabel.setBounds(57, 274, 222, 58);
		endTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frame.getContentPane().add(endTimeLabel);
		
		JLabel startTimeLabel = new JLabel("Start Time:");
		startTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		startTimeLabel.setBounds(57, 195, 222, 58);
		frame.getContentPane().add(startTimeLabel);
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		dayLabel.setBounds(57, 116, 222, 58);
		frame.getContentPane().add(dayLabel);
		
		JLabel activityLabel = new JLabel("Activity:");
		activityLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		activityLabel.setBounds(57, 353, 222, 58);
		frame.getContentPane().add(activityLabel);
		
		dayTextField = new JTextField();
		dayTextField.setColumns(10);
		dayTextField.setBounds(169, 116, 703, 58);
		dayTextField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frame.getContentPane().add(dayTextField);
		
		startTimeTextField = new JTextField();
		startTimeTextField.setColumns(10);
		startTimeTextField.setBounds(256, 195, 616, 58);
		startTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frame.getContentPane().add(startTimeTextField);
		
		endTimeTextField = new JTextField();
		endTimeTextField.setColumns(10);
		endTimeTextField.setBounds(246, 274, 626, 58);
		endTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frame.getContentPane().add(endTimeTextField);
		
		activityTextField = new JTextField();
		activityTextField.setColumns(10);
		activityTextField.setBounds(214, 353, 658, 58);
		activityTextField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frame.getContentPane().add(activityTextField);
		
		JLabel noteLabel = new JLabel("Note: For time inputs, please format text in hour semicolon");
		noteLabel.setBounds(57, 432, 815, 51);
		noteLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(noteLabel);
		
		JLabel noteTwoLabel = new JLabel("minute format. Also type full day name for Day.");
		noteTwoLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		noteTwoLabel.setBounds(57, 476, 815, 51);
		frame.getContentPane().add(noteTwoLabel);
		
		JButton addEventButton = new JButton("Add Event");
		addEventButton.setBounds(57, 566, 815, 104);
		addEventButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frame.getContentPane().add(addEventButton);
		addEventButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String day = dayTextField.getText();
				String startTime = startTimeTextField.getText();
				String endTime = endTimeTextField.getText();
				String activity = activityTextField.getText();

				try
				{
					Event event = new Event(day, startTime, endTime, activity);
					boolean added = Main.itinerary.add(event);
					if(!added)
					{
						JOptionPane.showMessageDialog(null, "Time slot occupied.");
					}
					else
					{
						String query = "insert into Activities (day,start_time,end_time,activity) values (?,?,?,?)";
						PreparedStatement pst = Main.connection.prepareStatement(query);
						pst.setString(1, day);
						pst.setString(2, startTime);
						pst.setString(3, endTime);
						pst.setString(4, activity);				
						pst.execute();
						JOptionPane.showMessageDialog(null, "Data saved!");
			
						pst.close();
						Main.itinerary.sort();
						updateList();
					
					}
				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
			}
		});
				
		
		
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
