/**
 * Struct-like object to represent time
 * @author bvien
 *
 */
public class Time 
{
	public int hour;
	public int minutes;
	
	public Time()
	{
	}
	
	public Time(int hour, int minutes)
	{
		this.hour = hour;
		this.minutes = minutes;
	}
	
	
	/**
	 * Helper method to get an easy to work with time format
	 * @param time The time string to format
	 * @return Time object to represent
	 */
	public static Time formatTime(String time)
	{
		int hour = 0;
		int minutes = 0;
		boolean semicolon = false;
		String hourString = "";
		String minutesString = "";
		for(int i = 0; i < time.length(); i++)
		{
			if(time.charAt(i) == ':')
			{
				semicolon = true;
			}
			else if(!semicolon)
			{
				hourString += time.charAt(i);
			}
			else
			{
				minutesString += time.charAt(i);
			}
		}
		hour = Integer.parseInt(hourString);
		minutes = Integer.parseInt(minutesString);
		return new Time(hour, minutes);
		
	}
	
	/**
	 * Formats a time object into String format
	 * @param time The time in question
	 * @return a String in semicolon time format
	 */
	public static String formatTimeString(Time time)
	{
		return Integer.toString(time.hour) + ":" + Integer.toString(time.minutes);
	}
	
	
	/**
	 * Turns a time object into an int of minutes in a day
	 * @param time The time to convert
	 */
	public static int convertMinutesInADay(Time time)
	{
		return time.hour * 60 + time.minutes;
	}
	
	/**
	 * Turns a String into an int of minutes in a day
	 * @param time The time to convert
	 */
	public static int convertMinutesInADay(String time)
	{
		return convertMinutesInADay(formatTime(time));
	}
}

