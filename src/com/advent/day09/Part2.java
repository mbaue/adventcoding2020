package com.advent.day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * --- Day 9: Encoding Error ---
 * --- Part Two ---
 * The final step in breaking the XMAS encryption relies on the invalid number you just found:
 * you must find a contiguous set of at least two numbers in your list which sum to the invalid number from step 1.
 *
 * Again consider the above example:
 *
 * 35
 * 20
 * 15
 * 25
 * 47
 * 40
 * 62
 * 55
 * 65
 * 95
 * 102
 * 117
 * 150
 * 182
 * 127
 * 219
 * 299
 * 277
 * 309
 * 576
 * In this list, adding up all of the numbers from 15 through 40 produces the invalid number from step 1, 127.
 * (Of course, the contiguous set of numbers in your actual list might be much longer.)
 *
 * To find the encryption weakness, add together the smallest and largest number in this contiguous range;
 * in this example, these are 15 and 47, producing 62.
 *
 * What is the encryption weakness in your XMAS-encrypted list of numbers?
 *
 * Your puzzle answer was 7409241.
 *
 */

public class Part2 {

    public static final long TARGET = 57195069L;
    public static long[] numbers = new long[1000];

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv09.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Set<Long> solution = new HashSet<>();

            int i = 0;
            while ((line = br.readLine()) != null) {
                numbers[i] = Long.parseLong(line);
                i++;
            }

            long sum;
            for (int startIndex = 0; startIndex < numbers.length; startIndex++) {
                i = startIndex;
                sum = numbers[i];
                while (sum < TARGET) {
                    sum += numbers[++i];
                    if (sum == TARGET) {
                        System.out.println("sekvence hledanych cisel zacina na indexu " + startIndex);
                        System.out.println("sekvence hledanych cisel konci  na indexu " + i);
                        System.out.println("---------------");
                        for (int j = startIndex; j <= i; j++) {
                            System.out.println(numbers[j]);
                            solution.add(numbers[j]);
                        }
                    }
                }
            }
            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println( Collections.min(solution) + Collections.max(solution));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
