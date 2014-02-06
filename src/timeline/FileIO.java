/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Daniel
 */
public class FileIO {
    ArrayList<Timeline> timelines;
    String fileName;
    /*
     * Constructor
     */
    public FileIO(){
        this.timelines = new ArrayList<Timeline>();
        fileName = "TimeLineSave.txt";
        BufferedReader reader;
        String raw;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            while(reader.ready()){
                raw = reader.readLine();
                String[] parsed = raw.split(";");
                String timelineName = parsed[0];
                Timeline timeline = new Timeline(timelineName);
                for(int i = 1; i<parsed.length; i++){
                    String[] e = parsed[i].split(":");
                    //order: title, category, startDate, endDate, category
                    Event event = new Event(e[0],e[1],Integer.parseInt(e[2])
                            ,Integer.parseInt(e[3]),new Category(e[4]));
                    timeline.getEvents().add(event);
                }
                timelines.add(timeline);
            }
        }catch(Exception ex){}
    }
    /*
     * @return The current ArrayList of Timelines.
     */
    public ArrayList<Timeline> loadAll(){
        return timelines;
    }
    /*
     * @param name The name of the Timeline to load.
     * @return The timeLine of that name or a new Timeline with "NoTimeline" as its name.
     */
    public Timeline load(String name){
        for(Iterator it = timelines.iterator();it.hasNext();){
            Timeline timeline = (Timeline)it.next();
            if(timeline.getTitle().equals(name)){
                return timeline;
            }
        }
        return new Timeline("NoTimeline");
    }
    /*
     * @return True if the writing the file was successful, otherwise False.
     */
    public boolean save(){
        PrintWriter writer;
        try{
            writer = new PrintWriter(fileName);
        }catch(Exception ex){
            return false;
        }
        //each timeline is on a seperate line
        //semi-colon will seperate events and the title
        //colon will seperate parts of events
        for(Iterator tLines = timelines.iterator();tLines.hasNext();){
            Timeline timeline = (Timeline)tLines.next();
            writer.print(timeline.getTitle()+";");
            for(Iterator events = timeline.getEvents().iterator();events.hasNext();){
                Event event = (Event)events.next();
                String name = event.getTitle();
                String des = event.getDescription();
                int start = event.getStartDate();
                int end = event.getEndDate();
                Category cat = event.getCategory();
                writer.print(name+":");
                writer.print(des+":");
                writer.print(start+":");
                writer.print(end+":");
                writer.print(cat.toString()+";");
            }
            writer.println();
        }
        return true;
    }
    /*
     * @param Timeline The Timeline to add.
     */
    public void addTimeline(Timeline t){
        timelines.add(t);
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
}
