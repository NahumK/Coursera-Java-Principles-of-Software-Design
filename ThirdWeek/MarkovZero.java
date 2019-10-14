
import java.util.Random;

public class MarkovZero {

    private String myText;
    private Random myRandom;
    
    public MarkovZero(){
        this.myRandom = new Random();
    }
    
    public void setRandom(int seed){
        this.myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        this.myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        
        if(this.myText == null)
            return "";
            
        StringBuilder sb = new StringBuilder();
        int len = this.myText.length();
        
        for(int i = 0; i < numChars; i++){
            int index = myRandom.nextInt(len);
            sb.append(this.myText.charAt(index));
        }
        
        return sb.toString();
    }
    
}
