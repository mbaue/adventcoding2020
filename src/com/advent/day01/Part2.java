package com.advent.day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * --- Day 1: Report Repair ---
 * --- Part Two ---
 * The Elves in accounting are thankful for your help;
 * one of them even offers you a starfish coin they had left over from a past vacation.
 * They offer you a second one if you can find three numbers in your expense report that meet the same criteria.
 * <p>
 * Using the above example again, the three entries that sum to 2020 are 979, 366, and 675.
 * Multiplying them together produces the answer, 241861950.
 * <p>
 * In your expense report, what is the product of the three entries that sum to 2020?
 * <p>
 * Your puzzle answer was 265253940.
 */

public class Part2 {
    public static final int TARGET_VALUE = 2020;

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv01.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int solution = 0;

            int[] list = new int[200];
            int i = 0;
            while ((line = br.readLine()) != null) {
                list[i] = Integer.parseInt(line);
                i++;
            }

            int lowerIndex = 0;
            int middleIndex;
            int upperIndex;
            Arrays.sort(list);
            do {
                middleIndex = lowerIndex + 1;
                upperIndex = list.length - 1;
                do {
                    if (list[middleIndex] + list[upperIndex] > TARGET_VALUE - list[lowerIndex]) {
                        upperIndex--;
                    } else if (list[middleIndex] + list[upperIndex] == TARGET_VALUE - list[lowerIndex]) {
                        System.out.println(list[lowerIndex] + " * " + list[middleIndex] + " * " + list[upperIndex] + " = " + (list[lowerIndex] * list[middleIndex] * list[upperIndex]));
                        solution = list[lowerIndex] * list[middleIndex] * list[upperIndex];
                        upperIndex--;
                        middleIndex++;
                    } else {
                        middleIndex++;
                    }
                }
                while (middleIndex < upperIndex);
                lowerIndex++;
            } while (list[lowerIndex] < TARGET_VALUE / 3);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("three numbers multiplied give = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
