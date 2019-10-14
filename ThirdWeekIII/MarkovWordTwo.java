
import java.util.*;

public class MarkovWordTwo implements IMarkovModel{

    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo(){
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
        int index = this.myRandom.nextInt(len - 2);
        String key1 = this.myText[index];
        String key2 = this.myText[index + 1];
        
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");

        for(int i = 0; i < numWords - 2; i++){
            
            ArrayList<String> follows = getFollows(key1, key2);
            //String key1key2 = key1 + " " + key2;
            //System.out.println("Key1 Key2 : " + key1key2 + "\t" + follows);
            
            int followsLen = follows.size();
            if(followsLen == 0)
                break;
                
            index = this.myRandom.nextInt(followsLen);
            String next = follows.get(index);
            
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
            
        }

        return sb.toString().trim();
    }

    private int indexOf(String[] words, String target1,String target2, int start){

        int len = words.length;

        for(int i = start; i < len - 1; i++){
            if(words[i].equals(target1) && words[i + 1].equals(target2))
                return i;
        }

        return -1;
    }

    public void testIndexOf(){
        String text = "this is just a test yes this is a simple test";
        String[] words =  text.split(" ");
        String target1 = "this";
        String target2 = "ils";
        int start = 0;
        System.out.println(indexOf(words, target1, target2, start));
    }

    private ArrayList<String> getFollows(String key1, String key2){

        ArrayList<String> follows = new ArrayList<String>();
       
        int pos = 0, len = this.myText.length;
        int index = indexOf(this.myText, key1, key2, pos);

        while((index != -1) && (index + 2 < len)){
            String next = this.myText[index + 2];
            follows.add(next);
            pos = index + 1;
            index = indexOf(this.myText, key1, key2, pos);
        }

        return follows;

    }

}

