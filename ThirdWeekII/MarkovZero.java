
import java.util.Random;

public class MarkovZero extends AbstractMarkovModel{
    
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
    
    public String toString(){
        String result = "MarkovModel of order 0";
        return result;
    }
    
}
