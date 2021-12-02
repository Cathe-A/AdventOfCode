package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainDay2 {
    public static void main(String[] args) {

        try {
            File myObj = new File("input2.txt");

            /********************TASKS********************/

            firstTaskDay2(myObj);
            secondTaskDay2(myObj);

            /*******************************************/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void secondTaskDay2(File myObj) throws FileNotFoundException {
        Scanner myReader = new Scanner(myObj);

        int position = 0;
        int depth = 0;
        int aim = 0;
        int result = 0;


        while (myReader.hasNextLine()) {
            String[] data = myReader.nextLine().split(" ");
            String command = data[0];
            int value = Integer.parseInt(data[1]);

            switch (command) {
                case "forward": position += value; depth += value*aim;  break;
                case "down": aim += value; break;
                case "up": aim -= value; break;
            }
        }
        myReader.close();

        result = position * depth;
        System.out.println(result);

    }

    private static void firstTaskDay2(File myObj) throws FileNotFoundException {
        Scanner myReader = new Scanner(myObj);
        int position = 0;
        int depth = 0;
        int result = 0;


        while (myReader.hasNextLine()) {
            String[] data = myReader.nextLine().split(" ");
            String command = data[0];
            int value = Integer.parseInt(data[1]);

            switch (command) {
                case "forward": position += value; break;
                case "down": depth += value; break;
                case "up": depth -= value; break;
            }
        }
        myReader.close();

        result = position * depth;
        System.out.println(result);

    }


}
