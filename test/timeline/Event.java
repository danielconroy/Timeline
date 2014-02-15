
package timeline;
/**
 * Datatype in which to store events.
 * 
 * @author Daniel
 * @author Kayley
 */
class Event{
	private String description, title;
	private Integer startDate, endDate;
	private Category category;
        
        /**
         * Constructor
         * 
         * @param title The name of the Event to be constructed.
         */
        public Event(String title){
            this.title = title;
            category = new Category("Default");
        }
        /**
         * Constructor
         * 
         * @param title The name of the Event to be consructed.
         * @param startDate The int value of start of the event.
         * @param category The category of the event.
         */
	public Event(String title, int startDate, Category category){
		this.title = title;
		this.startDate = startDate;
		this.category = category;
	}
        /**
         * Method to return the description of the Event.
         * 
         * @return The Description.
         */
	public String getDescription(){
		return description;
	}
        /**
         * Method to return the title of the Event.
         * 
         * @return The title of the event.
         */
	public String getTitle(){
		return title;
	}
        /**
         * Method to return the start date of the Event.
         * 
         * @return The start date.
         */
	public Integer getStartDate(){
		return startDate;
	}
        /**
         * Method to return the end date of the Event.
         * 
         * @return The end date.
         */
	public Integer getEndDate(){
                return endDate;
	}
        /**
         * Method to return the category of the Event.
         * 
         * @return The category.
         */
	public Category getCategory(){
            return category;
	}
        /**
         * Sets the description.
         * 
         * @param description The description to use.
         */
	public void setDescription(String description){
		this.description = description;
	}
        /**
         * Sets the title.
         * 
         * @param title The title to use.
         */
	public void setTitle(String title){
		this.title = title;
	}
        /**
         * Sets the start date.
         * 
         * @param startDate The start date to use.
         */
	public void setStartDate(int startDate){
		this.startDate = startDate;
	}
        /**
         * Sets the end date.
         * 
         * @param endDate The end date to use.
         */
	public void setEndDate(Integer endDate){
		this.endDate = endDate;
	}
        /**
         * Sets the category.
         * 
         * @param category The category to use.
         */
	public void setCategory(Category category){
		this.category = category;
	}
}