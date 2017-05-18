package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Arrays;

import static sample.Board.BOARD_SIZE;
import static sample.Board.board;

public class Cell extends HBox {

    private SimpleBooleanProperty alive;
    private int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        setPadding(new Insets(5, 5, 5, 5));


        alive = new SimpleBooleanProperty(false);
        backgroundProperty().bind(Bindings
                .when(alive)
                .then(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0.1, 0.1, 0.1, 0.1))))
                .otherwise(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(0.1, 0.1, 0.1, 0.1)))));


        setOnMouseMoved(d -> {
            if (d.isControlDown()) setState(true);
            if (d.isShiftDown()) setState(false);
        });
    }



    public void setState(boolean newstate){
        alive.setValue(newstate);
    }

    public boolean getState(){
        return alive.get();
    }

    public void changeState(){
        alive.set(alive.get()? false : true);
    }

    public long countNeighbours(){

        //toDo:moore-neighbour-tracing
         Cell[] neighbours = {board[x-1<0?BOARD_SIZE-1:x-1][y-1<0?BOARD_SIZE-1:y-1],
                              board[x][y-1<0?BOARD_SIZE-1:y-1],
                              board[x+1>BOARD_SIZE-1?0:x+1][y-1<0?BOARD_SIZE-1:y-1],
                              board[x-1<0?BOARD_SIZE-1:x-1][y],
                              board[x+1>BOARD_SIZE-1?0:x+1][y],
                              board[x-1<0?BOARD_SIZE-1:x-1][y+1>BOARD_SIZE-1?0:y+1],
                              board[x][y+1>BOARD_SIZE-1?0:y+1],
                              board[x+1>BOARD_SIZE-1?0:x+1][y+1>BOARD_SIZE-1?0:y+1]};

        return Arrays.stream(neighbours).filter(e-> e != null && e.getState()).count();
    }

    public boolean evolve() {
        return ((board[x][y].getState() && board[x][y].countNeighbours() > 3)
                || (board[x][y].getState() && board[x][y].countNeighbours() < 2)
                || (!board[x][y].getState() && board[x][y].countNeighbours() == 3));
    }
}
