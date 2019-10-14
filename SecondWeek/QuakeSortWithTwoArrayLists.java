
import edu.duke.*;
import java.util.*;

public class QuakeSortWithTwoArrayLists {

    public QuakeSortWithTwoArrayLists(){

    }

    private QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes){

        QuakeEntry min = quakes.get(0);

        for(QuakeEntry qe : quakes){
            if(qe.getMagnitude() < min.getMagnitude())
                min = qe;
        }

        return min;
    }

    private ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in){

        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();

        while(!in.isEmpty()){
            QuakeEntry min = getSmallestMagnitude(in);
            in.remove(min);
            out.add(min);
        }

        return out;

    }

    private void printList(ArrayList<QuakeEntry> list){
        for (QuakeEntry qe: list) 
            System.out.println(qe);
    }

    public void testSort() {

        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  

        System.out.println("read data for " + list.size() + " quakes");    
        list = sortByMagnitude(list);
        
        printList(list);

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    private void dumpCSV(ArrayList<QuakeEntry> list){

        System.out.println("Latitude,Longitude,Magnitude,Info");

        for(QuakeEntry qe : list){
            Location loc = qe.getLocation();
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",loc.getLatitude(), loc.getLongitude(), qe.getMagnitude(), qe.getInfo());
        }
    }

}
