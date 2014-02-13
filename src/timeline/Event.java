/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

/**
 *
 * @author Daniel
 */
class Event{
	private String description, title;
	private int startDate, endDate;
        private boolean hasStart, hasEnd = false;
	private Category category;
	
        public Event(String title, String des, int startDate, int endDate, Category category){
		this.title = title;
                this.description = des;
		this.startDate = startDate;
                this.hasStart = true;
		this.endDate = endDate;
                this.hasEnd = true;
		this.category = category;
	}
	public Event(String title, int startDate, int endDate, Category category){
		this.title = title;
		this.startDate = startDate;
                this.hasStart = true;
		this.endDate = endDate;
                this.hasEnd = true;
		this.category = category;
	}
	public Event(String title, int startDate, Category category){
		this.title = title;
		this.startDate = startDate;
                this.hasStart = true;
		this.category = category;
	}
	
	//Getter methods
	public String getDescription(){
            return description;
	}
	public String getTitle(){
            return title;
	}
        public boolean hasStartDate(){
            return hasStart;
        }
	public int getStartDate(){
            if(hasStart)
                return startDate;
            else
                return Integer.MIN_VALUE;
	}
        public boolean hasEndDate(){
            return hasEnd;
        }
	public int getEndDate(){
            if(hasEnd)
                return endDate;
            else
                return Integer.MAX_VALUE;
	}
	public Category getCategory(){
            return category;
	}
	
	//Setter methods
	public void setDescription(String description){
            this.description = description;
	}
	public void setTitle(String title){
            this.title = title;
	}
	public void setStartDate(int startDate){
            this.startDate = startDate;
	}
	public void setEndDate(int endDate){
            this.endDate = endDate;
	}
	public void setCategory(Category category){
            this.category = category;
	}
}
