package com.company;

/**
 * This class handles the tile logic.
 * @author Gabriel Beutler
 * @version 1.0
 */
public class Tile {
    private boolean isMine;
    private TileState state;
    private int index;
    private Grid grid;

    /**
     * @param grid  The grid of all tiles.
     * @param index The index in the grid array.
     */
    public Tile(Grid grid, int index) {
        this.isMine = false;
        this.state = TileState.HIDDEN;
        this.grid = grid;
        this.index = index;
    }

    /**
     * Test a tile.
     * @param sender Object that calls this function.
     * @return TileState
     */
    public TileState test(Game sender, Boolean firstCall) {
        if (this.isMine) {
            this.state = TileState.EXPLODED;
            sender.gameOver();
        } else {
            if (this.state != TileState.MARKED) {
                if(firstCall){
                    this.setState(TileState.DISCOVERED);
                }
                this.discover(sender);
            }
        }
        return this.state;
    }

    /**
     * Calls the test method of all surrounding tiles when they are no mine or they are not in range of one or more mines
     * and sets the tile state to discovered when they are no mine.
     * @param sender
     */
    public void discover(Game sender) {
        Tile[] tiles = getSurroundedTiles();
        for (int i = 0; i < tiles.length; i++) {
            Tile tile = tiles[i];
            if (tile == null) continue;
            if (tile.isMine) continue;
            if (tile.state == TileState.DISCOVERED) continue;


            tile.setState(TileState.DISCOVERED);
            if (tile.getNeighbourMineCount() == 0) {
                tile.test(sender, false);
            }

        }
    }

    /**
     * Set the state of a mine.
     * @param state tile state
     */
    public void setState(TileState state) {
        this.state = state;
    }

    /**
     * Mark a tile as mine.
     * @return tile state
     */
    public TileState mark() {
        if(this.state != TileState.DISCOVERED) {
            if (this.state == TileState.MARKED) {
                this.state = TileState.HIDDEN;
            } else {
                this.state = TileState.MARKED;
            }
        }
        return this.state;
    }

    /**
     * Get the tile state
     * @return tile state
     */
    public TileState getState() {
        return state;
    }

    /**
     * Sum the mines in range of this tile.
     * @return Mines in range.
     */
    public Integer getNeighbourMineCount() {
        Integer mines = 0;
        Tile[] tiles = getSurroundedTiles();
        for (int i = 0; i < tiles.length; i++) {
            Tile tile = tiles[i];
            if (tile == null) continue;
            if (tile.isMine) {
                mines++;
            }
        }
        return mines;
    }

    /**
     * Set index. Index is used in order to calculate the x and y cord.
     * @param index The index of the tile in the grid.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Get all surrounded tiles
     * @return tiles
     */
    public Tile[] getSurroundedTiles() {
        Tile[] tiles = new Tile[8];
        int startX = this.getX() - 1;
        int startY = this.getY() - 1;
        int scanLength = 3;
        int arrayIndex = 0;
        for (int y = startY; y < startY + scanLength; y++) {
            if (y < 0 || y > grid.getSize()) {
                continue;
            }
            for (int x = startX; x < startX + scanLength; x++) {
                if (x < 0 || x > grid.getSize()) {
                    continue;
                }
                if (!(x == this.getX() & y == this.getY())) {
                    Tile tile = grid.pickTile(x, y);
                    if (tile != null) {
                        tiles[arrayIndex] = tile;
                        arrayIndex++;
                    }
                }
            }
        }
        return tiles;
    }


    /**
     * Get the symbol displaying on the grid.
     * @return
     */
    public char getSymbol() {
        switch (this.state) {
            case HIDDEN:
                return '▓';
            case MARKED:
                return '░';
            case DISCOVERED:
                return getNeighbourMineCount().toString().toCharArray()[0];
            case EXPLODED:
                return '*';
        }
        return '.';
    }

    /**
     * Calculates the x cord for the tile.
     *
     * @return x cord
     */
    public int getX() {
        return this.index % this.grid.getSize();
    }

    /**
     * Calculates the y cord for the tile.
     * @return y cord
     */
    public int getY() {
        return this.index / this.grid.getSize();
    }

    /**
     * Checks if the tile is a mine.
     * @return
     */
    public boolean isMine() {
        return isMine;
    }

    /**
     * Sets if the tile is a mine.
     * @param mine
     */
    public void setMine(boolean mine) {
        isMine = mine;
    }
}
