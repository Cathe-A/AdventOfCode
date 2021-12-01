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



        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " + myObj.length());
        } else {
            System.out.println("The file does not exist.");
        }

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

