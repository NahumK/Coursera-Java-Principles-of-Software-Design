
/**
 * Write a description of QuakeEntry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class QuakeEntry implements Comparable<QuakeEntry>{
    private Location myLocation;
    private double magnitude;
    private String title;
    private double depth;

    public QuakeEntry(double lat, double lon, double mag, String t, double d){
        this.myLocation = new Location(lat, lon);
        this.magnitude = mag;
        this.title = t;
        this.depth = d;
    }

    public Location getLocation(){
        return this.myLocation;
    }

    public double getMagnitude(){
        return this.magnitude;
    }

    public String getInfo(){
        return this.title;
    }
    
    public double getDepth(){
        return this.depth;
    }

    public int compareTo(QuakeEntry loc){
        double difflat = this.myLocation.getLatitude() - loc.myLocation.getLatitude();
        if(Math.abs(difflat) < 0.001){
            double diff = this.myLocation.getLongitude() - loc.myLocation.getLongitude();
            if(diff < 0) return -1;
            if(diff > 0) return 1;
            return 0;
        }
        if(difflat < 0) return -1;
        if(difflat > 0) return 1;
        //never reached;
        return 0;
    }

    
    public String toString(){
        return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", this.myLocation.getLatitude(),this.myLocation.getLongitude(),this.magnitude,this.depth,this.title);
    }
    
}
