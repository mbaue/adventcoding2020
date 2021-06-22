package com.advent.day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 4: Passport Processing ---
 * --- Part Two ---
 * The line is moving more quickly now, but you overhear airport security talking
 * about how passports with invalid data are getting through. Better add some data validation, quick!
 * <p>
 * You can continue to ignore the cid field, but each other field has strict rules
 * about what values are valid for automatic validation:
 * <p>
 * byr (Birth Year) - four digits; at least 1920 and at most 2002.
 * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
 * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
 * hgt (Height) - a number followed by either cm or in:
 * If cm, the number must be at least 150 and at most 193.
 * If in, the number must be at least 59 and at most 76.
 * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
 * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
 * pid (Passport ID) - a nine-digit number, including leading zeroes.
 * cid (Country ID) - ignored, missing or not.
 * Your job is to count the passports where all required fields are both present
 * and valid according to the above rules. Here are some example values:
 * <p>
 * byr valid:   2002
 * byr invalid: 2003
 * <p>
 * hgt valid:   60in
 * hgt valid:   190cm
 * hgt invalid: 190in
 * hgt invalid: 190
 * <p>
 * hcl valid:   #123abc
 * hcl invalid: #123abz
 * hcl invalid: 123abc
 * <p>
 * ecl valid:   brn
 * ecl invalid: wat
 * <p>
 * pid valid:   000000001
 * pid invalid: 0123456789
 * Here are some invalid passports:
 * <p>
 * eyr:1972 cid:100
 * hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926
 * <p>
 * iyr:2019
 * hcl:#602927 eyr:1967 hgt:170cm
 * ecl:grn pid:012533040 byr:1946
 * <p>
 * hcl:dab227 iyr:2012
 * ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
 * <p>
 * hgt:59cm ecl:zzz
 * eyr:2038 hcl:74454a iyr:2023
 * pid:3556412378 byr:2007
 * Here are some valid passports:
 * <p>
 * pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
 * hcl:#623a2f
 * <p>
 * eyr:2029 ecl:blu cid:129 byr:1989
 * iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
 * <p>
 * hcl:#888785
 * hgt:164cm byr:2001 iyr:2015 cid:88
 * pid:545766238 ecl:hzl
 * eyr:2022
 * <p>
 * iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
 * Count the number of valid passports - those that have all required fields and valid values.
 * Continue to treat cid as optional. In your batch file, how many passports are valid?
 * <p>
 * Your puzzle answer was 150.
 */

public class Part2 {

    private static boolean checkFields(Map<String, String> psprt) {
        return psprt.containsKey("byr")
                && psprt.containsKey("iyr")
                && psprt.containsKey("eyr")
                && psprt.containsKey("hgt")
                && psprt.containsKey("hcl")
                && psprt.containsKey("ecl")
                && psprt.containsKey("pid");
    }

    private static void processPassport() {
        totalPassports++;
        if (checkFields(passport)) {
            System.out.println("OK, platnych=" + ++validPassports);
        } else {
            System.out.println("ERROR, neplatnych=" + ++invalidPassports);
        }
        System.out.println("********** END OF PASSPORT ************");
        passport.clear();
    }


    static Map<String, String> passport = new HashMap<>();
    static int validPassports = 0;
    static int invalidPassports = 0;
    static int totalPassports = 0;

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv04.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    processPassport();
                } else {
                    String[] a = line.split(" ");
                    for (String s : a) {
                        String[] item = s.split(":");
                        System.out.print(s);
                        switch (item[0]) {
                            case "byr": //birth year
                                Pattern patternByr = Pattern.compile("\\d{4}");
                                Matcher matcherByr = patternByr.matcher(item[1]);
                                if (!(matcherByr.matches() & Integer.parseInt(item[1]) >= 1920 & Integer.parseInt(item[1]) <= 2002)) {
                                    System.out.println(" - invalid byr -----------------------------------------");
                                } else {
                                    System.out.println(" - valid byr");
                                    passport.put(item[0], item[1]);
                                }
                                break;
                            case "iyr": // issue year
                                Pattern patternIyr = Pattern.compile("\\d{4}");
                                Matcher matcherIyr = patternIyr.matcher(item[1]);
                                if (!(matcherIyr.matches() & Integer.parseInt(item[1]) >= 2010 & Integer.parseInt(item[1]) <= 2020)) {
                                    System.out.println(" - invalid iyr -----------------------------------------");
                                } else {
                                    System.out.println(" - valid iyr");
                                    passport.put(item[0], item[1]);
                                }
                                break;
                            case "eyr": // expiration year
                                Pattern patternEyr = Pattern.compile("\\d{4}");
                                Matcher matcherEyr = patternEyr.matcher(item[1]);
                                if (!(matcherEyr.matches() & Integer.parseInt(item[1]) >= 2020 & Integer.parseInt(item[1]) <= 2030)) {
                                    System.out.println(" - invalid eyr --------------------------------------");
                                } else {
                                    System.out.println(" - valid eyr");
                                    passport.put(item[0], item[1]);
                                }
                                break;
                            case "hgt": // height
                                Pattern patternHgt = Pattern.compile("(\\d*)(cm|in)");
                                Matcher matcherHgt = patternHgt.matcher(item[1]);
                                if (matcherHgt.matches() &&
                                        (((Integer.parseInt(matcherHgt.group(1)) >= 150
                                                & Integer.parseInt(matcherHgt.group(1)) <= 193
                                                & matcherHgt.group(2).equals("cm")))
                                                || (Integer.parseInt(matcherHgt.group(1)) >= 59
                                                & Integer.parseInt(matcherHgt.group(1)) <= 76
                                                & matcherHgt.group(2).equals("in")))) {
                                    System.out.println(" - valid hgt");
                                    passport.put(item[0], item[1]);
                                } else {
                                    System.out.println(" - invalid hgt -------------------------------------------");
                                }
                                break;
                            case "hcl": // hair color
                                Pattern patternHcl = Pattern.compile("#[0-9a-f]{6}");
                                Matcher matcherHcl = patternHcl.matcher(item[1]);
                                if (!matcherHcl.matches()) {
                                    System.out.println(" - invalid hcl ----------------------------------------");
                                } else {
                                    System.out.println(" - valid hcl");
                                    passport.put(item[0], item[1]);
                                }
                                break;
                            case "ecl": // eye color
                                Pattern patternEcl = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth");
                                Matcher matcherEcl = patternEcl.matcher(item[1]);
                                if (!matcherEcl.matches()) {
                                    System.out.println(" - invalid ecl -------------------------------------");
                                } else {
                                    System.out.println(" - valid ecl");
                                    passport.put(item[0], item[1]);
                                }
                                break;
                            case "pid": // passport ID
                                Pattern patternPid = Pattern.compile("\\d{9}");
                                Matcher matcherPid = patternPid.matcher(item[1]);
                                if (!matcherPid.matches()) {
                                    System.out.println(" - invalid pid -------------------------------------");
                                } else {
                                    passport.put(item[0], item[1]);
                                    System.out.println(" - valid pid");
                                }
                                break;
                            case "cid": // country ID
                                System.out.println();
                                break;
                        }
                    }
                }
            }

            processPassport(); // last passport does not end with empty line
            //System.out.println("------------------------------------------------------------------------------------");

            System.out.println("invalidPassports=" + invalidPassports);
            System.out.println("totalPassports=" + totalPassports);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("validPassports=" + validPassports);
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
