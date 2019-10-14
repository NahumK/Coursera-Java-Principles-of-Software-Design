
public class QuakeEntry implements Comparable<QuakeEntry>{

    private Location myLocation;
    private String title;
    private double depth;
    private double magnitude;

    public QuakeEntry(double lat, double lon, double mag, 
    String t, double d) {
        myLocation = new Location(lat,lon);

        magnitude = mag;
        title = t;
        depth = d;
    }

    public Location getLocation(){
        return myLocation;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public String getInfo(){
        return title;
    }

    public double getDepth(){
        return depth;
    }

    public int compareTo(QuakeEntry loc){
        
        double mag1 = this.getMagnitude();
        double mag2 = loc.getMagnitude();
        double depth1 = this.getDepth();
        double depth2 = loc.getDepth();
        
        if(mag1 < mag2) return -1;
        
        if(mag1 > mag2) return 1;
        
        if(depth1 < depth2) return -1;
        
        if(depth1 > depth2) return 1;
        
        return 0;
        
    }

    public String toString(){
        return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
    }

}