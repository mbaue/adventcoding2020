package com.advent.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 2: Password Philosophy ---
 * --- Part Two ---
 * While it appears you validated the passwords correctly,
 * they don't seem to be what the Official Toboggan Corporate Authentication System is expecting.
 * <p>
 * The shopkeeper suddenly realizes that he just accidentally explained
 * the password policy rules from his old job at the sled rental place down the street!
 * The Official Toboggan Corporate Policy actually works a little differently.
 * <p>
 * Each policy actually describes two positions in the password, where 1 means the first character,
 * 2 means the second character, and so on.
 * (Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
 * Exactly one of these positions must contain the given letter.
 * Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
 * <p>
 * Given the same example list from above:
 * <p>
 * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
 * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
 * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
 * How many passwords are valid according to the new interpretation of the policies?
 * <p>
 * Your puzzle answer was 509.
 */

public class Part2 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv02.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            List<String> validPasswords = new ArrayList<>();
            List<String> invalidPasswords = new ArrayList<>();

            int first;
            int second;
            char letter;
            char[] password;
            while ((line = br.readLine()) != null) {
                first = Integer.parseInt(line.substring(0, line.indexOf('-')));
                second = Integer.parseInt(line.substring(line.indexOf('-') + 1, line.indexOf(' ')));
                letter = line.charAt(line.indexOf(' ') + 1);
                password = line.substring(line.lastIndexOf(' ') + 1).toCharArray();

                if ((password[first - 1] == letter && password[second - 1] != letter) || (password[first - 1] != letter && password[second - 1] == letter)) {
                    validPasswords.add(line);
                } else {
                    invalidPasswords.add(line);
                }
            }
            System.out.println("platnych hesel: " + validPasswords.size());
            System.out.println("neplatnych hesel: " + invalidPasswords.size());

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("number of valid passwords = " + validPasswords.size());
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
