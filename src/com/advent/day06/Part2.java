package com.advent.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * --- Day 6: Custom Customs ---
 * --- Part Two ---
 * As you finish the last group's customs declaration,
 * you notice that you misread one word in the instructions:
 *
 * You don't need to identify the questions to which anyone answered "yes";
 * you need to identify the questions to which everyone answered "yes"!
 *
 * Using the same example as above:
 *
 * abc
 *
 * a
 * b
 * c
 *
 * ab
 * ac
 *
 * a
 * a
 * a
 * a
 *
 * b
 * This list represents answers from five groups:
 *
 * In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.
 * In the second group, there is no question to which everyone answered "yes".
 * In the third group, everyone answered yes to only 1 question, a. Since some people did not answer "yes" to b or c, they don't count.
 * In the fourth group, everyone answered yes to only 1 question, a.
 * In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.
 * In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
 *
 * For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?
 *
 * Your puzzle answer was 3288.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv06.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int orderInGroup = 0;
            String prunik = "";
            int solution = 0;
            do {
                line = br.readLine();
                if (line == null || line.isEmpty()) {
                    solution = solution + prunik.length();
                    System.out.println("----------end of group-------------- " +
                            " strings in group = " + orderInGroup +
                            ", prunik = " + prunik +
                            ", pocet znaku = " + prunik.length() +
                            ", solution = " + solution);
                    orderInGroup = 0;
                    prunik = "";
                } else {
                    System.out.println(line);
                    if (orderInGroup > 0) {
                        prunik = intersect(line, prunik);
                    } else {
                        prunik = line;
                    }
                    orderInGroup++;
                }
            } while (line != null);
            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("soucet=" + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String intersect(String line, String previousLine) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (previousLine.indexOf(line.charAt(i)) >= 0) {
                sb.append(line.charAt(i));
            }
        }
        return sb.toString();

    }
}
