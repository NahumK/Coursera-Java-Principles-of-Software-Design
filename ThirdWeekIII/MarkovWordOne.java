
import java.util.*;

public class MarkovWordOne implements IMarkovModel{

    private String[] myText;
    private Random myRandom;

    public MarkovWordOne(){
        this.myRandom = new Random();
    }

    public void setRandom(int seed){
        this.myRandom = new Random(seed);
    }

    public void setTraining(String text){
        this.myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){

        StringBuilder sb = new StringBuilder();
        int len = this.myText.length;
        int index = this.myRandom.nextInt(len - 1);
        String key = this.myText[index];
        sb.append(key);
        sb.append(" ");

        for(int i = 0; i < numWords - 1; i++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Key : " + key + "\t" + follows);
            
            int followsLen = follows.size();
            if(followsLen == 0)
                break;
                
            index = this.myRandom.nextInt(followsLen);
            String next = follows.get(index);
            
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }

    private int indexOf(String[] words, String target, int start){

        int len = words.length;

        for(int i = start; i < len; i++){
            if(words[i].equals(target))
                return i;
        }

        return -1;
    }

    public void testIndexOf(){
        String text = "this is just a test yes this is a simple test";
        String[] words =  text.split(" ");
        String target = "test";
        int start = 5;
        System.out.println(indexOf(words, target, start));
    }

    private ArrayList<String> getFollows(String key){

        ArrayList<String> follows = new ArrayList<String>();
       
        int pos = 0, len = this.myText.length;
        int index = indexOf(this.myText, key, pos);

        while((index != -1) && (index + 1 < len)){
            String next = this.myText[index + 1];
            follows.add(next);
            pos = index + 1;
            index = indexOf(this.myText, key, pos);
        }

        return follows;

    }

}
