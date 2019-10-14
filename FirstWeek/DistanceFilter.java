

public class DistanceFilter implements Filter{

    private Location location;
    private double maxDistance;
    private String filterName;
    
    public DistanceFilter(Location loc, double distance, String filterName){
        this.location = loc;
        this.maxDistance = distance;
        this.filterName = filterName;
    }
    
    public String getName(){
        return this.filterName;
    }
    
    public boolean satisfies(QuakeEntry qe){
        double distance = qe.getLocation().distanceTo(this.location);
        boolean result = distance < this.maxDistance;
        return result;
    }
    
}
