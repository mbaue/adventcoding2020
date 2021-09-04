package com.advent.day17;

public class FourdimCube {
    public static final char ACTIVE = '#';
    public static final char INACTIVE = '.';

    private int dimX;
    private int dimY;
    private int dimZ;
    private int dimW;

    private char[][][][] values;

    public FourdimCube(int dimX, int dimY, int dimZ, int dimW) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.dimZ = dimZ;
        this.dimW = dimW;
        this.values = new char[dimX][dimY][dimZ][dimW];
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

    public int getDimW() {
        return dimW;
    }

    private void fillCubeInactive() {
        for (int z = 0; z < dimZ; z++) {
            for (int y = 0; y < dimY; y++) {
                for (int x = 0; x < dimX; x++) {
                    for (int w = 0; w < dimW; w++) {
                        values[x][y][z][w] = Cube.INACTIVE;
                    }
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

    public FourdimCube createOneLevelBigger() {
        FourdimCube bigger = new FourdimCube(this.dimX + 2, this.dimY + 2, this.dimZ + 2, this.dimW + 2);
        bigger.fillCubeInactive();
        for (int x = 0; x < this.dimX; x++) {
            for (int y = 0; y < this.dimY; y++) {
                for (int z = 0; z < this.dimZ; z++) {
                    for (int w = 0; w < this.dimW; w++) {
                        bigger.values[x + 1][y + 1][z + 1][w + 1] = this.values[x][y][z][w];
                    }
                }
            }
        }
        return bigger;
    }

    private boolean evaluateCell(int x, int y, int z, int w) {
        int activeNeighbors = this.countActiveNeighbors(x, y, z, w);
        if (this.values[x][y][z][w] == ACTIVE) {
            return activeNeighbors == 2 || activeNeighbors == 3;
        } else {
            return activeNeighbors == 3;
        }
    }

    public void countValuesFromPrevious(FourdimCube prevCube) {
        for (int x = 0; x < this.dimX; x++) {
            for (int y = 0; y < this.dimY; y++) {
                for (int z = 0; z < this.dimZ; z++) {
                    for (int w = 0; w < this.dimW; w++) {
                        if (prevCube.evaluateCell(x, y, z, w)) {
                            this.values[x][y][z][w] = ACTIVE;
                        } else {
                            this.values[x][y][z][w] = INACTIVE;
                        }
                    }
                }
            }
        }
    }

    public void fillInitial(String[] input) {
        for (int x = 0; x < this.dimX; x++) {
            for (int y = 0; y < this.dimY; y++) {
                for (int z = 0; z < this.dimZ; z++) {
                    for (int w = 0; w < this.dimW; w++) {

                        this.values[x][y][z][w] = input[x].charAt(y);
                    }
                }
            }
        }
    }

    public int countActive() {
        int cnt = 0;
        for (int x = 0; x < this.dimX; x++) {
            for (int y = 0; y < this.dimY; y++) {
                for (int z = 0; z < this.dimZ; z++) {
                    for (int w = 0; w < this.dimW; w++) {
                        if (this.values[x][y][z][w] == ACTIVE) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    private int countActiveNeighbors(int posX, int posY, int posZ, int posW) {
        int cnt = 0;
        for (int x = Math.max(posX - 1, 0); x <= Math.min(posX + 1, this.dimX - 1); x++) {
            for (int y = Math.max(posY - 1, 0); y <= Math.min(posY + 1, this.dimY - 1); y++) {
                for (int z = Math.max(posZ - 1, 0); z <= Math.min(posZ + 1, this.dimZ - 1); z++) {
                    for (int w = Math.max(posW - 1, 0); w <= Math.min(posW + 1, this.dimW - 1); w++) {
                        if (!(x == posX && y == posY && z == posZ && w == posW)) {
                            if (this.values[x][y][z][w] == ACTIVE) {
                                cnt++;
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
