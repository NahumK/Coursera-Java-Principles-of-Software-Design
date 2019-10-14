
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {

    private ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin){

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(QuakeEntry qe : quakeData){
            double currMag = qe.getMagnitude();
            if(currMag > magMin)
                answer.add(qe);
        }

        return answer;

    }

    private ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from){

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(QuakeEntry qe : quakeData){
            double currDist = qe.getLocation().distanceTo(from);
            if(currDist < distMax)
                answer.add(qe);
        }

        return answer;
    }

    private ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(QuakeEntry qe : quakeData){
            double currDepth = qe.getDepth();
            if((currDepth > minDepth) && (currDepth < maxDepth))
                answer.add(qe);
        }

        return answer;
    }

    private ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){

        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();

        int lenPhrase = phrase.length();

        for(QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            int len = title.length();
            int index = title.indexOf(phrase);
            if(index != -1){
                if(where.equals("start") && (index == 0))
                    result.add(qe);
                if(where.equals("end") && (index + lenPhrase == len))
                    result.add(qe);
                if(where.equals("any"))
                    result.add(qe);
            }
        }

        return result;
    }

    public void quakeByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        System.out.println("read data for " + quakeData.size() + " quakes");

        String where = "any";
        String phrase = "Can";
        ArrayList<QuakeEntry> list = filterByPhrase(quakeData, where, phrase);

        //*
        for (QuakeEntry qe : list) 
            System.out.println(qe); 
        //*/
        System.out.println("\n#quakes that match " + phrase + " at " + where + " : " + list.size());
    }

    public void quakesOfDepth(){

        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        ArrayList<QuakeEntry> listDepth = filterByDepth(list, minDepth, maxDepth);

        for (QuakeEntry qe : listDepth) 
            System.out.println(qe); 

        System.out.println("\n#quakes with depth between " + minDepth + " and " + maxDepth + " : " + listDepth.size());

    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list)
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",qe.getLocation().getLatitude(),qe.getLocation().getLongitude(),qe.getMagnitude(),qe.getInfo());

    }

    public void bigQuakes(){

        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        double magMin = 5.0;
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, magMin);

        for (QuakeEntry qe : listBig) 
            System.out.println(qe); 

        System.out.println("#quakes with magnitude greater than " + magMin + " : " + listBig.size());
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        /*
        Durham, NC Location(35.988, -78.907)
        Bridgeport, CA Location(38.17, -118.82)
        //*/
        double latitude = 38.17;
        double longitude = -118.82;
        double distMax = 1000*1000;
        Location city = new Location(latitude, longitude);

        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, distMax, city);
        int len = close.size();

        for (int i = 0; i < len; i++) {
            QuakeEntry entry = close.get(i);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }

        System.out.println("\n#quakes within the distance of " + (distMax/1000) + "km: " + close.size());
    }

}
