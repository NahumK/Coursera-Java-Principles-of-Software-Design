
import edu.duke.*;
import java.util.*;

public class QuakeSortInPlace {

    private int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from){

        int minIdx = from;
        int len = quakes.size();

        for(int i = from + 1; i < len; i++){
            double minMag = quakes.get(minIdx).getMagnitude();
            double currMag = quakes.get(i).getMagnitude();
            if(currMag < minMag)
                minIdx = i;
        }

        return minIdx;

    }

    private int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){

        int maxIdx = from;
        int len = quakes.size();

        for(int i = from + 1; i < len; i++){
            double maxDepth = quakes.get(maxIdx).getDepth();
            double currDepth = quakes.get(i).getDepth();
            if(currDepth > maxDepth)
                maxIdx = i;
        }

        return maxIdx;

    }

    private void sortByLargestDepth(ArrayList<QuakeEntry> in){

        int len = in.size();

        for(int i = 0; i < len; i++){
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qMax = in.get(maxIdx);
            in.set(i, qMax);
            in.set(maxIdx, qi);
        }

    }

    private void sortByMagnitude(ArrayList<QuakeEntry> in){

        int len = in.size();

        for(int i =  0; i < len; i++){
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qMin = in.get(minIdx);
            in.set(i, qMin);
            in.set(minIdx, qi);
        }

    }

    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){

        int len = quakeData.size();

        for(int i = 0; i < len - numSorted - 1; i++){
            QuakeEntry qe1 = quakeData.get(i);
            QuakeEntry qe2 = quakeData.get(i + 1);
            double mag1 = qe1.getMagnitude();
            double mag2 = qe2.getMagnitude();
            if(mag1 > mag2){
                quakeData.set(i, qe2);
                quakeData.set(i + 1, qe1);
            }
        }

        /*
        System.out.println("\n**** pass : " + (numSorted + 1) + " ****\n");
        printList(quakeData);
        //*/

    }

    private void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){

        int len = in.size();

        for(int i = 0; i < len - 1; i++)
            onePassBubbleSort(in, i);

    }

    private boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){

        int len = quakes.size();

        for(int i = 0; i < len - 1; i++){
            double mag1 = quakes.get(i).getMagnitude();
            double mag2 = quakes.get(i + 1).getMagnitude();
            if(mag1 > mag2)
                return false;
        }

        return true;

    }

    private void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){

        int len = in.size();
        int nberPass = 0;

        for(int i = 0; i < len - 1; i++){
            onePassBubbleSort(in, i);
            nberPass++;
            if(checkInSortedOrder(in))
                break;
        }
        
        System.out.println("\n **** BubbleSort with : " + nberPass + " pass ****\n");
    }

    private void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){

        int len = in.size();
        int nberSwap = 0;

        for(int i =  0; i < len; i++){
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qMin = in.get(minIdx);
            in.set(i, qMin);
            in.set(minIdx, qi);
            nberSwap++;
            if(checkInSortedOrder(in))
                break;
        }
        System.out.println("\n SelectionSort with : " + nberSwap + " swaps\n");
    }

    private void printList(ArrayList<QuakeEntry> list){
        for (QuakeEntry qe: list) 
            System.out.println(qe);
    }

    public void testSort() {

        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  

        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //printList(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println("\n**** After sorting ****");
        //printList(list);

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());

    }

    private void dumpCSV(ArrayList<QuakeEntry> list){

        System.out.println("Latitude,Longitude,Magnitude,Info");

        for(QuakeEntry qe : list){
            Location loc = qe.getLocation();
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",loc.getLatitude(), loc.getLongitude(), qe.getMagnitude(), qe.getInfo());
        }
    }

}
