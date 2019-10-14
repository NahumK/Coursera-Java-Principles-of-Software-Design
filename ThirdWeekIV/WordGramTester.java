
import java.util.*;

public class WordGramTester {
    
    public void tester(){
        
        String[] source = {"this", "is", "a", "test"};
        String word = "yes";
        int start = 0;
        int size = 4;
        WordGram wg = new WordGram(source, start, size);
        System.out.println(wg);
        WordGram wg2 = wg.shiftAdd(word);
        System.out.println(wg2);
    }
    
}
