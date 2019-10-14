
import edu.duke.*;
import java.util.*;

public class MarkovRunner {

    public void runMarkovZero(){
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        int seed = 1024;
        markov.setTraining(st);
        markov.setRandom(seed);
        
        for(int i = 0; i < 3; i++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
        
    }
    
    public void runMarkovOne(){
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 365;
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(seed);
       
        for(int i = 0; i < 3; i++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
   
    }
    
    public void runMarkovFour(){
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 715;
        MarkovFour markov = new MarkovFour();
        markov.setTraining(st);
        markov.setRandom(seed);
       
        for(int i = 0; i < 3; i++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
   
    }
    
     public void runMarkovModel(){
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int N = 7;
        int seed = 953;
        MarkovModel markov = new MarkovModel(N);
        markov.setTraining(st);
        markov.setRandom(seed);
       
        for(int i = 0; i < 3; i++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
   
    }
    
    private void printOut(String s){
        
        String[] words = s.split("\\s+");
        int pSize = 0;
        System.out.println("----------------------------------");
        
        int len = words.length;
        
        for(int i = 0; i < len; i++){
            System.out.print(words[i] + " ");
            pSize += words[i].length() + 1;
            if(pSize > 60){
                System.out.println();
                pSize = 0;
            }
        }
        
        System.out.println("\n----------------------------------");
        
    }
    
}
