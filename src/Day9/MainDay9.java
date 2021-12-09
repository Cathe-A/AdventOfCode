package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainDay9 {

    public static void main(String[] args) {

        try {
            File myObj = new File("input9.txt");
            Scanner myReader = new Scanner(myObj);
            List<String> myList = new ArrayList<>();

            int line = 0;

            int [][] tab = new int[100][100];

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                for (int i = 0; i < data.length(); i++) {
                    tab[line][i] = Integer.parseInt(String.valueOf(data.charAt(i)));
                }

                myList.add(data);
                line++;
            }


            /********************TASKS********************/

                     firstTaskDay9(tab);
                    secondTaskDay9(tab);

            /*******************************************/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void firstTaskDay9(int[][] mat) {

        int[][] tab = addTenToBoards(mat);

        int risk = 0;

        for (int i = 1; i < tab.length - 1; i++) {
            for (int j = 1; j < tab[0].length - 1; j++) {
                int current = tab[i][j];
                int up = tab[i - 1][j];
                int down = tab[i + 1][j];
                int left = tab[i][j - 1];
                int right = tab[i][j + 1];

                if (current < up && current < down && current < left && current < right) {
                    risk += (1 + current);
                }
            }

        }
        System.out.println("Task 1. TOTAL: " + risk );


    }


    public static void secondTaskDay9(int[][] mat) {

        int[][] tab = addTenToBoards(mat);

        List<Integer> allRes = new ArrayList<>();

        for (int i = 1; i < tab.length - 1; i++) {
            for (int j = 1; j < tab[0].length - 1; j++) {
                int current = tab[i][j];
                int up = tab[i - 1][j];
                int down = tab[i + 1][j];
                int left = tab[i][j - 1];
                int right = tab[i][j + 1];

                if (current < up && current < down && current < left && current < right) {
                   int res =  findSolution(tab, i, j,   Step.FIRST);
                    allRes.add(res);
                }
            }
        }

        Collections.sort(allRes);
        int finalResult = 1;

        for (int i =  allRes.size() - 3; i < allRes.size(); i++) {
            finalResult *= allRes.get(i);
        }
        System.out.println("Task 2. TOTAL: "  + finalResult);

    }

    private static int findSolution(int[][] tab, int line, int col, Step lastMove) {

        int total = 0;


        if (possiblePutNumber(tab, line, col)) {
            tab[line][col] = 10; //to not return after to the same cell
                total++;

            //Up
            if (lastMove != Step.DOWN) {
                total += findSolution(tab,  line - 1, col, Step.UP);
            }
            //LEFT
            if (lastMove != Step.RIGHT) {
                total += findSolution(tab,  line, col - 1, Step.LEFT);

            }
            //DOWN
            if (lastMove != Step.UP) {
                total +=  findSolution(tab, line + 1, col, Step.DOWN);

            }
            //RIGHT
            if (lastMove != Step.LEFT) {
                total += findSolution(tab,  line, col + 1, Step.RIGHT);

            }
        }
        return total;


    }

    private static boolean possiblePutNumber(int[][] tab, int line, int col) {

        return tab[line][col] <= 8 ;
    }








    /**
     *Add ten to boards so we don't need to manage bounds
     * @param tab
     * @return
     */
    private static int[][] addTenToBoards(int[][] tab) {

        int[][] newMat = new int[tab.length + 2][tab[0].length + 2];

        for (int i = 0; i < newMat.length; i++) {
            for (int j = 0; j < newMat[0].length; j++) {
                newMat[i][j] = 10;
            }
        }

        int lin = 1, col = 1;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                newMat[lin][col++] = tab[i][j];
            }
            col = 1;
            lin++;
        }
        return newMat;
    }



    public static void showMatrix(int[][] mat) {

        System.out.println("******");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("******");

    }


    public enum Step {
        UP, LEFT, RIGHT, DOWN, FIRST
    }


}
