package com.advent.day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 * --- Day 10: Adapter Array ---
 * --- Part Two ---
 * To completely determine whether you have enough adapters, you'll need to figure out
 * how many different ways they can be arranged. Every arrangement needs to connect
 * the charging outlet to your device. The previous rules about when adapters can successfully connect still apply.
 *
 * The first example above (the one that starts with 16, 10, 15) supports the following arrangements:
 *
 * (0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)
 * (0), 1, 4, 5, 6, 7, 10, 12, 15, 16, 19, (22)
 * (0), 1, 4, 5, 7, 10, 11, 12, 15, 16, 19, (22)
 * (0), 1, 4, 5, 7, 10, 12, 15, 16, 19, (22)
 * (0), 1, 4, 6, 7, 10, 11, 12, 15, 16, 19, (22)
 * (0), 1, 4, 6, 7, 10, 12, 15, 16, 19, (22)
 * (0), 1, 4, 7, 10, 11, 12, 15, 16, 19, (22)
 * (0), 1, 4, 7, 10, 12, 15, 16, 19, (22)
 * (The charging outlet and your device's built-in adapter are shown in parentheses.)
 * Given the adapters from the first example, the total number of arrangements
 * that connect the charging outlet to your device is 8.
 *
 * The second example above (the one that starts with 28, 33, 18) has many arrangements.
 * Here are a few:
 *
 * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
 * 32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 48, 49, (52)
 *
 * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
 * 32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 49, (52)
 *
 * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
 * 32, 33, 34, 35, 38, 39, 42, 45, 46, 48, 49, (52)
 *
 * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
 * 32, 33, 34, 35, 38, 39, 42, 45, 46, 49, (52)
 *
 * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
 * 32, 33, 34, 35, 38, 39, 42, 45, 47, 48, 49, (52)
 *
 * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
 * 46, 48, 49, (52)
 *
 * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
 * 46, 49, (52)
 *
 * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
 * 47, 48, 49, (52)
 *
 * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
 * 47, 49, (52)
 *
 * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
 * 48, 49, (52)
 * In total, this set of adapters can connect the charging outlet to your device in 19208 distinct arrangements.
 *
 * You glance back down at your bag and try to remember why you brought so many adapters;
 * there must be more than a trillion valid ways to arrange them!
 * Surely, there must be an efficient way to count the arrangements.
 *
 * What is the total number of distinct ways you can arrange the adapters to connect the charging outlet to your device?
 *
 * Your puzzle answer was 31581162962944.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv10.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int[] joltages = new int[105];

            int i = 0;
            while ((line = br.readLine()) != null) {
                joltages[i] = Integer.parseInt(line);
                i++;
            }
            joltages[i++] = 0;
            joltages[i] = 170;

            for (i = 0; i < joltages.length; i++) {
                System.out.print(joltages[i] + ", ");
            }
            System.out.println();

            Arrays.sort(joltages);

            int[][] jolt = new int[105][2];
            jolt[0][0] = joltages[0];
            jolt[0][1] = 1;
            for (i = 1; i < joltages.length; i++) {
                jolt[i][0] = joltages[i];
                if (jolt[i][0] - jolt[i - 1][0] == 3) {
                    jolt[i][1] = 1;
                    jolt[i - 1][1] = 1;
                }
            }
            System.out.print("joltages: ");
            for (i = 0; i < joltages.length; i++) {
                System.out.print(jolt[i][0] + ", ");
            }
            System.out.println();
            System.out.print("differences: ");
            for (i = 0; i < joltages.length; i++) {
                System.out.print(jolt[i][1] + ", ");
            }
            System.out.println();

            int elementsInRow = 0;
            int[] counts = new int[10];
            for (i = 1; i < joltages.length; i++) {
                if (jolt[i][1] == 0) {
                    elementsInRow++;
                } else {
                    if (elementsInRow > 0) {
                        System.out.print(elementsInRow + ", ");
                    }
                    counts[elementsInRow]++;
                    elementsInRow = 0;
                }
            }
            System.out.println();

            for (i = 0; i < counts.length; i++) {
                if (i != 0 && counts[i] != 0) {
                    System.out.print(i + ": " + counts[i] + ", ");
                }
            }
            System.out.println();

            double result = Math.pow(2, counts[1]) * Math.pow(4, counts[2]) * Math.pow(7, counts[3]);

            String s = String.format("%.0f", result);
            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println(s);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
