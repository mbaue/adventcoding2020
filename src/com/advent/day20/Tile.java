package com.advent.day20;

public class Tile {

    private final int tileNumber;
    private final String[] edges;
    private int edgesWithNeighbor;
    public static final int TILE_SIZE = 10;
    public static final int TOP_EDGE = 0;
    public static final int RIGHT_EDGE = 1;
    public static final int BOTTOM_EDGE = 2;
    public static final int LEFT_EDGE = 3;
    public static final int EDGES_COUNT = 4;

    public Tile(int tileNumber, String[] t) {
        this.tileNumber = tileNumber;
        this.edgesWithNeighbor = 0;
        this.edges = new String[EDGES_COUNT];
        edges[TOP_EDGE] = t[0];
        edges[BOTTOM_EDGE] = t[TILE_SIZE - 1];
        StringBuilder sbRight = new StringBuilder();
        StringBuilder sbLeft = new StringBuilder();
        for (int i = 0; i < TILE_SIZE; i++) {
            sbRight.append(t[i].charAt(TILE_SIZE - 1));
            sbLeft.append(t[i].charAt(0));
        }
        edges[RIGHT_EDGE] = sbRight.toString();
        edges[LEFT_EDGE] = sbLeft.toString();
    }

    public int getTileNumber() {
        return tileNumber;
    }

    public String getEdge(int edge){
        return edges[edge];
    }

    public int getEdgesWithNeighbor() {
        return edgesWithNeighbor;
    }

    public void incrEdgesWithNeighbor(){
        this.edgesWithNeighbor++;
    }

    public void printTile() {
        System.out.println("Tile " + this.tileNumber);
        System.out.println("top:" + edges[TOP_EDGE] + ", right:" + edges[RIGHT_EDGE] + ", bottom:" + edges[BOTTOM_EDGE] + ", left:" + edges[LEFT_EDGE]);
        System.out.println("top:    " + Tile.strToInt(edges[TOP_EDGE]) + "   , right:    " + Tile.strToInt(edges[RIGHT_EDGE]) + "   , bottom:    " + Tile.strToInt(edges[BOTTOM_EDGE]) + "   , left:    " + Tile.strToInt(edges[LEFT_EDGE]));
        System.out.println();
    }

    private static String rev(String s) {
        return (new StringBuilder(s)).reverse().toString();
    }

    private static int strToInt(String s) {
        return Integer.parseInt(s, 2);
    }

    public  static int minFromPair(String s) {
        int i1 = strToInt(s);
        int i2 = strToInt(rev(s));
        return Math.min(i1, i2);
    }

}
