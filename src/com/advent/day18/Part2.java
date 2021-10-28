package com.advent.day18;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * --- Day 18: Operation Order ---
 * --- Part Two ---
 * You manage to answer the child's questions and they finish part 1 of their homework, but get stuck when they reach the next section: advanced math.
 *
 * Now, addition and multiplication have different precedence levels, but they're not the ones you're familiar with. Instead, addition is evaluated before multiplication.
 *
 * For example, the steps to evaluate the expression 1 + 2 * 3 + 4 * 5 + 6 are now as follows:
 *
 * 1 + 2 * 3 + 4 * 5 + 6
 * 3   * 3 + 4 * 5 + 6
 * 3   *   7   * 5 + 6
 * 3   *   7   *  11
 * 21       *  11
 * 231
 * Here are the other examples from above:
 *
 * 1 + (2 * 3) + (4 * (5 + 6)) still becomes 51.
 * 2 * 3 + (4 * 5) becomes 46.
 * 5 + (8 * 3 + 9 + 3 * 4 * 3) becomes 1445.
 * 5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)) becomes 669060.
 * ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 becomes 23340.
 * What do you get if you add up the results of evaluating the homework problems using these new rules?
 *
 * Your puzzle answer was 328920644404583.
 */

public class Part2 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv18.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int sum = 0;
            long homeworkSolution = 0;

            while ((line = br.readLine()) != null) {
                sum++;

                System.out.println("------------------------------------------------------");
                System.out.println("cislo radku " + sum);
                System.out.println(line);
                Expression2 expr = new Expression2(line);
                long num = expr.processExpression();
                System.out.println(num);
                homeworkSolution += num;
                System.out.println(homeworkSolution);
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("solution = " + homeworkSolution);
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
