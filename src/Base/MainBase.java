package Base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainBase {
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

             //   firstTask(myList);
            //    secondTask(myList);

                /*******************************************/

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

