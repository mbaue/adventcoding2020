package com.advent.day17;

public class Cube {
    public static final char ACTIVE = '#';
    public static final char INACTIVE = '.';

    private int dimX;
    private int dimY;
    private int dimZ;

    private char[][][] values;

    public Cube(int dimX, int dimY, int dimZ) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.dimZ = dimZ;
        this.values = new char[dimX][dimY][dimZ];
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public int getDimZ() {
        return dimZ;
    }

    private void fillCubeInactive() {
        for (int z = 0; z < dimZ; z++) {
            for (int y = 0; y < dimY; y++) {
                for (int x = 0; x < dimX; x++) {
                    values[x][y][z] = Cube.INACTIVE;
                }
            }
        }
    }

    public void printStatus() {
        for (int z = 0; z < dimZ; z++) {
            for (int y = 0; y < dimY; y++) {
                for (int x = 0; x < dimX; x++) {
                    System.out.print(values[x][y][z]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public Cube createOneLevelBigger() {
        Cube bigger = new Cube(this.dimX + 2, this.dimY + 2, this.dimZ + 2);
        bigger.fillCubeInactive();
        for (int x = 0; x < this.dimX; x++) {
            for (int y = 0; y < this.dimY; y++) {
                for (int z = 0; z < this.dimZ; z++) {
                    bigger.values[x + 1][y + 1][z + 1] = this.values[x][y][z];
                }
            }
        }
        return bigger;
    }

    private boolean evaluateCell(int x, int y, int z) {
        int activeNeighbors = this.countActiveNeighbors(x, y, z);
        if (this.values[x][y][z] == ACTIVE) {
            return activeNeighbors == 2 || activeNeighbors == 3;
        } else {
            return activeNeighbors == 3;
        }
    }

    public void countValuesFromPrevious(Cube prevCube) {
        for (int x = 0; x < this.dimX; x++) {
            for (int y = 0; y < this.dimY; y++) {
                for (int z = 0; z < this.dimZ; z++) {
                    if (prevCube.evaluateCell(x, y, z)) {
                        this.values[x][y][z] = ACTIVE;
                    } else {
                        this.values[x][y][z] = INACTIVE;
                    }
                }
            }
        }
    }

    public void fillInitial(String[] input) {
        for (int x = 0; x < this.dimX; x++) {
            for (int y = 0; y < this.dimY; y++) {
                for (int z = 0; z < this.dimZ; z++) {
                    this.values[x][y][z] = input[x].charAt(y);
                }
            }
        }
    }

    public int countActive() {
        int cnt = 0;
        for (int x = 0; x < this.dimX; x++) {
            for (int y = 0; y < this.dimY; y++) {
                for (int z = 0; z < this.dimZ; z++) {
                    if (this.values[x][y][z] == ACTIVE) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    private int countActiveNeighbors(int posX, int posY, int posZ) {
        int cnt = 0;
        for (int x = Math.max(posX - 1, 0); x <= Math.min(posX + 1, this.dimX - 1); x++) {
            for (int y = Math.max(posY - 1, 0); y <= Math.min(posY + 1, this.dimY - 1); y++) {
                for (int z = Math.max(posZ - 1, 0); z <= Math.min(posZ + 1, this.dimZ - 1); z++) {
                    if (!(x == posX && y == posY && z == posZ)) {
                        if (this.values[x][y][z] == ACTIVE) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}