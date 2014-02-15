
package timeline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.awt.Color;

/**
 * The class for reading and writing to and from files that enables saving and
 * loading of timelines and categories.
 * 
 * @author Daniel
 * @author Kayley
 */
public class FileIO {
    ArrayList<Timeline> timelines;
    ArrayList<Category> categories;
    String timeLineNames;
    String catNames;
    /*
     * Constructor
     */
    public FileIO(){
        this.timelines = new ArrayList<Timeline>();
        this.categories = new ArrayList<Category>();
        timeLineNames = "TimeLineSave.txt";
        catNames = "CatSave.txt";
        BufferedReader reader;
        String raw;
        try{
            reader = new BufferedReader(new FileReader(timeLineNames));
            while(reader.ready()){
                raw = reader.readLine();
                String[] parsed = raw.split(";");
                String timelineName = parsed[0];
                Timeline timeline = new Timeline(timelineName);
                for(int i = 1; i<parsed.length; i++){
                    String[] e = parsed[i].split(":");
                    Event event = new Event(e[0]);
                    //order: title, description, startDate, endDate, category
                    if(!e[1].equals("null")){
                        event.setDescription(e[1]);
                    }
                    event.setStartDate(Integer.parseInt(e[2]));
                    if(!e[3].equals("null")){
                        event.setEndDate(Integer.parseInt(e[3]));
                    }
                    //Must save cat color info too!!!
                    event.setCategory(new Category(e[4]));
                    timeline.addEvent(event);
                }
                timelines.add(timeline);
            }
            reader.close();
        }catch(Exception ex){
        }
        try{
            reader = new BufferedReader(new FileReader(catNames));
            while(reader.ready()){
                raw = reader.readLine();
                String[] parsed = raw.split(";");
                Category category = new Category(parsed[0]);
                Integer red = Integer.parseInt(parsed[1]);
                Integer green = Integer.parseInt(parsed[2]);
                Integer blue = Integer.parseInt(parsed[3]);
                category.setColor(new Color(red,green,blue));
                categories.add(category);
            }
            reader.close();
        }catch(Exception ex){}
    }
    /**
     * Saves the current state Timelines and Categories.
     * @return True if successful, False if unsuccessful.
     */
    public boolean save(){
        PrintWriter writer;
        try{
            writer = new PrintWriter(timeLineNames);
        }catch(Exception ex){
            return false;
        }
        //each timeline is on a seperate line
        //semi-colon will seperate events and the title
        //colon will seperate parts of events
        for(Iterator tLines = timelines.iterator();tLines.hasNext();){
            Timeline timeline = (Timeline)tLines.next();
            writer.print(timeline.getTitle()+";");
            for(Iterator events = timeline.getEventIterator();events.hasNext();){
                Event event = (Event)events.next();
                String name = event.getTitle();
                Category cat = event.getCategory();
                
                String des;
                des = event.getDescription();
                int start = event.getStartDate();
                Integer end;
                end = event.getEndDate();
                writer.print(name+":");
                writer.print(des+":");
                writer.print(start+":");
                writer.print(end+":");
                writer.print(cat.getName()+";");
            }
            writer.println();
        }
        writer.flush();
        try{
            writer = new PrintWriter(catNames);
        }catch(Exception ex){
            return false;
        }
        //each category is on a seperate line
        //semi-colon will seperate parts of category
        for(Iterator tLines = categories.iterator();tLines.hasNext();){
            Category category = (Category)tLines.next();
            writer.print(category.getName()+";");
            writer.print(category.getColor().getRed()+";");
            writer.print(category.getColor().getGreen()+";");
            writer.print(category.getColor().getBlue());
            writer.println();
        }
        writer.flush();
        writer.close();
        return true;
    }
    /*
     * Adds a Timeline to the current collection of Timelines.
     * 
     * @param Timeline The Timeline to add.
     * returns True if successful, False otherwise.
     */
    public boolean addTimeline(Timeline t){
        if(containsTitle(t)) return false;
        else timelines.add(t);
        return true;
    }
    /**
     * Adds a Category to the current collection of Categories.
     * 
     * @param c The Category to add.
     * @return True if successful, False otherwise.
     */
    public boolean addCategory(Category c){
        if(containsTitle(c)) return false;
        else categories.add(c);
        return true;        
    }
    
    /**
     * Searches known timeline titles to find a match.
     * 
     * @param time The timeline for which to search.
     * @return True if found, False otherwise.
     */
    public boolean containsTitle(Timeline time){
        for(Timeline t : timelines){
            if(t.getTitle().equals(time.getTitle())) return true;
        }return false;
    }
    /**
     * Searches known category titles to find a match.
     * 
     * @param cat The category for which to search.
     * @return True if found, False otherwise.
     */
    public boolean containsTitle(Category cat){
        for(Category c : categories){
            if(c.getName().equals(cat.getName())) return true;
        }return false;
    }
    /**
     * Searches known timelines to find a match.
     * 
     * @param name The timeline name for which to search.
     * @return True if found, False otherwise.
     */
    public boolean containsTimeline(String name){
        for(Timeline t : timelines){
            if(t.getTitle().equals(name)) return true;
        }return false;
    }
    /**
     * Deletes a timeline from the current set of timelines.
     * 
     * @param name The name of the timeline to delete.
     * @return True if found and removed, False otherwise.
     */
    public boolean deleteTimeline(String name){
        for(Iterator it = timelines.iterator();it.hasNext();){
            Timeline timeline = (Timeline)it.next();
            if(timeline.getTitle().equals(name)){
                timelines.remove(timeline);
                return true;
            }
        }
        return false;
    }
    /**
     * Deletes a timeline from the current set of timelines.
     * 
     * @param t The timeline to delete.
     * @return True if found and removed, False otherwise.
     */
    public boolean deleteTimeline(Timeline t){
        return timelines.remove(t);
    }
    /**
     * Deletes a Category from the current set of categories.
     * 
     * @param name The name of the category to delete.
     * @return True if found and removed, False otherwise.
     */
    public boolean deleteCategory(String name){
        for(Iterator it = categories.iterator();it.hasNext();){
            Category category = (Category)it.next();
            if(category.getName().equals(name)){
                categories.remove(category);
                return true;
            }
        }
        return false;
    }
    /**
     * Deletes a Category from the current set of categories.
     * 
     * @param cat The category to delete.
     * @return True if found and removed, False otherwise.
     */
    public boolean deleteCategory(Category cat){
        return categories.remove(cat);
    }

    /**
     * Method to return the names of the current timelines.
     * 
     * @return The ArrayList of the names of the current Timelines.
     */
    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<String>();
        for(Iterator it = timelines.iterator();it.hasNext();){
            Timeline timeline = (Timeline)it.next();
            names.add(timeline.getTitle());
        }
        return names;
    }
    /**
     * Returns an iterator of the known categories.
     * 
     * @return An iterator over the known categories.
     */
    public Iterator<Category> getCategoryIterator(){
        return new Iterator<Category>() {
        // Start stepping through the array from the beginning
        private int nextIndex = 0;
        public boolean hasNext() {
            // Check if the current element is the last in the array
            return (nextIndex < categories.size());
        }        
        public Category next() {
            return categories.get(nextIndex++);
        }
        public void remove(){  
            categories.remove(nextIndex);
        }
    };
    }
    /**
     * Returns an iterator of the known timelines.
     * 
     * @return An iterator over the known timelines.
     */
    public Iterator<Timeline> getTimelineIterator(){
        return new Iterator<Timeline>() {
        // Start stepping through the array from the beginning
        private int nextIndex = 0;
        public boolean hasNext() {
            // Check if the current element is the last in the array
            return (nextIndex < timelines.size());
        }        
        public Timeline next() {
            return timelines.get(nextIndex++);
        }
        public void remove(){  
            timelines.remove(nextIndex);
        }
    };
    }
    /**
     * Method to get the number of categories known.
     * 
     * @return An int representing the number of categories known.
     */
    public int catSize(){
        return categories.size();
    }
    /**
     * Method to get the number of timelines known.
     * 
     * @return An int representing the number of timelines known.
     */
    public int timeSize(){
        return timelines.size();
    }
    /**
     * Method to get the default category.
     * 
     * @return The default Category.
     */
    public Category getDefaultCategory(){
        return categories.get(0);
    }
}