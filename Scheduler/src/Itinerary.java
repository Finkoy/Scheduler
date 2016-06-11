import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;

/**
 * Schedule for the day 
 * @author bvien
 *
 */
public class Itinerary 
{
	private ArrayList<Event> schedule;
	
	/**
	 * Default constructor
	 * Initializes schedule
	 */
	public Itinerary()
	{	
		schedule = new ArrayList<Event>();
	}
	
	/**
	 * Copy constructor
	 * @param other The other itinerary to copy from
	 */
	public Itinerary(Itinerary other)
	{
		schedule = new ArrayList<Event>();
		for(Event event : other.schedule)
		{
			schedule.add(event);
		}
	}
	
	/**
	 * Returns the size of the itinerary
	 * @return schedule's size
	 */
	public int size()
	{
		return schedule.size();
	}
	
	/**
	 * Adds an event to the itinerary
	 * @param event The event to be added
	 */
	public boolean add(Event event)
	{
		if(!isOccupied(event))
		{
			schedule.add(event);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes all of an activity by the name of it
	 * @param activity The name of the activity
	 */
	public void removeAllOfOne(String activity)
	{
		Iterator<Event> it = schedule.iterator();
		while(it.hasNext())
		{
			Event other = it.next();
			if(other.getActivity().equalsIgnoreCase(activity))
			{
				it.remove();
			}
		}
	}
	
	/**
	 * Removes an instance of an event exactly
	 * @param event The event to be removed
	 */
	public void removeInstanceOf(Event event)
	{
		Iterator<Event> it = schedule.iterator();
		while(it.hasNext())
		{
			Event candidate = it.next();
			if(candidate.isEqual(event))
			{
				it.remove();
				return;
			}
		}
	}
	
	/**
	 * Clears the schedule
	 */
	public void removeAll()
	{
		schedule.clear();
	}
	
	/**
	 * Gets the schedule
	 * @return schedule
	 */
	public ArrayList<Event> getSchedule()
	{
		return schedule;
	}
	
	/**
	 * Checks if a time slot is occupied
	 * @param event The event in question
	 * @return true if a slot is occupied
	 */
	public boolean isOccupied(Event event)
	{
		if(schedule.isEmpty()) return false;
		for(Event other : schedule)
		{
			if(isInRangeOf(event, other, false))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to sort the itinerary
	 */
	public void sort()
	{
		Collections.sort(schedule);
	}
	
	public String getDay()
	{
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		
		switch(day)
		{
			case Calendar.SUNDAY:
				return "Sunday";
			case Calendar.MONDAY:
				return "Monday";
			case Calendar.TUESDAY:
				return "Tuesday";
			case Calendar.WEDNESDAY:
				return "Wednesday";
			case Calendar.THURSDAY:
				return "Thursday";
			case Calendar.FRIDAY:
				return "Friday";
			case Calendar.SATURDAY:
				return "Saturday";
			default: return "Sunday";
		}
		
	}
	
	/**
	 * Helper method to check if two time slots are overlapping
	 * @param event The first event in comparison
	 * @param other The other event in comparison
	 * @param otherWay Signal to check the other order
	 * @return
	 */
	private boolean isInRangeOf(Event event, Event other, boolean otherWay)
	{
		int otherStartMinutes = Time.convertMinutesInADay(Time.formatTime(other.getStartTime()));
		int otherEndMinutes = Time.convertMinutesInADay(Time.formatTime(other.getEndTime()));
		int thisStartMinutes = Time.convertMinutesInADay(Time.formatTime(event.getStartTime()));
		int thisEndMinutes = Time.convertMinutesInADay(Time.formatTime(event.getEndTime()));
		
		if(thisStartMinutes >= otherStartMinutes && thisStartMinutes <= otherEndMinutes ||
				thisEndMinutes >= otherStartMinutes && thisEndMinutes <= otherEndMinutes)
		{
			return true;
		}
		else if(otherWay == false)
		{
			return isInRangeOf(other, event, true);
		}
		return false;

	}
}
