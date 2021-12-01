package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDay1 {
    public static void main(String[] args) {

        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            List<String> myList  = new ArrayList<String>();


            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

              myList.add(data);

                System.out.println(data);

            }
            myReader.close();

          /********************TASKS********************/

            firstTask(myList);
            secondTask(myList);

            /*******************************************/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void firstTask(List<String> myList) {

        int cpt = 0;

        for (int i=0; i< myList.size() - 1; i++) {

            if(Integer.parseInt(myList.get(i)) < Integer.parseInt(myList.get(i + 1))){
                cpt++;
                System.out.println("BEFORE: " + myList.get(i) + " After:  " + myList.get(i + 1)  + " CPT: " + cpt);
            }
        }
            System.out.println("COMPTEUR " + cpt);
    }


    public static void secondTask(List<String> myList){

        int cptResult = -1;
        int prevSum = 0;

        for (int i=0; i< myList.size() - 2; i++) {

            int firstvalue = Integer.parseInt(myList.get(i));
            int secondvalue = Integer.parseInt(myList.get(i+1));
            int thirdvalue = Integer.parseInt(myList.get(i+2));
            int sum = firstvalue + secondvalue + thirdvalue;

            System.out.println("First: " + firstvalue + " Second: " + secondvalue +
                                " Third: " + thirdvalue + " sum: "+ sum + " prevSum: " + prevSum);

            if(sum > prevSum){
                cptResult++;
            }
            prevSum = sum;

            System.out.println("RESULT: " + cptResult);

        }

    }


}
