
import edu.duke.*;

public class MarkovRunner {

    public void runModel(IMarkovModel markov, String text, int size){ 
        
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        
        for(int i = 0; i < 1; i++){ 
            String output = markov.getRandomText(size); 
            printOut(output); 
        } 
        
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        
        for(int i = 0; i < 1; i++){ 
            String output = markov.getRandomText(size); 
            printOut(output); 
        } 
        
    } 
    
    public void testFollows(){
        
        String text = "this is just a test yes this is a simple test";
        int order = 2;
        MarkovWord markov = new MarkovWord(order); 
        runModel(markov, text, 200);
        
    }
    
    public void testHashMap(){

        FileResource fr = new FileResource();
        String text = fr.asString();
        text = text.replace('\n', ' ');
        //String text = "this is a test yes this is really a test yes a test this is wow";
        
        int size = 50;
        int order = 2;
        int seed = 65;

        EfficientMarkovWord markov = new EfficientMarkovWord(order);
        runModel(markov, text, size, seed);

    }
    
    public void compareMethods(){

        FileResource fr = new FileResource();
        String text = fr.asString();
        text = text.replace('\n', ' ');
        int size = 100;
        int N = 2;
        int seed = 42;
        long startTime, estimatedTime;
        
        startTime = System.nanoTime();
        
        /*
        MarkovWord mz = new MarkovWord(N);
        runModel(mz, text, size, seed);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("MarkovModel duration : " + estimatedTime);
        //*/
        
        //*
        EfficientMarkovWord mz1 = new EfficientMarkovWord(N);
        runModel(mz1, text, size, seed);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("EfficientMarkovModel duration : " + estimatedTime);
        //*/

    }


    public void runMarkovWord() { 
        
        FileResource fr = new FileResource(); 
        String text = fr.asString(); 
        text = text.replace('\n', ' ');
        int size = 120;
        int seed = 844;
        int order = 5;
        
        MarkovWord markov = new MarkovWord(order); 
        runModel(markov, text, size, seed); 
        
    } 
    
    

    private void printOut(String text){
        
        String[] words = text.split("\\s+");
        int pSize = 0;
        int len = words.length;
        
        System.out.println("----------------------------------");
        
        for(int i = 0; i < len; i++){
            
            System.out.print(words[i] + " ");
            pSize += words[i].length() + 1;
            
            if (pSize > 60) {
                System.out.println(); 
                pSize = 0;
            } 
            
        } 
        
        System.out.println("\n----------------------------------");
    } 

}
