
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {

    public EarthQuakeClient2(){

    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f){

        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();

        for(QuakeEntry qe : quakeData){
            if(f.satisfies(qe))
                result.add(qe);
        }

        return result;

    }

    private void printQuake(ArrayList<QuakeEntry> data){
        int len = data.size();
        for(QuakeEntry qe : data)
            System.out.println(qe);
    }

    public void quakesWithFilter(){

        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);         
        System.out.println("read data for " + quakeData.size() + " quakes");

        double minMag = 3.5;
        double maxMag = 4.5;
        double minDepth = -55000.0;
        double maxDepth = -20000.0 ;
        double maxDistance = 1000 * 1000;
        String where = "any";
        String phrase = "a";
        //Colorado Location(39.7392, -104.9903
        double latitude = 39.7392;
        double longitude = -104.9903;
        Location city = new Location(latitude, longitude); 

        Filter f = new MinMagFilter(minMag, "minMag");
        Filter f1 = new MagnitudeFilter(minMag, maxMag, "Magnitude");
        Filter f2 = new DepthFilter(minDepth, maxDepth, "Depth");
        Filter f3 = new DistanceFilter(city, maxDistance, "Distance");
        Filter f4 = new PhraseFilter(where, phrase, "Phrase");

        ArrayList<QuakeEntry> list1  = filter(quakeData, f1); 
        //printQuake(list1);
        ArrayList<QuakeEntry> list2  = filter(list1, f2); 
        printQuake(list2);
        
        System.out.println("\n#quakes are : " + list2.size());

    }

    public void testMatchAllFilter(){

        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        //printQuake(quakeData);
        System.out.println("read data for "+ quakeData.size()+ " quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        
        double minMag = 1.0;
        double maxMag = 4.0;
        double minDepth = -180000.0;
        double maxDepth = -30000.0;
        String where = "any";
        String phrase = "o";
        
        maf.addFilter(new MagnitudeFilter(minMag, maxMag, "Magnitude"));
        maf.addFilter(new DepthFilter(minDepth, maxDepth, "Depth"));
        maf.addFilter(new PhraseFilter(where, phrase, "Phrase"));
        
        //Filter f = maf;
        ArrayList<QuakeEntry> list = filter(quakeData, maf);
        printQuake(list);
        
        System.out.println("\n#quakes are : " + list.size());
        System.out.println("Filters used are : " + maf.getName());

    }
    
    public void testAllMatchFilter2(){
        
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        //printQuake(quakeData);
        System.out.println("read data for "+ quakeData.size()+ " quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        
        double minMag = 0.0;
        double maxMag = 5.0;
        String where = "any";
        String phrase = "e";
        //Billund Location(55.7308, 9.1153)
        double latitude = 55.7308;
        double longitude = 9.1153;
        double maxDistance = 3000 * 1000;
        Location city = new Location(latitude, longitude);
        
        maf.addFilter(new MagnitudeFilter(minMag, maxMag, "Magnitude"));
        maf.addFilter(new DistanceFilter(city, maxDistance, "Distance"));
        maf.addFilter(new PhraseFilter(where, phrase, "Phrase"));
        
        ArrayList<QuakeEntry> list = filter(quakeData, maf);
        printQuake(list);
        
        System.out.println("\n#quakes are : " + list.size());
        System.out.println("Filters used are : " + maf.getName());
        
    }

    public void createCSV(){

        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        dumpCSV(quakeData);
        System.out.println("# quakes read: "+ quakeData.size());

    }

    public void dumpCSV(ArrayList<QuakeEntry> list){

        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            Location loc = qe.getLocation();
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",loc.getLatitude(),loc.getLongitude(),qe.getMagnitude(),qe.getInfo());
        }

    }

}
