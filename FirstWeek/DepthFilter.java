
public class DepthFilter implements Filter{
    
    private double minDepth;
    private double maxDepth;
    private String filterName;
    
    public DepthFilter(double min, double max, String filterName){
        this.minDepth = min;
        this.maxDepth = max;
        this.filterName = filterName;
    }
    
    public String getName(){
        return this.filterName;
    }
    
    public boolean satisfies(QuakeEntry qe){
        double depth = qe.getDepth();
        boolean result = ((depth >= this.minDepth) && (depth <= this.maxDepth));
        return result;
    }
    
}
