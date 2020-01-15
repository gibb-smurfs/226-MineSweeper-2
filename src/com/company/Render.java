package com.company;

/**
 * This class drwas the grid.
 * @author Gabriel Beutler
 * @version 1.0
 */
public class Render {
    private Grid gird;

    /**
     * Set the grid to render.
     * @param gird Grid to render.
     */
    public Render(Grid gird) {
        this.gird = gird;
    }

    /**
     * This functions draws the grid.
     * @param showMines Forcing to draw a mines.
     */
    public void renderGrid(Boolean showMines) {
        this.renderHorizontalFieldNumbers();
        for (int line = 0; line < this.gird.getSize(); line++) {
            System.out.print(String.format(" %s", line + 1)); //Horiziontale Zeilennummer
            for (int column = 0; column < this.gird.getSize(); column++) {
                Tile tile = this.gird.pickTile(line, column);
                if (showMines && tile.isMine()) {
                    System.out.print(" ▒");
                } else {
                    System.out.print(String.format(" %s", tile.getSymbol()));
                }
            }
            System.out.println(""); // Zeilenumbruch
        }
    }

    /**
     * Generates multiple line breaks.
     * @param height The height
     */
    public void generateLineBreaks(int height){
        for(int i = 0; i < height; i++){
            System.out.println();
        }
    }

    /**
     * Draws the line numbers at the top.
     */
    private void renderHorizontalFieldNumbers() {
        System.out.print("  "); // Um zwei einrücken, damit Zahlen unter dem Spielfeld sind.
        for (int i = 0; i < this.gird.getSize(); i++) {
            System.out.print(String.format(" %s", i + 1));
        }
        System.out.println();
    }
}
