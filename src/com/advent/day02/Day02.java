package com.advent.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://adventofcode.com/2020/day/2
 * <p>
 * --- Day 2: Password Philosophy ---
 * Your flight departs in a few days from the coastal airport;
 * the easiest way down to the coast from here is via toboggan.
 * <p>
 * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day.
 * "Something's wrong with our computers; we can't log in!" You ask if you can take a look.
 * <p>
 * Their password database seems to be a little corrupted:
 * some of the passwords wouldn't have been allowed by
 * the Official Toboggan Corporate Policy that was in effect when they were chosen.
 * <p>
 * To try to debug the problem, they have created a list (your puzzle input) of passwords
 * (according to the corrupted database) and the corporate policy when that password was set.
 * <p>
 * For example, suppose you have the following list:
 * <p>
 * 1-3 a: abcde
 * 1-3 b: cdefg
 * 2-9 c: ccccccccc
 * Each line gives the password policy and then the password.
 * The password policy indicates the lowest and highest number of times a given letter
 * must appear for the password to be valid. For example, 1-3 a means
 * that the password must contain a at least 1 time and at most 3 times.
 * <p>
 * In the above example, 2 passwords are valid. The middle password, cdefg, is not;
 * it contains no instances of b, but needs at least 1. The first and third passwords are valid:
 * they contain one a or nine c, both within the limits of their respective policies.
 * <p>
 * How many passwords are valid according to their policies?
 * <p>
 * Your puzzle answer was 434.
 */

public class Day02 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv02.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            List<String> validPasswords = new ArrayList<>();
            List<String> invalidPasswords = new ArrayList<>();

            int min;
            int max;
            char letter;
            char[] password;
            while ((line = br.readLine()) != null) {
                min = Integer.parseInt(line.substring(0, line.indexOf('-')));
                max = Integer.parseInt(line.substring(line.indexOf('-') + 1, line.indexOf(' ')));
                letter = line.charAt(line.indexOf(' ') + 1);
                password = line.substring(line.lastIndexOf(' ') + 1).toCharArray();
                int count = 0;
                for (char c : password) {
                    if (c == letter) {
                        count++;
                    }
                }
                if (min <= count & count <= max) {
                    validPasswords.add(line);
                } else {
                    invalidPasswords.add(line);
                }
            }
            System.out.println("pocet platnych hesel = " + validPasswords.size());
            System.out.println("pocet neplatnych hesel = " + invalidPasswords.size());

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("number of valid passwords = " + validPasswords.size());
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
