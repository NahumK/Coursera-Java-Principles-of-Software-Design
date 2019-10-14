
import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel{
    
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel(){
        this.myRandom = new Random();
    }
    
    public void setTraining(String s){
        this.myText = s.trim();
    }
    
    public void setRandom(int seed){
        this.myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows(String key){
        
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0, len = this.myText.length(), keyLen = key.length();
        int index = this.myText.indexOf(key);
        
        while((index != -1) && (index + keyLen + 1 <= len)){
            String next = "" + this.myText.charAt(index + keyLen);
            follows.add(next);
            pos = index + 1;
            index = this.myText.indexOf(key, pos);
        }
        
        return follows;
        
    }
 
    abstract public String getRandomText(int numChars);
    
}
