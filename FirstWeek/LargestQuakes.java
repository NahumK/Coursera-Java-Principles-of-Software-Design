
import java.util.*;

public class LargestQuakes {
    
    private int indexOfLargest(ArrayList<QuakeEntry> data){
        
        int indexMax = -1, i = 0;
        double magMax = Double.MIN_VALUE;
        
        for(QuakeEntry qe : data){
            double mag = qe.getMagnitude();
            if(mag > magMax){
                magMax = mag;
                indexMax = i;
            }
            i++;
        }
        //System.out.println("Largest magnitude : " + magMax + " at index : " + indexMax);
        return indexMax;
    }
    
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copyData = new ArrayList<QuakeEntry>(quakeData);
        
        for(int j = 0; j < howMany; j++){
            int indexMax = indexOfLargest(copyData);
            QuakeEntry qe = copyData.get(indexMax);
            result.add(qe);
            copyData.remove(indexMax);
        }
        
        return result;
        
    }
    
    private void printData(ArrayList<QuakeEntry> data){
     
        for(QuakeEntry qe : data)
            System.out.println(qe);
        
    }

    public void findLargestQuakes(){

        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        int howMany = 50;
         
        System.out.println("\nread data for " + quakeData.size());
        //System.out.println(indexOfLargest(quakeData));
        
        ArrayList<QuakeEntry> list = getLargest(quakeData, howMany);
        printData(list);
        System.out.println("\n#sorted quakes by magnitude : " + howMany);

    }
}
