package com.advent.day04;

/*
regex debugging
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRE {

    public static void main(String[] args) {
        String s1 = "160524987";
        String s2 = "000115687";
        String s3 = "12";
        String s4 = "1401234567";
        String s5 = "601654789in";
        String s6 = "a70in";
        Pattern pattern = Pattern.compile("\\d{9}");
        Matcher matcher = pattern.matcher(s1);
        System.out.println(matcher.matches());
        matcher = pattern.matcher(s2);
        System.out.println(matcher.matches());
        matcher = pattern.matcher(s3);
        System.out.println(matcher.matches());
        matcher = pattern.matcher(s4);
        System.out.println(matcher.matches());
        matcher = pattern.matcher(s5);
        System.out.println(matcher.matches());
        matcher = pattern.matcher(s6);
        System.out.println(matcher.matches());


    }

}
