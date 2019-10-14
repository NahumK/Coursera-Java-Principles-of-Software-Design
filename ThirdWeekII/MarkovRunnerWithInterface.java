
import edu.duke.*;

public class MarkovRunnerWithInterface {

    private void runModel(IMarkovModel markov, String text, int size, int seed){

        markov.setTraining(text);
        markov.setRandom(seed);

        System.out.println("running with " + markov);

        for(int i = 0; i < 3; i++){
            String outText = markov.getRandomText(size);
            printOut(outText);
        }

    }

    public void testHashMap(){

        FileResource fr = new FileResource();
        String text = fr.asString();
        //String text = "yes-this-is-a-thin-pretty-pink-thistle";
        text = text.replace('\n', ' ');
        
        int size = 500;
        int N = 5;
        int seed = 531;

        EfficientMarkovModel markov = new EfficientMarkovModel(N);
        runModel(markov, text, size, seed);

    }

    public void compareMethods(){

        FileResource fr = new FileResource();
        String text = fr.asString();
        text = text.replace('\n', ' ');
        int size = 1000;
        int N = 2;
        int seed = 42;
        long startTime, estimatedTime;
        
        startTime = System.nanoTime();
        
        /*
        MarkovModel mz = new MarkovModel(N);
        runModel(mz, text, size, seed);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("MarkovModel duration : " + estimatedTime);
        //*/
        
        //*
        EfficientMarkovModel mz1 = new EfficientMarkovModel(N);
        runModel(mz1, text, size, seed);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("EfficientMarkovModel duration : " + estimatedTime);
        //*/

    }

    public void runMarkov(){

        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int N = 3;
        int seed = 1;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(N);
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
