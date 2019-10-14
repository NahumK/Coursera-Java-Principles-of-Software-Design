
import java.util.*;

public class WordGram {

    private String[] myWords;
    private int myHash;
    
    public WordGram(String[] source, int start, int size){
        this.myWords = new String[size];
        System.arraycopy(source, start, this.myWords, 0, size);
    }
    
    public String wordAt(int index){
        if(index < 0 || index >= this.myWords.length)
            throw  new IndexOutOfBoundsException("bad index in wordAt" + index);
        return this.myWords[index];
    }
    
    public int length(){
        return this.myWords.length;
    }
    
    public String toString(){
        String result = "";
        int len = this.myWords.length;
        
        for(int i = 0; i < len; i++)
            result += this.myWords[i] + " ";
        
        return result.trim();
    }
    
    public boolean equals(Object o){
        
        WordGram other = (WordGram)o;
        
        int len = this.length();
        
        if(len != other.length())
            return false;
            
        for(int i = 0; i < len; i++){
            if(!this.wordAt(i).equals(other.wordAt(i)))
                return false;
        }
        
        return true;
    }
    
    public WordGram shiftAdd(String word){
        
        int len = this.length();
        String[] output = new String[len];
        
        output[len - 1] = word; 
        System.arraycopy(this.myWords, 1, output, 0, len - 1);
        WordGram out = new WordGram(output, 0, len);
        
        return out;
        
    }
    
    public int hashCode(){
        return toString().hashCode();
    }
    
}
