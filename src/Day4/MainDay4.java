package Day4;

import java.io.*;
import java.util.*;

public class MainDay4 {

    public static void main(String[] args)  {

        /*
        * Preparation of data
        * */
        try {
            File myObj = new File("input4.txt");
            Scanner myReader = new Scanner(myObj);
            PrintWriter printWriter = new PrintWriter("input4_modif.txt");

            String[] valuesStr = myReader.nextLine().split(",");

            int[] values = new int[valuesStr.length];
            for (int i = 0; i < valuesStr.length; i++) {
                values[i] = Integer.parseInt((valuesStr[i]));
            }


            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                if (!data.isEmpty()) {
                    data = data.replaceAll("\\s+", ",").trim();
                    printWriter.write(data);
                    printWriter.write("\n");
                }
            }

            printWriter.close();
            myReader.close();


            File file = new File("input4_modif.txt");
            Scanner scanner = new Scanner(file);

            //two lists for two tasks
            List<int[][]> myList1 = new ArrayList<>();
            List<int[][]> myList2 = new ArrayList<>();
            int cpt = 0;
            int[][] matrix1 = new int[5][5];
            int[][] matrix2 = new int[5][5];

            while (scanner.hasNextLine()) {

                String[] myData = scanner.nextLine().split(",");

                if (cpt != matrix1.length) {
                    for (int i = 0; i < myData.length; i++) {
                        matrix1[cpt][i] = Integer.parseInt(myData[i]);
                        matrix2[cpt][i] = Integer.parseInt(myData[i]);
                    }
                    cpt++;
                } else {
                    myList1.add(matrix1);
                    myList2.add(matrix2);
                    matrix1 = new int[5][5];
                    matrix2 = new int[5][5];
                    cpt = 0;

                    for (int i = 0; i < myData.length; i++) {
                        matrix1[cpt][i] = Integer.parseInt(myData[i]);
                        matrix2[cpt][i] = Integer.parseInt(myData[i]);
                    }
                    cpt++;
                }
            }
            myList1.add(matrix1);
            myList2.add(matrix2);

            myReader.close();


            /********************TASKS********************/

            int result1 = firstTaskDay4(myList1, values);
            int result2 = secondTaskDay4(myList2, values);

            System.out.println("FINAL RESULT: " + result1);
            System.out.println("FINAL RESULT: " + result2);

            /*******************************************/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Create matrix of matrices
     * Create list of values
     * For each matrix pass through its values
     * If there is this value -> add delta to number
     * Check if there is all ">delta" in row,
     * if not, check if there is all ">delta" in column
     * If yes, calculate the sum of rest in the matrix
     */
    public static int firstTaskDay4(List<int[][]> myList, int[] values) {

        int searchedNum;
        int delta = 1000; //adding the delta to distinguish marked numbers but also to save it's values

        //for each value in the list
        for (int i = 0; i < values.length; i++) {

            searchedNum = values[i];

            //for each matrix of the List
            for (int j = 0; j < myList.size(); j++) {

                int [][] tab;
                int result;
                tab = myList.get(j);

                //for each cell in matrix
                for (int k = 0; k < tab.length; k++) {
                    for (int m = 0; m < tab[0].length; m++) {

                        if (tab[k][m] == searchedNum) {
                            tab[k][m] = tab[k][m] + delta;
                            if(checkIfWinner(tab, k, m, delta)) {
                                result = calculateResult(tab, delta) ;
                                return result * searchedNum;
                            }
                        }
                    }
                }
            }
        }

        return 0;
}

    private static int secondTaskDay4(List<int[][]> myList, int[] values) {

        int searchedNum;
        int delta = 1000;
        int result;



        for (int i = 0; i < values.length; i++) {

            searchedNum = values[i];

            for (int j = 0; j < myList.size(); j++) {
                int [][] tab;
                tab = myList.get(j);

                for (int k = 0; k < tab.length; k++) {
                    for (int m = 0; m < tab[0].length; m++) {

                        if (tab[k][m] == searchedNum) {
                            tab[k][m] = tab[k][m] + delta;
                            if(checkIfWinner(tab, k, m, delta)) {

                                if(myList.size() == 1) {
                                    result = calculateResult(tab, delta) ;
                                    return   result * searchedNum;
                                }
                                //remove the "winner" so the list is stays update
                                myList.remove(myList.get(j));
                                j--;
                            }
                        }
                    }
                }
            }
        }

        return 0;

    }


    private static int calculateResult(int[][] tab, int delta) {

        int sum = 0;

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                    if(tab[i][j] - delta < 0) {
                        sum = sum + tab[i][j];
                    }
            }
        }
        return sum;
    }


    private static boolean checkIfWinner(int[][] tab, int ligne, int col, int delta) {


        int sumHoriz = 0;
        int sumVertic = 0;

        for (int i = 0; i < tab.length; i++) {
            sumHoriz += tab[ligne][i];
            if (sumHoriz - delta * tab.length > 0)
                return true;
        }

        for (int i = 0; i < tab.length; i++) {
            sumVertic += tab[i][col];
            if(sumVertic - delta * tab.length > 0)
                return true;

        }
       return false;

        }

        public static void showMatrix(int[][] mat){

            System.out.println("******");
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    System.out.print(mat[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("******");

        }



    public static void showListOfMatrice(List<int[][]> myList){

            System.out.println("---------LIST OF MATRIX------------------");
            for (int[][] item : myList) {
                for (int[] item1 : item) {
                    for (int item2 : item1) {
                        System.out.print(item2 + " ");
                    }
                    System.out.println();
                }
                System.out.println("-------------------------------------------------------");

            }
        }

}
