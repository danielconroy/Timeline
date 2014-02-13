/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.util.ArrayList;

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
	public ArrayList<Event> getEvents(){
		return events;
	}
	
	public void setTitle(String title){
		this.title = title;
	} 
	
	public void addEvent(Event e){
		events.add(e);
	}
	public void deleteEvent(Event e){
		events.remove(e);
	}
}
