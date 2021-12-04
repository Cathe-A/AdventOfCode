package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class MainDay3 {

    public static void main(String[] args) throws IOException {

        try {
            File myObj = new File("input3.txt");
            Scanner myReader = new Scanner(myObj);

            List<String> myList  = new ArrayList<>();

            Path path = Paths.get( myObj.getPath());
            long lines = Files.lines(path).count();

            int [][] tab = new int[(int)lines][12];

            int line = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                for (int i = 0; i < data.length(); i++) {
                    tab[line][i] = Integer.parseInt(String.valueOf(data.charAt(i)));
                }
                myList.add(data);
                line++;
            }
            myReader.close();

            /********************TASKS********************/

            firstTaskDay3(tab);
            secondTaskDay3(myList);

            /*******************************************/

        } catch (FileNotFoundException e ) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void firstTaskDay3(int[][] tab) {

        int powerConsumption;
        int epsilonRate;
        int gammaRate;
        int sum;

        String gamma = "";
        String epsilon = "";

        for (int i = 0; i < tab[0].length; i++) {
                sum = 0;

            for (int j = 0; j < tab.length; j++) {
                   sum += tab[j][i];
            }

            if(sum > tab.length / 2) {

                gamma += 1;
                epsilon +=0;
            } else {

                gamma += 0;
                epsilon +=1;
            }
        }


        gammaRate = Integer.parseInt(gamma,2);
        epsilonRate = Integer.parseInt(epsilon,2);
        powerConsumption = gammaRate*epsilonRate;

        System.out.println("RESULT 1: " + powerConsumption);


    }

    private static void secondTaskDay3(List<String> myList) {

        int lifeRating;
        int oxygenGenRating;
        int co2Rating;

        List<String> oxygen = new ArrayList<>();
        List<String> co2 = new ArrayList<>();

        for (String item : myList) {
            oxygen.add(item);
            co2.add(item);
        }

        calculateOxygen(oxygen);
        calculateCO2(co2);

        oxygenGenRating = Integer.parseInt(oxygen.get(0), 2);
        co2Rating = Integer.parseInt(co2.get(0), 2);
        lifeRating = oxygenGenRating * co2Rating;

        System.out.println("RESULT 2: " + lifeRating);

    }



    private static void calculateOxygen(List<String> oxygen) {

        for (int i = 0; i < oxygen.get(0).length(); i++) {

            if (oxygen.size() == 1) {
                break;
            }
            int finalJ = i;
            if (moreOnes(oxygen, i)) {
                oxygen.removeIf(x -> x.charAt(finalJ) == '0');
            } else {
                oxygen.removeIf(x -> x.charAt(finalJ) == '1');
            }
        }

    }

    private static void calculateCO2(List<String> co2){


        for (int i = 0; i < co2.get(0).length(); i++) {

                if (co2.size() == 1) {
                    break;
                }

                int finalJ = i;
                if(moreOnes(co2, i)) {
                    co2.removeIf(x -> x.charAt(finalJ) == '1');
                } else {
                    co2.removeIf(x -> x.charAt(finalJ) == '0');
                }
            }
        }


    private static boolean moreOnes(List<String> list, int index) {

        int one = 0;
        int zero = 0;

        for (int j = 0; j < list.size(); j++) {

            if (list.get(j).charAt(index) == '1') {
                one++;
            } else {
                zero++;
            }
        }

        return one >= zero ? true : false ;

    }


}









