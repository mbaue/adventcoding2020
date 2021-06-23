package com.advent.day06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        String s1 = "tvruzbysjdflicehwkm";
        String s2 = "hswlezrvxjbtckfyim";
        String s3 = "lzkjcbsfmetihuvyr";
        String s4 = "rkjibhvylfscemzt";
        String s5 = "zjctmrhsegfklnibyv";

        char[] cha1 = s1.toCharArray();
        char[] cha2 = s2.toCharArray();
        char[] cha3 = s3.toCharArray();
        char[] cha4 = s4.toCharArray();
        char[] cha5 = s5.toCharArray();

        Arrays.sort(cha1);
        Arrays.sort(cha2);
        Arrays.sort(cha3);
        Arrays.sort(cha4);
        Arrays.sort(cha5);

        System.out.println(cha1);
        System.out.println(cha2);
        System.out.println(cha3);
        System.out.println(cha4);
        System.out.println(cha5);

        Set<Character> set = new HashSet<>();
        for (char c : cha1) {
            set.add(c);
        }

        List<Character> inters = set.stream().filter(c -> s2.contains(c.toString())).collect(Collectors.toList());
        System.out.println(inters.toString());
        inters = set.stream().filter(c -> s3.contains(c.toString())).collect(Collectors.toList());
        System.out.println(inters.toString());
        inters = set.stream().filter(c -> s4.contains(c.toString())).collect(Collectors.toList());
        System.out.println(inters.toString());
        inters = set.stream().filter(c -> s5.contains(c.toString())).collect(Collectors.toList());
        System.out.println(inters.toString());
        System.out.println(inters.size());

        inters.clear();
        System.out.println(inters.size());
    }
}
