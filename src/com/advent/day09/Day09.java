package com.advent.day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2020/day/9
 *
 * --- Day 9: Encoding Error ---
 * With your neighbor happily enjoying their video game, you turn your attention
 * to an open data port on the little screen in the seat in front of you.
 *
 * Though the port is non-standard, you manage to connect it to your computer through the clever use
 * of several paperclips. Upon connection, the port outputs a series of numbers (your puzzle input).
 *
 * The data appears to be encrypted with the eXchange-Masking Addition System (XMAS) which,
 * conveniently for you, is an old cypher with an important weakness.
 *
 * XMAS starts by transmitting a preamble of 25 numbers. After that, each number you receive
 * should be the sum of any two of the 25 immediately previous numbers.
 * The two numbers will have different values, and there might be more than one such pair.
 *
 * For example, suppose your preamble consists of the numbers 1 through 25 in a random order.
 * To be valid, the next number must be the sum of two of those numbers:
 *
 * 26 would be a valid next number, as it could be 1 plus 25 (or many other pairs, like 2 and 24).
 * 49 would be a valid next number, as it is the sum of 24 and 25.
 * 100 would not be valid; no two of the previous 25 numbers sum to 100.
 * 50 would also not be valid; although 25 appears in the previous 25 numbers,
 * the two numbers in the pair must be different.
 * Suppose the 26th number is 45, and the first number (no longer an option,
 * as it is more than 25 numbers ago) was 20. Now, for the next number to be valid,
 * there needs to be some pair of numbers among 1-19, 21-25, or 45 that add up to it:
 *
 * 26 would still be a valid next number, as 1 and 25 are still within the previous 25 numbers.
 * 65 would not be valid, as no two of the available numbers sum to it.
 * 64 and 66 would both be valid, as they are the result of 19+45 and 21+45 respectively.
 * Here is a larger example which only considers the previous 5 numbers (and has a preamble of length 5):
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
 * In this example, after the 5-number preamble, almost every number is the sum
 * of two of the previous 5 numbers; the only number that does not follow this rule is 127.
 *
 * The first step of attacking the weakness in the XMAS data is to find the first number in the list
 * (after the preamble) which is not the sum of two of the 25 numbers before it.
 * What is the first number that does not have this property?
 *
 * Your puzzle answer was 57195069.
 */

public class Day09 {

    public static final int PREAMBLE = 25;
    public static void main(String[] args) {

        try {
            File file = new File("resources/adv09.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            ArrayList<Long> numbers = new ArrayList<>(PREAMBLE+1);
            int i = 0;
            Set<Long> solution = new HashSet<>();

            while ((line = br.readLine()) != null) {
                if (i < PREAMBLE+1) {
                    numbers.add(Long.decode(line));
                } else {
                    System.out.println(numbers.subList(0, 25));
                    System.out.println(numbers.get(25));
                    List<Long> frame = new ArrayList<>(numbers.subList(0, 25));
                    if (!isSumOfPrevious(frame, numbers.get(25))) {
                        solution.add(numbers.get(25));
                    }
                    numbers.remove(0);
                    numbers.add(Long.decode(line));
                }
                i++;
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println(solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isSumOfPrevious(List<Long> list, Long num) {
        List<Long>  testSeq =  list.stream().filter(l -> (l<num)).sorted().collect(Collectors.toList());
        System.out.println(testSeq);
        System.out.println("-------------");

        int lower = 0;
        int higher = testSeq.size()-1;
        while (higher > lower) {
            if (testSeq.get(lower) + testSeq.get(higher) > num) {
                higher--;
            } else if (testSeq.get(lower) + testSeq.get(higher) == num) {
                return true;
            } else {
                lower++;
            }
        }
        return false;
    }
}
