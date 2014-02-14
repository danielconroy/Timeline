package timeline;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Daniel
 */
class Timeline{
	private String title;
	private ArrayList<Event> events; 

	Timeline(String title){
            this.title = title;
            events = new ArrayList<Event>();
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	} 

	public void addEvent(Event e){
            if(!containsTitle(e.getTitle()))
                events.add(e);
	}
        
	public void deleteEvent(Event e){
		events.remove(e);
	}
        
        public boolean containsTitle(String title){
            for(Event e : events){
                if(e.getTitle().equals(title)) return true;
            }return false;
        }
        
        public int eventSize(){
            return events.size();
        }
        
        public Event findEvent(String natitleme) throws Exception{
            for(int i = 0 ; i < events.size(); i++){
                if(events.get(i).getTitle().equals(title))
                    return events.get(i);
            }
            throw new Exception("Not found.");
        }
        
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