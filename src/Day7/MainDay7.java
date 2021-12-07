package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainDay7 {
    public static void main(String[] args) {

        try {
            File myObj = new File("input7.txt");
            Scanner myReader = new Scanner(myObj);
            List<Integer> myList  = new ArrayList<>();

            while (myReader.hasNext()) {
                String[] data = myReader.next().split(",");
                for (String str : data) {
                    myList.add(Integer.parseInt(str));
                }
            }
            myReader.close();

            /********************TASKS********************/

            firstTaskDay7(myList);
            secondTaskDay7(myList);

            /*******************************************/


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private static void secondTaskDay7(List<Integer> myList) {

        Long totalFuel = Long.MAX_VALUE;
        Long bestTotal = 0l;

        Collections.sort(myList);

        //We need to pass trough all possible numbers between max and min values in the list
        int size = myList.get(myList.size() - 1)  - myList.get(0) ;

        for (int i = 0; i < size; i++) {
            bestTotal = calculateFuelSecondTask(myList, i);
            if(bestTotal < totalFuel) {
                totalFuel = bestTotal;
            }
        }

        System.out.println("Second Task. Total fuel: " + totalFuel);

    }


    private static void firstTaskDay7(List<Integer> myList) {

        int totalFuel = Integer.MAX_VALUE;
        int bestTotal = 0;

        Collections.sort(myList);

        for (int i = 0; i < myList.size(); i++) {
            bestTotal = calculateFuelFirstTask(myList, myList.get(i)) ;
            if(bestTotal < totalFuel) {
                totalFuel = bestTotal;
            }
        }

        System.out.println("First Task. Total fuel: " + totalFuel);

    }

    /**
     *Formula:  n(n+1)/2
     * @param num1
     * @param num2
     * @return
     */
    public static int calculateFuelWithSteps(int num1, int num2) {

        int n = Math.abs(num1 - num2);
        return  (n * (n+1)) / 2;

    }


    public static Long calculateFuelSecondTask(List<Integer> myList, int num) {

        Long fuel = 0l;

        for (int i = 0; i < myList.size(); i++) {

            Long fuelStep = Long.valueOf(calculateFuelWithSteps(myList.get(i), num));
            fuel +=  fuelStep;
        }
        return fuel;
    }


    public static int calculateFuelFirstTask(List<Integer> myList, int num) {

        int fuel = 0;

        for (int i = 0; i < myList.size(); i++) {
            fuel += Math.abs(myList.get(i) - num);
        }

        return fuel;
        
    }


    private static void showList(List<Integer> myList){

        for (Integer item : myList) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }



}

