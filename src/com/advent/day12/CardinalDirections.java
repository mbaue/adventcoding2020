package com.advent.day12;

public enum CardinalDirections {
    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W');

    private final int num;
    private final char code;

    CardinalDirections(int num, char code) {
        this.num = num;
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public int getNum() {
        return num;
    }

    public CardinalDirections turnRight(int degrees) {
        for (CardinalDirections cd : CardinalDirections.values()) {
            if (cd.num == (this.getNum() + degrees/90) % 4) {
                return cd;
            }
        }
        return null;
    }

    public CardinalDirections turnLeft(int degrees) {
        for (CardinalDirections cd : CardinalDirections.values()) {
            if (cd.num == (this.getNum() - degrees/90 + 4) % 4) {
                return cd;
            }
        }
       return null;
    }

}