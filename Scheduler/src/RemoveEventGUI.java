import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

/**
 * GUI for removing events
 * @author bvien
 *
 */
public class RemoveEventGUI implements UtilityGUI{

	protected JFrame frame;
	private JTextField dayTextField;
	private JTextField startTextField;
	private JTextField endTextField;
	private JTextField activityTextField;
	private JTextField removeAllOfOneActivityTextField;

	/**
	 * Create the application.
	 */
	public RemoveEventGUI() {
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
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 985, 774);
		frame.getContentPane().setLayout(null);
		
		JPanel removeOnePanel = new JPanel();
		removeOnePanel.setBounds(21, 95, 298, 587);
		frame.getContentPane().add(removeOnePanel);
		removeOnePanel.setLayout(null);
		
		JLabel removeOneEventLabel = new JLabel("Remove an Event");
		removeOneEventLabel.setBounds(32, 5, 233, 37);
		removeOneEventLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		removeOnePanel.add(removeOneEventLabel);
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setBounds(21, 86, 110, 55);
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		removeOnePanel.add(dayLabel);
		
		JLabel startLabel = new JLabel("Start Time:");
		startLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		startLabel.setBounds(21, 162, 139, 55);
		removeOnePanel.add(startLabel);
		
		JLabel endLabel = new JLabel("End Time:");
		endLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		endLabel.setBounds(21, 238, 139, 55);
		removeOnePanel.add(endLabel);
		
		JLabel activityLabel = new JLabel("Activity:");
		activityLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		activityLabel.setBounds(21, 314, 139, 55);
		removeOnePanel.add(activityLabel);
		
		dayTextField = new JTextField();
		dayTextField.setBounds(88, 96, 189, 41);
		dayTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		removeOnePanel.add(dayTextField);
		dayTextField.setColumns(10);
		
		startTextField = new JTextField();
		startTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		startTextField.setColumns(10);
		startTextField.setBounds(163, 162, 114, 41);
		removeOnePanel.add(startTextField);
		
		endTextField = new JTextField();
		endTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		endTextField.setColumns(10);
		endTextField.setBounds(150, 241, 127, 41);
		removeOnePanel.add(endTextField);
		
		activityTextField = new JTextField();
		activityTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		activityTextField.setColumns(10);
		activityTextField.setBounds(122, 314, 155, 41);
		removeOnePanel.add(activityTextField);
		
		JLabel noteLabel = new JLabel("Follow the same");
		noteLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		noteLabel.setBounds(21, 390, 244, 55);
		removeOnePanel.add(noteLabel);
		
		JLabel noteLabel2 = new JLabel("procedure as adding.");
		noteLabel2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		noteLabel2.setBounds(21, 428, 266, 55);
		removeOnePanel.add(noteLabel2);
		
		JButton removeOneEventButton = new JButton("Remove an Event");
		removeOneEventButton.setBounds(21, 493, 256, 73);
		removeOneEventButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
		removeOnePanel.add(removeOneEventButton);
		removeOneEventButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String day = dayTextField.getText();
				String startTime = startTextField.getText();
				String endTime = endTextField.getText();
				String activity = activityTextField.getText();

				try
				{
					Main.itinerary.removeInstanceOf(new Event(day, startTime, endTime, activity));
					updateList();
				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
			}
		});
		
		
		JPanel removeAllOnePanel = new JPanel();
		removeAllOnePanel.setBounds(332, 95, 298, 587);
		frame.getContentPane().add(removeAllOnePanel);
		removeAllOnePanel.setLayout(null);
		
		JLabel removeAllOfOneLabel = new JLabel("Remove all Instances");
		removeAllOfOneLabel.setBounds(8, 5, 282, 37);
		removeAllOfOneLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		removeAllOnePanel.add(removeAllOfOneLabel);
		
		JLabel removeAllOne_TwoLabel = new JLabel("of an Event");
		removeAllOne_TwoLabel.setBounds(73, 47, 152, 37);
		removeAllOne_TwoLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		removeAllOnePanel.add(removeAllOne_TwoLabel);
		
		JLabel removeAllOfOneActivityLabel = new JLabel("Activity:");
		removeAllOfOneActivityLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		removeAllOfOneActivityLabel.setBounds(21, 137, 139, 55);
		removeAllOnePanel.add(removeAllOfOneActivityLabel);
		
		removeAllOfOneActivityTextField = new JTextField();
		removeAllOfOneActivityTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		removeAllOfOneActivityTextField.setColumns(10);
		removeAllOfOneActivityTextField.setBounds(21, 192, 269, 55);
		removeAllOnePanel.add(removeAllOfOneActivityTextField);
		
		JButton removeAllOfOneEventButton = new JButton("Remove Event");
		removeAllOfOneEventButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
		removeAllOfOneEventButton.setBounds(21, 493, 256, 73);
		removeAllOnePanel.add(removeAllOfOneEventButton);
		removeAllOfOneEventButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String activity = removeAllOfOneActivityTextField.getText();

				try
				{
					Main.itinerary.removeAllOfOne(activity);;
					updateList();
				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
			}
		});
		
		JPanel removeAllPanel = new JPanel();
		removeAllPanel.setBounds(640, 95, 298, 587);
		frame.getContentPane().add(removeAllPanel);
		removeAllPanel.setLayout(null);
		
		JLabel removeAllLabel = new JLabel("Remove All");
		removeAllLabel.setBounds(75, 5, 148, 37);
		removeAllLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		removeAllPanel.add(removeAllLabel);
		
		JButton removeAllButton = new JButton("Remove All");
		removeAllButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
		removeAllButton.setBounds(21, 493, 256, 73);
		removeAllPanel.add(removeAllButton);
		removeAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Main.itinerary.removeAll();
					updateList();
				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
			}
		});
		
		JLabel warningLabel = new JLabel("Warning: This");
		warningLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		warningLabel.setBounds(21, 63, 256, 55);
		removeAllPanel.add(warningLabel);
		
		JLabel warning_2Label = new JLabel("button will erase");
		warning_2Label.setFont(new Font("Tahoma", Font.PLAIN, 28));
		warning_2Label.setBounds(21, 114, 256, 55);
		removeAllPanel.add(warning_2Label);
		
		JLabel warning_3Label = new JLabel("your entire schedule.");
		warning_3Label.setFont(new Font("Tahoma", Font.PLAIN, 28));
		warning_3Label.setBounds(21, 172, 266, 55);
		removeAllPanel.add(warning_3Label);
		
		JLabel removalLabel = new JLabel("Removal");
		removalLabel.setBounds(394, 21, 161, 69);
		removalLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frame.getContentPane().add(removalLabel);
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
