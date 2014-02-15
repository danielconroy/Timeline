package timeline;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Datatype to represent a timeline.
 * 
 * @author Daniel
 * @author Kayley
 */
class Timeline{
	private String title;
	private ArrayList<Event> events; 
        /**
         * Constructor
         * 
         * @param title The title of the timeline.
         */
	Timeline(String title){
            this.title = title;
            events = new ArrayList<Event>();
	}
        /**
         * A method to retrieve the title.
         * 
         * @return The title.
         */
	public String getTitle(){
		return title;
	}
        /**
         * Sets the title.
         * 
         * @param title The title to be set.
         */
	public void setTitle(String title){
		this.title = title;
	} 
        /**
         * Adds an event to this timeline.
         * 
         * @param e The event to be added.
         */
	public void addEvent(Event e){
            if(!containsTitle(e.getTitle()))
                events.add(e);
	}
        /**
         * Deletes an event from this timeline.
         * 
         * @param e The event to be deleted.
         */
	public void deleteEvent(Event e){
		events.remove(e);
	}
        /**
         * Searches for the title of an event.
         * 
         * @param title The title for which to search.
         * @return True if found, else false.
         */
        public boolean containsTitle(String title){
            for(Event e : events){
                if(e.getTitle().equals(title)) return true;
            }return false;
        }
        /**
         * Returns the number of events.
         * 
         * @return The number of events.
         */
        public int eventSize(){
            return events.size();
        }
        /**
         * Finds and returns an event
         * 
         * @param title The name of the event to return.
         * @return The Event found.
         * @throws Exception If not found, throws this exception.
         */
        public Event findEvent(String title) throws Exception{
            for(int i = 0 ; i < events.size(); i++){
                if(events.get(i).getTitle().equals(title))
                    return events.get(i);
            }
            throw new Exception("Not found.");
        }
        /**
         * Returns an iterator over this timeline's events.
         * 
         * @return The iterator.
         */
        public Iterator<Event> getEventIterator(){
            return new Iterator<Event>() {
            // Start stepping through the array from the beginning
            private int nextIndex = 0;
            public boolean hasNext() {
                // Check if the current element is the last in the array
                return (nextIndex < events.size());
            }        
            public Event next() {
                return events.get(nextIndex++);
            }
            public void remove(){  
                events.remove(nextIndex);
            }
        };
    }
}