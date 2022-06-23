package com.advent.day20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://adventofcode.com/2020/day/20
 *
 * --- Day 20: Jurassic Jigsaw ---
 * The high-speed train leaves the forest and quickly carries you south.
 * You can even see a desert in the distance! Since you have some spare time,
 * you might as well see if there was anything interesting in the image the
 * Mythical Information Bureau satellite captured.
 *
 * After decoding the satellite messages, you discover that the data actually
 * contains many small images created by the satellite's camera array.
 * The camera array consists of many cameras; rather than produce a single square image,
 * they produce many smaller square image tiles that need to be reassembled back into a single image.
 *
 * Each camera in the camera array returns a single monochrome image tile with a random unique ID number.
 * The tiles (your puzzle input) arrived in a random order.
 *
 * Worse yet, the camera array appears to be malfunctioning: each image tile has been rotated and
 * flipped to a random orientation. Your first task is to reassemble the original image
 * by orienting the tiles so they fit together.
 *
 * To show how the tiles should be reassembled, each tile's image data includes a border that
 * should line up exactly with its adjacent tiles. All tiles have this border, and the border
 * lines up exactly when the tiles are both oriented correctly. Tiles at the edge of the image
 * also have this border, but the outermost edges won't line up with any other tiles.
 *
 * For example, suppose you have the following nine tiles:
 *
 * Tile 2311:
 * ..##.#..#.
 * ##..#.....
 * #...##..#.
 * ####.#...#
 * ##.##.###.
 * ##...#.###
 * .#.#.#..##
 * ..#....#..
 * ###...#.#.
 * ..###..###
 *
 * Tile 1951:
 * #.##...##.
 * #.####...#
 * .....#..##
 * #...######
 * .##.#....#
 * .###.#####
 * ###.##.##.
 * .###....#.
 * ..#.#..#.#
 * #...##.#..
 *
 * Tile 1171:
 * ####...##.
 * #..##.#..#
 * ##.#..#.#.
 * .###.####.
 * ..###.####
 * .##....##.
 * .#...####.
 * #.##.####.
 * ####..#...
 * .....##...
 *
 * Tile 1427:
 * ###.##.#..
 * .#..#.##..
 * .#.##.#..#
 * #.#.#.##.#
 * ....#...##
 * ...##..##.
 * ...#.#####
 * .#.####.#.
 * ..#..###.#
 * ..##.#..#.
 *
 * Tile 1489:
 * ##.#.#....
 * ..##...#..
 * .##..##...
 * ..#...#...
 * #####...#.
 * #..#.#.#.#
 * ...#.#.#..
 * ##.#...##.
 * ..##.##.##
 * ###.##.#..
 *
 * Tile 2473:
 * #....####.
 * #..#.##...
 * #.##..#...
 * ######.#.#
 * .#...#.#.#
 * .#########
 * .###.#..#.
 * ########.#
 * ##...##.#.
 * ..###.#.#.
 *
 * Tile 2971:
 * ..#.#....#
 * #...###...
 * #.#.###...
 * ##.##..#..
 * .#####..##
 * .#..####.#
 * #..#.#..#.
 * ..####.###
 * ..#.#.###.
 * ...#.#.#.#
 *
 * Tile 2729:
 * ...#.#.#.#
 * ####.#....
 * ..#.#.....
 * ....#..#.#
 * .##..##.#.
 * .#.####...
 * ####.#.#..
 * ##.####...
 * ##..#.##..
 * #.##...##.
 *
 * Tile 3079:
 * #.#.#####.
 * .#..######
 * ..#.......
 * ######....
 * ####.#..#.
 * .#...#.##.
 * #.#####.##
 * ..#.###...
 * ..#.......
 * ..#.###...
 * By rotating, flipping, and rearranging them, you can find a square arrangement
 * that causes all adjacent borders to line up:
 *
 * #...##.#.. ..###..### #.#.#####.
 * ..#.#..#.# ###...#.#. .#..######
 * .###....#. ..#....#.. ..#.......
 * ###.##.##. .#.#.#..## ######....
 * .###.##### ##...#.### ####.#..#.
 * .##.#....# ##.##.###. .#...#.##.
 * #...###### ####.#...# #.#####.##
 * .....#..## #...##..#. ..#.###...
 * #.####...# ##..#..... ..#.......
 * #.##...##. ..##.#..#. ..#.###...
 *
 * #.##...##. ..##.#..#. ..#.###...
 * ##..#.##.. ..#..###.# ##.##....#
 * ##.####... .#.####.#. ..#.###..#
 * ####.#.#.. ...#.##### ###.#..###
 * .#.####... ...##..##. .######.##
 * .##..##.#. ....#...## #.#.#.#...
 * ....#..#.# #.#.#.##.# #.###.###.
 * ..#.#..... .#.##.#..# #.###.##..
 * ####.#.... .#..#.##.. .######...
 * ...#.#.#.# ###.##.#.. .##...####
 *
 * ...#.#.#.# ###.##.#.. .##...####
 * ..#.#.###. ..##.##.## #..#.##..#
 * ..####.### ##.#...##. .#.#..#.##
 * #..#.#..#. ...#.#.#.. .####.###.
 * .#..####.# #..#.#.#.# ####.###..
 * .#####..## #####...#. .##....##.
 * ##.##..#.. ..#...#... .####...#.
 * #.#.###... .##..##... .####.##.#
 * #...###... ..##...#.. ...#..####
 * ..#.#....# ##.#.#.... ...##.....
 * For reference, the IDs of the above tiles are:
 *
 * 1951    2311    3079
 * 2729    1427    2473
 * 2971    1489    1171
 * To check that you've assembled the image correctly, multiply the IDs of the four corner tiles together.
 * If you do this with the assembled tiles from the example above, you get 1951 * 3079 * 2971 * 1171 = 20899048083289.
 *
 * Assemble the tiles into an image. What do you get if you multiply together the IDs of the four corner tiles?
 *
 * Your puzzle answer was 17250897231301.
 */
public class Day20 {

    private static final List<Tile> tiles = new ArrayList<>();
    private static final Map<Integer, Integer> stringsMap = new HashMap<>();

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv20.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            long solution = 1;

            // parse input, create list of tiles with their edge values stored
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Tile")) {
                    int tileNum = Integer.parseInt(line.substring(5, 9));
                    String[] znaky = new String[10];
                    for (int i = 0; i < Tile.TILE_SIZE; i++) {
                        znaky[i] = br.readLine().replace("#", "0").replace(".", "1");
                    }
                    Tile t = new Tile(tileNum, znaky);
                    tiles.add(t);

                    // vyplnit tabulku cetnosti - kolikrat se vyskytuje nektera hran
                    for (int i = 0; i < Tile.EDGES_COUNT; i++) {
                        addToMap(Tile.minFromPair(t.getEdge(i)));
                    }
                }
            }
            // podle vytvorene tabulky cetnosti nastavit jednotlivym dlazdicim pocet jejich existujicich sousedu
            for (Tile t : tiles) {
                for (int i = 0; i < Tile.EDGES_COUNT; i++) {
                    int key = Tile.minFromPair(t.getEdge(i));
                    if (stringsMap.get(key) > 1) {
                        t.incrEdgesWithNeighbor();
                    }
                }
                if (t.getEdgesWithNeighbor() == 2) {
                    t.printTile();
                    solution *= t.getTileNumber();
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("solution = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addToMap(int num) {
        if (stringsMap.containsKey(num)) {
            int cetnost = stringsMap.get(num) + 1;
            stringsMap.replace(num, cetnost);
        } else {
            stringsMap.put(num, 1);
        }
    }
}
