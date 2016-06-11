/**
 * Event object to put into schedule
 * @author bvien
 *
 */
public class Event implements Comparable<Event>
{
	private String day;
	private String startTime;
	private String endTime;
	private String activity;
	private int duration; // in terms of minutes
	
	/**
	 * Blank constructor
	 */
	public Event()
	{
		this.day = "";
		this.startTime = "";
		this.endTime = "";
		this.activity = "";
		this.duration = 0;
	}
	
	/**
	 * Constructor 1
	 * @param day The day in question
	 * @param startTime The starting time of the event
	 * @param endTime The ending time of the event
	 * @param activity The name of the activity
	 */
	public Event(String day, String startTime, String endTime, String activity)
	{
		this.day = new String(day);
		this.startTime = new String(startTime);
		this.endTime = new String(endTime);
		this.activity = new String(activity);
		this.duration = getDuration();
	}

	public boolean isEqual(Event other)
	{
		if(this.day.equalsIgnoreCase(other.day) && this.startTime.equalsIgnoreCase(other.startTime) &&
				this.endTime.equalsIgnoreCase(other.endTime) && this.activity.equalsIgnoreCase(other.activity))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the day
	 * @return day
	 */
	public String getday()
	{
		return day;
	}
	
	/**
	 * Gets the start time
	 * @return startTime
	 */
	public String getStartTime()
	{
		return startTime;
	}
	
	/**
	 * Gets the end time
	 * @return endTime
	 */
	public String getEndTime()
	{
		return endTime;
	}
	
	/**
	 * Gets the activity
	 * @return activity
	 */
	public String getActivity()
	{
		return activity;
	}
	
	/**
	 * Gets the duration of an event
	 * @return Duration of the eent
	 */
	public int getDuration()
	{
		return duration;
	}
	
	/**
	 * Sets the day
	 * @param day The day to set day to
	 */
	public void setday(String day)
	{
		this.day = day;
	}
	
	/**
	 * Sets the time
	 * @param time The time to set time to
	 */
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	
	/**
	 * Sets the activity
	 * @param activity
	 */
	public void setActivity(String activity)
	{
		this.activity = activity;
	}
	
	/**
	 * 
	 * @param o The object in question
	 * @return
	 */
	@Override
	public int compareTo(Event o) 
	{
		return (Time.convertMinutesInADay(this.startTime) > Time.convertMinutesInADay(o.startTime)) ? 1 : -1;
	}
	
	/**
	 * Finds the duration of an event
	 * This method assumes that events DO NOT span more than 1 day
	 * @param start Start time of the event
	 * @param end End time of the event
	 * @return the duration in terms of minutes
	 */
	private int findDuration(String start, String end)
	{
		Time startTime = Time.formatTime(start);
		Time endTime = Time.formatTime(end);
		return Math.abs(endTime.hour - startTime.hour) * 60 + 
				Math.abs(endTime.minutes - startTime.minutes);
	}
	
	
	private boolean validTimes(String start, String end)
	{
		return (findDuration(start, end) <= 0) ? false : true;
	}
	
}
