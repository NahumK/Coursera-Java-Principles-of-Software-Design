
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{

    private int nberCharac;
    private HashMap<String, ArrayList<String>> memoryFollow;

    public EfficientMarkovModel(int n){
        this.nberCharac = n;
    }
    
     public void setTraining(String s){
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
    }

    private void buildMap(){
        
        this.memoryFollow = new HashMap<String, ArrayList<String>>();
        int len = this.myText.length();
        
        for(int i = 0; i <= len - this.nberCharac; i++){
            String key = this.myText.substring(i, i + this.nberCharac);
            if(!this.memoryFollow.containsKey(key))
                this.memoryFollow.put(key, super.getFollows(key));
        }

    }

    protected ArrayList<String> getFollows(String key){

        return this.memoryFollow.get(key);

    }
    
    private void printHashMapInfo(){
        
        System.out.println("\n----------------------------------");
        
        int largestSize = 0;
        
        //*
        //Print the HashMap (all the keys and their corresponding values)
        
        //System.out.println("Memory map of all the keys and values : ");
        
        for(String key : this.memoryFollow.keySet()){
            ArrayList<String> follows = this.memoryFollow.get(key);
            //System.out.println("key : " + key + "\t" + follows);
            int size = follows.size();
            if(size > largestSize)
                largestSize = size;
        }
        //*/
        
        //Print the number of keys in the HashMap
        System.out.println("Nber of keys : " + this.memoryFollow.size());
        
        //Print the size of the largest value in the HashMap
        System.out.println("Size largest value in the HashMap : " + largestSize);
        
        //Print the keys that have the maximum size value.
        ArrayList<String> largestKeys = new ArrayList<String>();
        for(String key : this.memoryFollow.keySet()){
            if(this.memoryFollow.get(key).size() == largestSize)
                largestKeys.add(key);
        }
        System.out.println("Keys with maximum size values :  " + largestKeys);
        System.out.println("\n----------------------------------");
    }

    public String getRandomText(int numChars){

        if(this.myText == null)
            return "";

        StringBuilder result = new StringBuilder();
        int len = this.myText.length();
        int index = this.myRandom.nextInt(len - this.nberCharac);
        String key = this.myText.substring(index, index + this.nberCharac);
        result.append(key);

        for(int i = 0; i < numChars - this.nberCharac; i++){
            ArrayList<String> follows = this.getFollows(key);
            int followsLen = follows.size();
            if(followsLen == 0)
                break;
            index = this.myRandom.nextInt(followsLen);
            String next = follows.get(index);
            result.append(next);
            key = key.substring(1) + next;
        }

        return result.toString();
    }

    public String toString(){
        String result = "EfficientMarkovModel of order " + this.nberCharac;
        return result;
    }

}

