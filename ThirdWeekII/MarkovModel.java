
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{

    private int nberCharac;

    public MarkovModel(int n){
        this.nberCharac = n;
    }

    public String getRandomText(int numChars){

        if(this.myText == null)
            return "";

        StringBuilder result = new StringBuilder();
        int len = this.myText.length();
        int index = this.myRandom.nextInt(len - this.nberCharac);
        String key = this.myText.substring(index, index + this.nberCharac);
        result.append(key);

        for(int i = 0; i < numChars - this.nberCharac; i++){
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
    
    public String toString(){
        String result = "MarkovModel of order " + this.nberCharac;
        return result;
    }

}

