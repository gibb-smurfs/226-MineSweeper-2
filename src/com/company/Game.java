package com.company;

/**
 * This class is handles the game logic.
 * @author Gabriel Beutler
 * @version 1.0
 */
public class Game {
    private Grid grid;
    private Render render;
    private Boolean running = true;
    private Input input = new Input();
    private Integer mineCount = 4;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Game();
    }

    /**
     * Set start values.
     */
    public Game() {
        this.grid = new Grid(2, this.mineCount);
        this.render = new Render(this.grid);
        startGame();
    }

    public void renderGrid(){
        this.render.generateLineBreaks(8);
        this.render.renderGrid(false);
    }

    /**
     * Checks if all marks are correct and end the game in this case.
     * @return
     */
    public boolean areAllMarksCorrect(){
        int correctMarks = 0;
        for (int i = 0; i < this.grid.getTiles().length; i++) {
            Tile tile = this.grid.getTiles()[i];
            if(tile.isMine() && tile.getState() == TileState.MARKED){
                correctMarks++;
            }
        }
        return (correctMarks == this.mineCount);
    }

    /**
     * Start a game.
     */
    public void startGame(){
        this.createGame();
        while (this.running) {
            this.renderGrid();
            processUserInput();
            if(areAllMarksCorrect()){
                this.userWin();
            }
        }
    }

    /**
     * Convert user input to instructions.
     */
    public void processUserInput(){
        char[] input = this.input.getUserInput();
        int x = Character.getNumericValue(input[2]) - 1;
        int y = Character.getNumericValue(input[1]) - 1;
        char command = input[0];
        Tile tile = this.grid.pickTile(x, y);
        if(command == 't') {
            tile.test(this, true);
        }
        if(command == 'm'){
            tile.mark();
        }
    }

    /**
     * Players loses.
     */
    public void gameOver(){
        this.running = false;
        this.renderGrid();
        System.out.println("Du hast eine Mine aufgedeckt.");
        System.out.println("GAME OVER");
    }

    /**
     * Players wins.
     */
    public void userWin(){
        this.running = false;
        this.renderGrid();
        System.out.println("Du hast alle Minen gefunden!");
    }

    /**
     * Start a new round.
     */
    private void createGame(){
        this.grid.generateGrid();
    }
}
