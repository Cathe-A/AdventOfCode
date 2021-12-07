package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDay5 {


    public static void main(String[] args) {

        try {
            File myObj = new File("input5.txt");
            Scanner myReader = new Scanner(myObj);

            List<String> listFrom = new ArrayList<String>();
            List<String> listTo = new ArrayList<String>();
            int cpt = 0;
            int matrixLength = 1000;

            while (myReader.hasNext()) {
                String data = myReader.next().replaceAll("->", "");

                if (!data.isEmpty()) {
                    if (cpt % 2 == 0) {
                        listFrom.add(data);
                        cpt++;
                    } else {
                        listTo.add(data);
                        cpt--;
                    }
                }
            }
            myReader.close();

            /********************TASKS********************/

            //  firstTaskDay5(listFrom, listTo, matrixLength);
            secondTaskDay5(listFrom, listTo, matrixLength);

            /*******************************************/


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void firstTaskDay5(List<String> listFrom, List<String> listTo, int matrixLength) {


        int x1, x2, y1, y2;
        String[][] tab = new String[matrixLength][matrixLength];

        initializeMatrix(tab);

        for (int i = 0; i < listFrom.size(); i++) {


            String[] str = listFrom.get(i).split(",");
            x1 = Integer.parseInt(str[0]);
            y1 = Integer.parseInt(str[1]);

            String[] str1 = listTo.get(i).split(",");
            x2 = Integer.parseInt(str1[0]);
            y2 = Integer.parseInt(str1[1]);

            if (x1 == x2) {
                drawInMatrix(tab, y1, y2, "y", x1);
            } else if (y1 == y2) {
                drawInMatrix(tab, x1, x2, "x", y1);
            }
        }

        System.out.println("Task1: Stat: " + statistics(tab));

    }

    public static void secondTaskDay5(List<String> listFrom, List<String> listTo, int matrixLength) {

        int x1, x2, y1, y2;
        String[][] tab = new String[matrixLength][matrixLength];

        initializeMatrix(tab);

        for (int i = 0; i < listFrom.size(); i++) {


            String[] str = listFrom.get(i).split(",");
            x1 = Integer.parseInt(str[0]);
            y1 = Integer.parseInt(str[1]);

            String[] str1 = listTo.get(i).split(",");
            x2 = Integer.parseInt(str1[0]);
            y2 = Integer.parseInt(str1[1]);

            if (x1 == x2) {
                drawInMatrix(tab, y1, y2, "y", x1);
            } else if (y1 == y2) {
                drawInMatrix(tab, x1, x2, "x", y1);
            } else {
                drawInDiagonal(tab, x1, x2, y1, y2);
            }
        }

        System.out.println("Task 2. Stat: " + statistics(tab));

    }

    /**
     * Count only numbers that greater than 1
     * @param tab
     * @return
     */
    private static int statistics(String[][] tab) {

        int cpt = 0;

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (!tab[i][j].contains(".") && Integer.parseInt(tab[i][j]) > 1) {
                    cpt++;
                }
            }
        }
        return cpt;
    }

    /**
     * Add numbers to matrix
     * @param tab
     * @param from
     * @param to
     * @param axe
     * @param start
     */
    private static void drawInMatrix(String[][] tab, int from, int to, String axe, int start) {

        if (from > to) {
            int temp = from;
            from = to;
            to = temp;
        }

        if (axe.equals("x")) {

            for (int j = from; j <= to; j++) {
                if (!tab[start][j].equals(".")) {
                    tab[start][j] = String.valueOf(Integer.parseInt(tab[start][j]) + 1);
                } else {
                    tab[start][j] = "1";
                }
            }
        } else {
            for (int j = from; j <= to; j++) {
                if (!tab[j][start].equals(".")) {
                    tab[j][start] = String.valueOf(Integer.parseInt(tab[j][start]) + 1);
                } else {
                    tab[j][start] = "1";
                }
            }
        }
    }


    private static void drawInDiagonal(String[][] tab, int x1, int x2, int y1, int y2) {

        /**
         * To always have the x that increases, so drawing the line by two directions:
         * y increasing or y decreasing
         */
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;

            temp = y1;
            y1 = y2;
            y2 = temp;
        }

        if (y1 > y2) {

            int start = x1;
            for (int i = start; i <= x2; i++) {
                if (!tab[y1][x1].equals(".")) {
                    tab[y1][x1] = String.valueOf(Integer.parseInt(tab[y1][x1]) + 1);
                } else {
                    tab[y1][x1] = "1";
                }
                x1++;
                y1--;
            }
        } else {

            int start = x1;
            for (int i = start; i <= x2; i++) {
                if (!tab[y1][x1].equals(".")) {
                    tab[y1][x1] = String.valueOf(Integer.parseInt(tab[y1][x1]) + 1);
                } else {
                    tab[y1][x1] = "1";
                }
                x1++;
                y1++;
            }

        }

    }

    public static void writeInFile(String[][] tab) throws FileNotFoundException {

        PrintWriter printWriter = new PrintWriter("input5_modif.txt");

        for (String[] str : tab) {
            for (String s : str) {
                printWriter.write(s);
            }
            printWriter.write("\n");
        }
        printWriter.close();
    }


    private static void initializeMatrix(String[][] tab) {

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                tab[i][j] = ".";
            }
        }
    }


    private static void showMatrix(String[][] tab) {

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }


}
