package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class generates a grid.
 * @author Gabriel Beutler
 * @version 1.0
 */
public class GridGenerator {
    private Grid grid;

    /**
     * @param grid
     */
    public GridGenerator(Grid grid) {
        this.grid = grid;
    }

    /**
     * Generate a new grid.
     * @return Grid as tile array
     */
    public Tile[] generateNewGrid(){
        return this.shuffleGrid(this.fillGrid());
    }

    /**
     * Apply a random order to the tiles.
     * @param tiles
     * @return Grid as tile array
     */
    private Tile[] shuffleGrid(Tile[] tiles){
        List<Tile> shuffleTiles = Arrays.asList(tiles);
        Collections.shuffle(shuffleTiles);
        tiles = shuffleTiles.toArray(Tile[]::new);
        this.applyTileIndex(tiles);
        return tiles;
    }

    /**
     * Loops trough every tile and assigns the index to it.
     * This function is used after shuffle.
     * @param tiles Grid
     */
    private void applyTileIndex(Tile[] tiles){
        for (int i = 0; i < tiles.length; i++) {
            tiles[i].setIndex(i);
        }
    }

    /**
     * Filling a grid with mines and tiles.
     * @return Grid as tile array
     */
    private Tile[] fillGrid(){
        int tileCount = this.grid.getSize() * this.grid.getSize() ;
        Tile[] tiles = new Tile[tileCount];

        for (int i = 0; i < tileCount; i++) {
            Tile tile = new Tile(this.grid, i);
            if(i < this.grid.getMineCount()){
                tile.setMine(true);
            }
            tiles[i] = tile;
        }

        return tiles;
    }
}
