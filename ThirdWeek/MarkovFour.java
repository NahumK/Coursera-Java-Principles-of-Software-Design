
import java.util.*;

public class MarkovFour {

    private String myText;
    private Random myRandom;

    public MarkovFour(){
        this.myRandom = new Random();
    }

    public void setRandom(int seed){
        this.myRandom = new Random(seed);
    }

    public void setTraining(String s){
        this.myText = s.trim();
    }

    public ArrayList<String> getFollows(String key){
        
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

    public String getRandomText(int numChars){

        if(this.myText == null)
            return "";

        StringBuilder result = new StringBuilder();
        int len = this.myText.length();
        int index = this.myRandom.nextInt(len - 4);
        String key = this.myText.substring(index, index + 4);
        result.append(key);

        for(int i = 0; i < numChars - 4; i++){
            ArrayList<String> follows = getFollows(key);
            int followsLen = follows.size();
            if(followsLen == 0)
                break;
            index = this.myRandom.nextInt(followsLen);
            String next = follows.get(index);
            result.append(next);
            key = key.substring(1) + next;
        }

        return result.toString();
    }

}

