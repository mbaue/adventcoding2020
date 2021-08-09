package com.advent.day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * --- Day 11: Seating System ---
 * --- Part Two ---
 * As soon as people start to arrive, you realize your mistake.
 * People don't just care about adjacent seats - they care about the first seat
 * they can see in each of those eight directions!
 *
 * Now, instead of considering just the eight immediately adjacent seats,
 * consider the first seat in each of those eight directions.
 * For example, the empty seat below would see eight occupied seats:
 *
 * .......#.
 * ...#.....
 * .#.......
 * .........
 * ..#L....#
 * ....#....
 * .........
 * #........
 * ...#.....
 * The leftmost empty seat below would only see one empty seat, but cannot see any of the occupied ones:
 *
 * .............
 * .L.L.#.#.#.#.
 * .............
 * The empty seat below would see no occupied seats:
 *
 * .##.##.
 * #.#.#.#
 * ##...##
 * ...L...
 * ##...##
 * #.#.#.#
 * .##.##.
 * Also, people seem to be more tolerant than you expected: it now takes five or more visible occupied seats
 * for an occupied seat to become empty (rather than four or more from the previous rules).
 * The other rules still apply: empty seats that see no occupied seats become occupied,
 * seats matching no rule don't change, and floor never changes.
 *
 * Given the same starting layout as above, these new rules cause the seating area to shift around as follows:
 *
 * L.LL.LL.LL
 * LLLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLLL
 * L.LLLLLL.L
 * L.LLLLL.LL
 * #.##.##.##
 * #######.##
 * #.#.#..#..
 * ####.##.##
 * #.##.##.##
 * #.#####.##
 * ..#.#.....
 * ##########
 * #.######.#
 * #.#####.##
 * #.LL.LL.L#
 * #LLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLL#
 * #.LLLLLL.L
 * #.LLLLL.L#
 * #.L#.##.L#
 * #L#####.LL
 * L.#.#..#..
 * ##L#.##.##
 * #.##.#L.##
 * #.#####.#L
 * ..#.#.....
 * LLL####LL#
 * #.L#####.L
 * #.L####.L#
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##LL.LL.L#
 * L.LL.LL.L#
 * #.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##L#.#L.L#
 * L.L#.#L.L#
 * #.L####.LL
 * ..#.#.....
 * LLL###LLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##L#.#L.L#
 * L.L#.LL.L#
 * #.LLLL#.LL
 * ..#.L.....
 * LLL###LLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 * Again, at this point, people stop shifting around and the seating area reaches equilibrium.
 * Once this occurs, you count 26 occupied seats.
 *
 * Given the new visibility method and the rule change for occupied seats becoming empty,
 * once equilibrium is reached, how many seats end up occupied?
 *
 * Your puzzle answer was 1944.
 */



public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv11.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int lineNum = 0;
            WaitingRoom actualWR = new WaitingRoom();
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    actualWR.seats[lineNum][col] = line.charAt(col);
                }
                lineNum++;
            }

            System.out.println("----------- cekarna v init stavu");
            actualWR.printWaitingRoomPartial();

            WaitingRoom prevWR = new WaitingRoom();
            int round = 0;
            while (actualWR.isDifferentFrom(prevWR)) {
                round++;
                prevWR.copyValuesFrom(actualWR);
                prevWR.evaluateNextRoundEnhanced(actualWR);
                System.out.println("------------" + round + ". kolo -------------------------");
                actualWR.printWaitingRoomPartial();
                System.out.println("occupied seats = " + actualWR.countOccupiedSeats());
                System.out.println();
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("occupied seats after final round = " + actualWR.countOccupiedSeats());
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}