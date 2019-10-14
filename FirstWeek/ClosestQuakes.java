
import java.util.*;

public class ClosestQuakes {

    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){

        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();

        for(int j = 0; j < howMany; j++){

            int minIndex = 0;
            int len = copy.size();

            for(int i = 0; i < len; i++){
                Location loc = copy.get(i).getLocation();
                Location minLoc = copy.get(minIndex).getLocation();
                if(loc.distanceTo(current) < minLoc.distanceTo(current))
                    minIndex = i;
            }

            result.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return result;
    }

    public void findClosestQuakes(){
        
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());
        
        //jakarta Location(-6.211, 106.845)
        double latitude = -6.211;
        double longitude = 106.845;
        int howMany = 3;
        Location city  = new Location(latitude, longitude);

        ArrayList<QuakeEntry> close = getClosest(list, city, howMany);
        int len = close.size();
        
        for(int  i = 0; i < len; i++){
            QuakeEntry entry = close.get(i);
            Location loc = entry.getLocation();
            double distanceInMeters = city.distanceTo(loc);
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000, entry);
        }
        
        System.out.println("number found : " + len);
        
    }

}
