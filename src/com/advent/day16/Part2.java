package com.advent.day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 16: Ticket Translation ---
 * --- Part Two ---
 * Now that you've identified which tickets contain invalid values, discard those tickets entirely.
 * Use the remaining valid tickets to determine which field is which.
 *
 * Using the valid ranges for each field, determine what order the fields appear on the tickets.
 * The order is consistent between all tickets: if seat is the third field, it is the third field on every ticket, including your ticket.
 *
 * For example, suppose you have the following notes:
 *
 * class: 0-1 or 4-19
 * row: 0-5 or 8-19
 * seat: 0-13 or 16-19
 *
 * your ticket:
 * 11,12,13
 *
 * nearby tickets:
 * 3,9,18
 * 15,1,5
 * 5,14,9
 * Based on the nearby tickets in the above example, the first position must be row,
 * the second position must be class, and the third position must be seat;
 * you can conclude that in your ticket, class is 12, row is 11, and seat is 13.
 *
 * Once you work out which field is which, look for the six fields on your ticket
 * that start with the word departure. What do you get if you multiply those six values together?
 *
 * Your puzzle answer was 1382443095281.
 */
public class Part2 {

    public static List<Parameter> parameters = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv16.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int[] myTicket = new int[20];

            boolean yourTicket = false;
            boolean nearbyTickets = false;
            Pattern patternInterval = Pattern.compile("\\d+-\\d+");
            List<int[]> nearbyTicketsCol = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                Matcher matcherInterval = patternInterval.matcher(line);

                if (!line.equals("")) {
                    if (line.equals("your ticket:")) {
                        yourTicket = true;
                        //continue;
                    } else if (line.equals("nearby tickets:")) {
                        nearbyTickets = true;
                        //continue;
                    } else if (!yourTicket && !nearbyTickets) {
                        // interval
                        String parName = line.substring(0, line.indexOf(':'));
                        Parameter par = new Parameter(parName);
                        while (matcherInterval.find()) {
                            String s = matcherInterval.group();
                            String[] strinterv = s.split("-");
                            Interval interval = new Interval(Integer.parseInt(strinterv[0]), Integer.parseInt(strinterv[1]));
                            par.addInterval(interval);
                        }
                        parameters.add(par);
                    } else if (yourTicket && !nearbyTickets) {
                        // my ticket
                        String[] myTicketString = line.split(",");
                        for (int i = 0; i < myTicketString.length; i++) {
                            myTicket[i] = Integer.parseInt(myTicketString[i]);
                        }
                    } else {
                        // nearby tickets
                        String[] nearbyTicketString = line.split(",");
                        int[] nearbyTicket = new int[20];
                        int i = 0;
                        boolean validLine = true;
                        for (String s : nearbyTicketString) {
                            int num = Integer.parseInt(s);
                            nearbyTicket[i] = num;
                            i++;
                            if (!isNumValid(num)) {
                                validLine = false;
                                break;
                            }
                        }
                        if (validLine) {
                            nearbyTicketsCol.add(nearbyTicket);
                        }
                    }
                }
            }

            // check if ticket data on certain position corresponds with interval of certain parameter
            for (Parameter par : parameters) {
                for (int data = 0; data < 20; data++) {
                    boolean dataCorresponds = true;
                    for (int[] ticket : nearbyTicketsCol) {
                        boolean bool = par.isValid(ticket[data]);
                        if (!bool) {
                            dataCorresponds = false;
                            break;
                        }
                    }
                    if (dataCorresponds) {
                        System.out.println(par.getParamName() + ", muze byt na pozici " + data);
                        par.possiblePositions.add(data);
                    }
                }
            }

            printParamPositions();
            // work out, which data is which (eliminate)
            boolean changed;
            do {
                changed = false;
                for (Parameter par : parameters) {
                    if (par.possiblePositions.size() == 1 && !par.isFinalPosition()) {
                        par.setFinalPosition(true);
                        int findPosition = par.possiblePositions.get(0);
                        for (Parameter p : parameters) {
                            if (!p.isFinalPosition()) {
                                p.possiblePositions.remove(Integer.valueOf(findPosition));
                            }
                        }
                        printParamPositions();
                        changed = true;
                    }
                }
            } while (changed);

            // count product - solution
            long sum2 = 1;
            for (Parameter p : parameters) {
                if (p.getParamName().startsWith("departure")) {
                    sum2 *= myTicket[p.possiblePositions.get(0)];
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("solution part2 = " + sum2);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isNumValid(int number) {
        for (Parameter par : parameters) {
            if (par.isValid(number)) {
                return true;
            }
        }
        return false;
    }

    public static void printParamPositions() {
        System.out.println("---------------------------------------------");
        for (Parameter p : parameters) {
            System.out.print(p.getParamName() + "   ");
            System.out.print(p.possiblePositions.toString());
            System.out.println("    " + p.isFinalPosition());
        }
    }

}
