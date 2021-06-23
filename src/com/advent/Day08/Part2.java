package com.advent.Day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * --- Day 8: Handheld Halting ---
 * --- Part Two ---
 * After some careful analysis, you believe that exactly one instruction is corrupted.
 *
 * Somewhere in the program, either a jmp is supposed to be a nop, or a nop is supposed to be a jmp.
 * (No acc instructions were harmed in the corruption of this boot code.)
 *
 * The program is supposed to terminate by attempting to execute an instruction immediately
 * after the last instruction in the file. By changing exactly one jmp or nop,
 * you can repair the boot code and make it terminate correctly.
 *
 * For example, consider the same program from above:
 *
 * nop +0
 * acc +1
 * jmp +4
 * acc +3
 * jmp -3
 * acc -99
 * acc +1
 * jmp -4
 * acc +6
 * If you change the first instruction from nop +0 to jmp +0, it would create a single-instruction infinite loop,
 * never leaving that instruction. If you change almost any of the jmp instructions,
 * the program will still eventually find another jmp instruction and loop forever.
 *
 * However, if you change the second-to-last instruction (from jmp -4 to nop -4),
 * the program terminates! The instructions are visited in this order:
 *
 * nop +0  | 1
 * acc +1  | 2
 * jmp +4  | 3
 * acc +3  |
 * jmp -3  |
 * acc -99 |
 * acc +1  | 4
 * nop -4  | 5
 * acc +6  | 6
 * After the last instruction (acc +6), the program terminates by attempting to run the instruction
 * below the last instruction in the file. With this change, after the program terminates,
 * the accumulator contains the value 8 (acc +1, acc +1, acc +6).
 *
 * Fix the program so that it terminates normally by changing exactly one jmp (to nop) or nop (to jmp).
 * What is the value of the accumulator after the program terminates?
 *
 * Your puzzle answer was 733.
 */

public class Part2 {

    private static String[][] instructions = new String[650][3];
    private static StringBuffer solution = new StringBuffer();

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv08.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int i = 0;
            int jmpCount = 0;
            int nopCount = 0;
            while ((line = br.readLine()) != null) {
                instructions[i][0] = line.split(" ")[0];
                instructions[i][1] = line.split(" ")[1];
                instructions[i][2] = "N";
                if (instructions[i][0].equals("nop")) {
                    nopCount++;
                }
                if (instructions[i][0].equals("jmp")) {
                    jmpCount++;
                }
                i++;
            }
            System.out.println("nalezeno " + nopCount +" instrukci nop a "+ jmpCount + " instrukci jmp");

            // postupne projit vsechny operace a vyzkouset cyklus pro nahrazeni - j index nahrazovani nop a jmp
            for (int j = 0; j < 641; j++) {
                if (!instructions[j][0].equals("acc")) {
                    switchInstructions(j);

                    i = 1;
                    int accumulator = 0;
                    // provadeni sekvence instrukci
                    while (i <= 641 && instructions[i - 1][2].equals("N")) {
                        instructions[i - 1][2] = "Y";
                        switch (instructions[i - 1][0]) {
                            case "acc" -> {
                                accumulator += Integer.parseInt(instructions[i - 1][1]);
                                i++;
                            }
                            case "jmp" -> i += Integer.parseInt(instructions[i - 1][1]);
                            case "nop" -> i++;
                        }
                    }
                    // program zakoncen posledni instrukci?
                    if (i == 642) {
                        solution.append("changed instr on line ")
                                .append(j + 1)
                                .append(" changed to ")
                                .append(instructions[j][0])
                                .append(" ")
                                .append(instructions[j][1]);
                        solution.append("\n      accumulator = " + accumulator);
                        System.out.println("+++++++++++++++++++++++++++++++++++++accumulator=" + accumulator);
                    }
                    // vynulovat tag pouziti instrukce pro dalsi pokus
                    for (int k = 0; k < 641; k++) {
                        instructions[k][2] = "N";
                    }
                    switchInstructions(j);
                }
            }

            System.out.println("----------------------------SOLUTION------------------------------");
            System.out.println(solution);
            System.out.println("------------------------------------------------------------------");
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void switchInstructions(int j) {
        if (instructions[j][0].equals("nop")) {
            instructions[j][0] = "jmp";
        } else {
            instructions[j][0] = "nop";
        }
    }
}
