
import java.util.*;

public class MarkovWord implements IMarkovModel{

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order){
        this.myOrder = order;
        this.myRandom = new Random();
    }

    public void setRandom(int seed){
        this.myRandom = new Random(seed);
    }

    public void setTraining(String text){
        this.myText = text.split("\\s+");
    }

    
    private int indexOf(String[] words, WordGram target, int start){

        int len = words.length;
        int wgLen = target.length();

        for(int i = start; i <= len - wgLen; i++){
            String[] tmp = new String[wgLen];
            System.arraycopy(words, i, tmp, 0, wgLen);
            WordGram compare = new WordGram(tmp, 0, wgLen);
            if(target.equals(compare))
                return i;
        }

        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram){

        ArrayList<String> follows = new ArrayList<String>();

        int pos = 0, len = this.myText.length;
        int index = indexOf(this.myText, kGram, pos);

        while((index != -1) && (index + this.myOrder < len)){
            String next = this.myText[index + this.myOrder];
            follows.add(next);
            pos = index + 1;
            index = indexOf(this.myText, kGram, pos);
        }

        return follows;

    }

    public void testIndexOf(){

        String[] words = {"this", "is", "a", "test"};
        String[] source = {"aN", "test"};
        WordGram target = new WordGram(source, 0, 2);
        int index = indexOf(words, target, 0);
        System.out.println(index);

    }
    
    private void appendWordGram(StringBuilder sb, WordGram wg){
        int len = wg.length();
        for(int i = 0; i < len; i++){
            sb.append(wg.wordAt(i));
            sb.append(" ");
        }
    }

    public String getRandomText(int numWords){

        StringBuilder sb = new StringBuilder();
        int len = this.myText.length;
        int index = this.myRandom.nextInt(len - this.myOrder);
 
        WordGram key = new WordGram(this.myText, index, this.myOrder);
        appendWordGram(sb, key);

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
            key = key.shiftAdd(next);
            
        }
        
        return sb.toString().trim();

    }

}
