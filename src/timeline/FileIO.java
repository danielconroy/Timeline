
package timeline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.awt.Color;

/**
 *
 * @author Daniel
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
     * @param Timeline The Timeline to add.
     * returns false if a timeline with that title already exists, true otherwise.
     */
    public boolean addTimeline(Timeline t){
        if(containsTitle(t)) return false;
        else timelines.add(t);
        return true;
    }
    
    public boolean addCategory(Category c){
        if(containsTitle(c)) return false;
        else categories.add(c);
        return true;        
    }
    
    /* 
     *  returns true if timelines contains a timeline of the same title as parameter. 
    */
    public boolean containsTitle(Timeline time){
        for(Timeline t : timelines){
            if(t.getTitle().equals(time.getTitle())) return true;
        }return false;
    }
    
    public boolean containsTitle(Category cat){
        for(Category c : categories){
            if(c.getName().equals(cat.getName())) return true;
        }return false;
    }
    
    public boolean containsTimeline(String name){
        for(Timeline t : timelines){
            if(t.getTitle().equals(name)) return true;
        }return false;
    }


    /*
     * @param name The name of the Timeline to be deleted.
     * @return True if found and removed, else False.
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
    
    public boolean deleteTimeline(Timeline t){
        return timelines.remove(t);
    }
    
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
    
    public boolean deleteCategory(Category cat){
        return categories.remove(cat);
    }

    /*
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

    public int catSize(){
        return categories.size();
    }
    
    public int timeSize(){
        return timelines.size();
    }
    
    public Category getDefaultCategory(){
        return categories.get(0);
    }
    
    

    
}