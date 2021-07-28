package com.advent.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * --- Part Two ---
 * The shuttle company is running a contest: one gold coin for anyone that can find the earliest
 * timestamp such that the first bus ID departs at that time and each subsequent listed bus ID departs
 * at that subsequent minute. (The first line in your input is no longer relevant.)
 *
 * For example, suppose you have the same list of bus IDs as above:
 *
 * 7,13,x,x,59,x,31,19
 * An x in the schedule means there are no constraints on what bus IDs must depart at that time.
 *
 * This means you are looking for the earliest timestamp (called t) such that:
 *
 * Bus ID 7 departs at timestamp t.
 * Bus ID 13 departs one minute after timestamp t.
 * There are no requirements or restrictions on departures at two or three minutes after timestamp t.
 * Bus ID 59 departs four minutes after timestamp t.
 * There are no requirements or restrictions on departures at five minutes after timestamp t.
 * Bus ID 31 departs six minutes after timestamp t.
 * Bus ID 19 departs seven minutes after timestamp t.
 * The only bus departures that matter are the listed bus IDs at their specific offsets from t.
 * Those bus IDs can depart at other times, and other bus IDs can depart at those times.
 * For example, in the list above, because bus ID 19 must depart seven minutes after the timestamp
 * at which bus ID 7 departs, bus ID 7 will always also be departing with bus ID 19 at seven minutes after timestamp t.
 *
 * In this example, the earliest timestamp at which this occurs is 1068781:
 *
 * time     bus 7   bus 13  bus 59  bus 31  bus 19
 * 1068773    .       .       .       .       .
 * 1068774    D       .       .       .       .
 * 1068775    .       .       .       .       .
 * 1068776    .       .       .       .       .
 * 1068777    .       .       .       .       .
 * 1068778    .       .       .       .       .
 * 1068779    .       .       .       .       .
 * 1068780    .       .       .       .       .
 * 1068781    D       .       .       .       .
 * 1068782    .       D       .       .       .
 * 1068783    .       .       .       .       .
 * 1068784    .       .       .       .       .
 * 1068785    .       .       D       .       .
 * 1068786    .       .       .       .       .
 * 1068787    .       .       .       D       .
 * 1068788    D       .       .       .       D
 * 1068789    .       .       .       .       .
 * 1068790    .       .       .       .       .
 * 1068791    .       .       .       .       .
 * 1068792    .       .       .       .       .
 * 1068793    .       .       .       .       .
 * 1068794    .       .       .       .       .
 * 1068795    D       D       .       .       .
 * 1068796    .       .       .       .       .
 * 1068797    .       .       .       .       .
 * In the above example, bus ID 7 departs at timestamp 1068788 (seven minutes after t).
 * This is fine; the only requirement on that minute is that bus ID 19 departs then, and it does.
 *
 * Here are some other examples:
 *
 * The earliest timestamp that matches the list 17,x,13,19 is 3417.
 * 67,7,59,61 first occurs at timestamp 754018.
 * 67,x,7,59,61 first occurs at timestamp 779210.
 * 67,7,x,59,61 first occurs at timestamp 1261476.
 * 1789,37,47,1889 first occurs at timestamp 1202161486.
 * However, with so many bus IDs in your list, surely the actual earliest timestamp will be larger than 100000000000000!
 *
 * What is the earliest timestamp such that all of the listed bus IDs
 * depart at offsets matching their positions in the list?
 *
 * Your puzzle answer was 552612234243498.
 *
 * --------------------------------------------------
 *
 * řešení za pomocí poznatků o čínské větě o zbytcích:
 * https://cs.wikipedia.org/wiki/%C4%8C%C3%ADnsk%C3%A1_v%C4%9Bta_o_zbytc%C3%ADch
 * http://voho.eu/wiki/cinska-veta-o-zbytcich/
 *
 */

public class Part2 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv13.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            Integer.parseInt(br.readLine());
            String[] parts = br.readLine().split(",");

            ArrayList<Bus> buses = new ArrayList<>();
            for (int i = 0; i < parts.length; i++) {
                if (!parts[i].equals("x")) {
                    buses.add(new Bus(i, Integer.parseInt(parts[i])));
                }
            }

            long modulo = 1L;
            for (Bus bus : buses) {
                modulo *= bus.getInterval();
            }
            System.out.println("modulo = " + modulo);

            long x = 0L;
            long s;
            long t;
            long q;

            for (Bus bus : buses) {
                System.out.println("---------------------------------");
                System.out.println(bus);
                s = modulo / bus.getInterval();
                System.out.println("s = " + s);
                t = getInversionModulo(s, bus.getInterval());
                System.out.println("t = " + t);
                q = s * t;
                System.out.println("q = " + q);
                x += (q * (bus.getInterval() - bus.getDelay()));
            }

            long solution = x % modulo;

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("timestamp = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static int getInversionModulo(long s, int moduloClass) {
        for (int i = 0; i < moduloClass; i++) {
//            System.out.println("i*s = " + i + " * " + s
//                    + " = " + (i * s)
//                    + " : " + moduloClass
//                    + " = " + (i * s) / moduloClass
//                    + ",  zbytek = " + (i * s) % moduloClass);
            if ((s * i) % moduloClass == 1) {
                return i;
            }
        }
        return 0;
    }
}