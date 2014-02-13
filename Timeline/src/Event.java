class Event{
	private String description, title;
	private Integer startDate, endDate;
	private Category category;

        public Event(String title){
            this.title = title;
            category = new Category("Default");
        }
        public Event(String title, String des, int startDate, int endDate, Category category){
		this.title = title;
                this.description = des;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
	}
	public Event(String title, int startDate, int endDate, Category category){
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
	}
	public Event(String title, int startDate, Category category){
		this.title = title;
		this.startDate = startDate;
		this.category = category;
	}

	//Getter methods
	public String getDescription(){
		return description;
	}
	public String getTitle(){
		return title;
	}
	public Integer getStartDate(){
		return startDate;
	}
	public Integer getEndDate(){
             if(endDate != null)
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