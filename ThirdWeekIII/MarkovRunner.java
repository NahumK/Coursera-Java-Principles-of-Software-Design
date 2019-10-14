
import edu.duke.*;

public class MarkovRunner {

    public void runModel(IMarkovModel markov, String text, int size){ 
        
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        
        for(int i = 0; i < 3; i++){ 
            String output = markov.getRandomText(size); 
            printOut(output); 
        } 
        
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        
        for(int i = 0; i < 3; i++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
        
    } 
    
    public void testFollows(){
        
        String text = "this is just a test yes this is a simple test";
        //MarkovWordOne markov = new MarkovWordOne(); 
        MarkovWordTwo markov = new MarkovWordTwo();
        runModel(markov, text, 200);
        
    }

    public void runMarkov() { 
        
        FileResource fr = new FileResource(); 
        String text = fr.asString(); 
        text = text.replace('\n', ' ');
        int size = 120;
        int seed = 139;
        
        MarkovWordOne markov = new MarkovWordOne(); 
        runModel(markov, text, size, seed); 
        
    } 
    
    public void runMarkovTwo() { 
        
        FileResource fr = new FileResource(); 
        String text = fr.asString(); 
        text = text.replace('\n', ' ');
        int size = 120;
        int seed = 832;
        
        MarkovWordTwo markov = new MarkovWordTwo(); 
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
