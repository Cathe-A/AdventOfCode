package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainDay8 {
    public static void main(String[] args) {


        try {
            File myObj = new File("input8.txt");
            Scanner myReader = new Scanner(myObj);

            List<String[]> myListFull = new ArrayList<>();
            List<String[]> myListFirstPart = new ArrayList<>();
            List<String[]> myList3SecondPart = new ArrayList<>();

            while (myReader.hasNextLine()) {

                String line = myReader.nextLine().replaceAll("\\|", "");
                String[] data = line.replaceAll("\\s+", " ").trim().split(" ");

                String[] data2 = new String[10];
                for (int i = 0; i < data.length - 4; i++) {
                    data2[i] = data[i];
                }

                String[] data3 = new String[4];
                int cpt = 0;
                for (int i = data.length - 4; i < data.length; i++) {
                    data3[cpt++] = data[i];

                }
                myListFull.add(data);
                myListFirstPart.add(data2);
                myList3SecondPart.add(data3);
            }

            myReader.close();


            /********************TASKS********************/

            firstTaskDay8(myListFull);
            secondTaskDay8(myListFirstPart, myList3SecondPart);

            /*******************************************/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void secondTaskDay8(List<String[]> myList, List<String[]> myListFourDigit) {

        int total = 0;

        for (int i = 0; i < myList.size(); i++) {
            String[] listStrings = myList.get(i);
            total += calculateDigits(listStrings, myListFourDigit.get(i));
        }

        System.out.println("TASK2. TOTAL : " + total);

    }


    public static void firstTaskDay8(List<String[]> myList) {


        int cpt = 0;

        for (String[] listStrings : myList) {
            for (int k = listStrings.length - 1; k > 9; k--) {
                if (listStrings[k].length() == 2 || listStrings[k].length() == 4
                        || listStrings[k].length() == 3 || listStrings[k].length() == 7) {
                    cpt++;
                }
            }
        }
        System.out.println("TASK1. TOTAL: " + cpt);

    }

    public static int calculateDigits(String[] listString, String[] fourDigits) {


        String[][] result = new String[10][2];
        Arrays.sort(listString, Comparator.comparingInt(String::length));

        String seven = listString[1];
        String four = listString[2];


        int size;
        String nine = "";
        for (int i = listString.length - 1; i >= 0; i--) {

            size = listString[i].length();

            switch (size) {
                case 2:
                    result[i][1] = "1";
                    break;
                case 3:
                    result[i][1] = "7";
                    break;
                case 4:
                    result[i][1] = "4";
                    break;
                case 5:
                    if (compareIfContains(seven, listString[i])) {
                        result[i][1] = "3";
                        break;
                    } else if (compareIfContains(listString[i], nine)) {
                        result[i][1] = "5";
                        break;
                    } else {
                        result[i][1] = "2";
                        break;
                    }
                case 6:
                    if (compareIfContains(seven, listString[i])) {
                        if (compareIfContains(four, listString[i])) {
                            result[i][1] = "9";
                            nine = listString[i];
                        } else {
                            result[i][1] = "0";
                        }
                    } else {
                        result[i][1] = "6";
                    }
                    break;
                case 7:
                    result[i][1] = "8";
            }//endswitch

            result[i][0] = listString[i];
        }

        return calculateSumOfLine(result, fourDigits);

    }

    private static int calculateSumOfLine(String[][] result, String[] fourDigits) {

        StringBuilder outputValue = new StringBuilder();

        for (int i = 0; i < fourDigits.length; i++) {
            for (int j = 0; j < result.length; j++) {

                String str1 = Stream.of(fourDigits[i].split(""))
                        .sorted().collect(Collectors.joining());
                String str2 = Stream.of(result[j][0].split(""))
                        .sorted().collect(Collectors.joining());

                fourDigits[i] = str1;
                result[j][0] = str2;

                if (str1.equals(str2)) {
                    outputValue.append(result[j][1]);
                }
            }
        }

        return Integer.parseInt(outputValue.toString());

    }


    private static boolean compareIfContains(String str1, String str2) {

        int cpt = 0;

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str2.charAt(j) == str1.charAt(i)) {
                    cpt++;
                }
            }
        }
        return cpt == str1.length();
    }


    private static void showList(List<String[]> myList) {

        for (String[] item : myList) {
            for (String str : item) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }


    private static void showListString(String[] myList) {

        for (String str : myList) {
            System.out.print(str + " ");
        }
        System.out.println("-----------------");
    }


}



