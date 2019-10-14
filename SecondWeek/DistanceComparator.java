
import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry>{

    Location fromWhere;
    
    public DistanceComparator(Location where){
        this.fromWhere = where;
    }
    
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        double dist1 = qe1.getLocation().distanceTo(this.fromWhere);
        double dist2 = qe1.getLocation().distanceTo(this.fromWhere);
        return Double.compare(dist1, dist2);
    }
    
}
