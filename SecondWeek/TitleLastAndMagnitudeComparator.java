
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        
        String[] words1 = qe1.getInfo().split(" ");
        String[] words2 = qe2.getInfo().split(" ");
        String lastWord1 = words1[words1.length - 1];
        String lastWord2 = words2[words2.length - 1];
        double mag1 = qe1.getMagnitude();
        double mag2 = qe2.getMagnitude();
        
        int wordCompare = lastWord1.compareTo(lastWord2);
        
        if(wordCompare != 0) return wordCompare;
        
        return Double.compare(mag1, mag2);
        
    }
    
}
