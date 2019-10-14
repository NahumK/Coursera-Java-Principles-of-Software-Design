
import edu.duke.*;
import java.util.*;

public class Tester {

    public void testGetFollows(){

        String text = "this is a test yes this is a test.";
        String key = "t.";
        
        MarkovOne markov = new MarkovOne();
        markov.setTraining(text);
        
        ArrayList<String> follows = markov.getFollows(key);
        System.out.println(follows);
        System.out.println("Size : " + follows.size());

    }
    
     public void testGetFollowsWithFile(){

        FileResource fr = new FileResource();
        String text = fr.asString();
        String key = "he";
        
        MarkovOne markov = new MarkovOne();
        markov.setTraining(text);
        
        ArrayList<String> follows = markov.getFollows(key);
        //System.out.println(follows);
        System.out.println("Follows size : " + follows.size());

    }

}
