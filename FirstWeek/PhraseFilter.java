
public class PhraseFilter implements Filter{

    private String where;
    private String phrase;
    private String filterName;

    public PhraseFilter(String where, String phrase, String filterName){
        this.where = where;
        this.phrase = phrase;
        this.filterName = filterName;
    }
    
    public String getName(){
        return this.filterName;
    }

    public boolean satisfies(QuakeEntry qe){
        
        String title = qe.getInfo();
        
        if(where.equals("start") && title.startsWith(phrase))
            return true;
        if(where.equals("end") && title.endsWith(phrase))
            return true;
        if(where.equals("any") && title.contains(phrase))
            return true;
            
        return false;
        
    }

}
