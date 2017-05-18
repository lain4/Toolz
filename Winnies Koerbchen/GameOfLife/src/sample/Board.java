package sample;

import javafx.scene.layout.GridPane;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board extends GridPane {

    public static final int BOARD_SIZE = 100;
    public static Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

    public Board(){
        init();
    }

    private void init(){

        for(int x = 0; x < BOARD_SIZE; x++){
            for(int y = 0; y < BOARD_SIZE; y++){
                Cell cell = new Cell(x,y);
                this.add(cell,x,y);
                board[x][y] = cell;

            }
        }
    }


    public void reset(){
        getChildren().filtered(n-> n instanceof Cell).forEach(n ->((Cell) n).setState(false));
    }

    private List<Cell> prepareGeneration(){
        return Arrays
                .stream(board)
                .flatMap(Arrays::stream)
                .filter(Cell::evolve)
                .collect(Collectors.toList());
    }

    private void generate(){
        prepareGeneration()
                .forEach(Cell::changeState);
    }

    public void update(){
        generate();
    }
}
