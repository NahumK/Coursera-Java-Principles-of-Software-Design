
import java.util.*;

public class MatchAllFilter implements Filter{

    private ArrayList<Filter> filters;
    private String filtersNames;
    
    public MatchAllFilter(){
        this.filters = new ArrayList<Filter>();
        this.filtersNames = "";
    }
    
    public String getName(){
        return this.filtersNames;
    }
    
    public void addFilter(Filter f){
        this.filters.add(f);
        this.filtersNames += f.getName() + " ";
    }
    
    public boolean satisfies(QuakeEntry qe){
      
        for(Filter f : filters){
            if(!f.satisfies(qe))
                return false;
        }
        
        return true;
    }
}
