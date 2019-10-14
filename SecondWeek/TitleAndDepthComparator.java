
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{

    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        
        String title1 = qe1.getInfo();
        String title2 = qe2.getInfo();
        double depth1 = qe1.getDepth();
        double depth2 = qe2.getDepth();
        
        int titleCompare = title1.compareTo(title2);
        
        if(titleCompare != 0) return titleCompare;
        
        return Double.compare(depth1, depth2);
    
    }
    
}
