

public class MinMagFilter implements Filter{

    private double magMin;
    private String filterName;
    
    public MinMagFilter(double min, String name){
        this.magMin = min;
        this.filterName = name;
    }
    
    public String getName(){
        return this.filterName;
    }
    
    public boolean satisfies(QuakeEntry qe){
        boolean result = qe.getMagnitude() >= this.magMin;
        return result;
    }
    
}
