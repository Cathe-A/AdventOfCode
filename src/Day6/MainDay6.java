package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDay6 {
    public static void main(String[] args) {

        try {
            File myObj = new File("input6.txt");
            Scanner myReader = new Scanner(myObj);
            List<Integer> myList = new ArrayList<>();
            String[] data;
            while (myReader.hasNext()) {
                data = myReader.next().split(",");

                for (int i = 0; i < data.length; i++) {
                    myList.add(Integer.parseInt(data[i]));
                }
            }
            myReader.close();


            int nbDays = 256;


            /********************TASKS********************/

        //    firstTaskDay6(myList, nbDays);
            secondTaskDay6(myList, nbDays);

            /*******************************************/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    /**
     * As we know, we have only 9 possible "state" of the fish (from 0 to 8),
     * so we can iterate through the list of length 9 and use the index as a
     * equivalent of state.
     * First, we put all our data in array, counting the apparition of each number
     * and putting it in appropriate cell.
     * Then, each day, the values of one day should be trandormed to the tab[i - 1].
     * So, general algorithm is:
     * Take the value from tab[i]
     * Pass it to next tab value tab[i-1]
     * Keep the value of previous tab[i+1] to the current tab[i]
     * if the tab[0] > 0 => put tab[0] in tab[8] and put 0 to tab[0]
     */
    private static void secondTaskDay6(List<Integer> myList, int nbDays) {

        int[] tab = new int[9];

        //initializer le tableau
        for (int j = 0; j < myList.size(); j++) {
            tab[(myList.get(j))] = (tab[myList.get(j)] + 1);
        }

        showArray(tab);

        //Transform to long
        List<Long> listOfLongs = new ArrayList<>();
        for (int i = 0; i < tab.length; i++) {
            listOfLongs.add(Long.valueOf(tab[i]));
        }

        showListLong(listOfLongs);


        //nbPass -> number to send to next value, so to tab[i-1]
        //nbKeep -> number to keep, so from tab[i+1] to tab[i]
        for (int i = 0; i < nbDays; i++) {

            Long nbPass;
            Long nbKeep = 0l;
            Long newFish = 0l;

            //Calculate the number of new fish
            if (listOfLongs.get(0) > 0) {
                newFish = listOfLongs.get(0);
                listOfLongs.set(0, 0l);
            }


            for (int j = listOfLongs.size() - 1; j >= 0; j--) {

                nbPass = listOfLongs.get(j);
                listOfLongs.set(j, nbKeep);
                nbKeep = nbPass;
            }

            if (newFish > 0) {
                listOfLongs.set(6, listOfLongs.get(6) + newFish);
                listOfLongs.set(8, newFish);
            }

            showListLong(listOfLongs);
        }

        Long total = 0l;

        for (int j = 0; j < listOfLongs.size(); j++) {
            total = total + listOfLongs.get(j);
        }
        System.out.println("TOTAL: " + total);

    }

    private static void firstTaskDay6(List<Integer> myList, int nbDays) {

        int newFish = 0;
        Long total;


        for (int j = 0; j < nbDays; j++) {
            for (int i = 0; i < myList.size(); i++) {

                if (myList.get(i) == 0) {
                    myList.set(i, 6);
                    newFish++;
                } else {
                    myList.set(i, (myList.get(i) - 1));
                }
            }

            for (int i = 0; i < newFish; i++) {
                myList.add(8);
            }

            newFish = 0;

        }

        total = Long.valueOf(myList.size());
        System.out.println("TOTAL: " + total);

    }


    public static void showList(List<Integer> myList, int day) {

        System.out.print("Day " + day + ": ");
        for (Integer item : myList) {
            System.out.print(item + ",");
        }
        System.out.println();

    }

    public static void showListLong(List<Long> myList) {


        for (Long item : myList) {
            System.out.print(item + ",");
        }
        System.out.println();

    }


    public static void showList(List<Integer> myList) {

        for (Integer item : myList) {
            System.out.print(item + ", ");
        }
        System.out.println();

    }


    public static void showArray(Long[] tab) {

        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }

    public static void showArray(int[] tab) {

        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }


}
