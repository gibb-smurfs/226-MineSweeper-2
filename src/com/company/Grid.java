package com.company;

/**
 * This class allows to interact with the gird.
 * @author Gabriel Beutler
 * @version 1.0
 */
public class Grid {
    private int size;
    private int mineCount;
    private Tile[] tiles;

    /**
     * Set default size an mine count.
     * @param size Size of 1 Dimension in the grid.
     * @param mineCount Amount of mines in the grid.
     */
    public Grid(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
    }

    /**
     * Generating a fresh grid.
     */
    public void generateGrid() {
        GridGenerator generator = new GridGenerator(this);
        this.tiles = generator.generateNewGrid();
    }

    /**
     * Select a tile in the grid based on the x and y cord.
     * @param x X cord in the grid.
     * @param y Y cord in the grid.
     * @return Tile at the position.
     */
    public Tile pickTile(int x, int y) {
        int index = (y * this.size) + x;
        if (index < tiles.length & index >= 0){
            return tiles[index];
        }
        return null;
    }

    /**
     * Get amount of tiles in the grid.
     * @return Amount of mines.
     */
    public int getMineCount() {
        return mineCount;
    }

    /**
     * Get the size of 1 dimension of the grid.
     * @return Size of 1 dimension.
     */
    public int getSize() {
        return size;
    }

    /**
     * Get all tiles.
     * @return Tile array.
     */
    public Tile[] getTiles() {
        return tiles;
    }


}
