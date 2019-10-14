
import java.util.*;

public class MarkovFour extends AbstractMarkovModel{

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

    public String toString(){
        String result = "MarkovModel of order 4";
        return result;
    }

}

