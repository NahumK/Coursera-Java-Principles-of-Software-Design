

public class MagnitudeFilter implements Filter{
    
    private double minMag;
    private double maxMag;
    private String filterName;
    
    public MagnitudeFilter(double min, double max, String name){
        this.minMag = min;
        this.maxMag = max;
        this.filterName = name;
    }
    
    public String getName(){
        return this.filterName;
    }
    
    public boolean satisfies(QuakeEntry qe){
        double  mag = qe.getMagnitude();
        boolean result = ((mag >= this.minMag) && (mag <= this.maxMag));
        return result;
    }
    
    
}
