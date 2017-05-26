package sample.Model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import sample.Main.Main;
import sample.Pattern.Pattern;
import sample.Pattern.PatternManager;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static sample.Model.Board.BOARD_SIZE;
import static sample.Model.Board.board;


public class Cell extends HBox implements Entity {

    public SimpleBooleanProperty alive = new SimpleBooleanProperty(false);
    public static SimpleIntegerProperty neighProp = new SimpleIntegerProperty();
    private int x, y;

    public Cell(int x, int y) {
        setPadding(new Insets(5, 5, 5, 5));

        this.x = x;
        this.y = y;

        backgroundProperty()
                .bind(Bindings
                        .when(alive)
                        .then(new Background(new BackgroundFill(Main.picker.getValue(), CornerRadii.EMPTY, new Insets(0.1, 0.1, 0.1, 0.1))))
                        .otherwise(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(0.1, 0.1, 0.1, 0.1)))));

        insertPattern();
        insertOrDelete();
    }


    @Override
    public void setState(boolean newstate){
        alive.setValue(newstate);
    }

    @Override
    public boolean getState(){
        return alive.get();
    }

    @Override
    public void changeState(){
        alive.set(!alive.get());
    }

    @Override
    public long countNeighbours(){

        try{
            if(Main.tf.getText()!= null) neighProp.setValue(Integer.parseInt(Main.tf.getText()));
        }catch(NumberFormatException e){
            e.getCause();
        }

        //toDo:moore-neighbour-tracing
         Cell[] neighbours = {board[x-1<0?BOARD_SIZE-1:x-1][y-1<0?BOARD_SIZE-1:y-1],
                              board[x][y-1<0?BOARD_SIZE-1:y-1],
                              board[x+1>BOARD_SIZE-1?0:x+1][y-1<0?BOARD_SIZE-1:y-1],
                              board[x-1<0?BOARD_SIZE-1:x-1][y],
                              board[x+1>BOARD_SIZE-1?0:x+1][y],
                              board[x-1<0?BOARD_SIZE-1:x-1][y+1>BOARD_SIZE-1?0:y+1],
                              board[x][y+1>BOARD_SIZE-1?0:y+1],
                              board[x+1>BOARD_SIZE-1?0:x+1][y+1>BOARD_SIZE-1?0:y+1]};

        return neighProp.get() + Arrays.stream(neighbours).filter(e-> e != null && e.getState()).count();
    }

    @Override
    public boolean evolve() {

        return ((board[x][y].getState() && board[x][y].countNeighbours() > 3)
                || (board[x][y].getState() && board[x][y].countNeighbours() < 2)
                || (!board[x][y].getState() && board[x][y].countNeighbours() == 3));
    }




    public void insertOrDelete(){
        setOnMouseMoved(e-> {
            if (e.isControlDown()) setState(true);
            if (e.isShiftDown()) setState(false);
        });
    }

    public void insertPattern(){
        setOnMouseClicked(e->{
            if(Main.patternview.getSelectionModel().getSelectedItem()!=null) {
                Pattern p = PatternManager.getChosenPattern();
                List<String> tmpList = Arrays.asList(p.getPattern().split(""));

                int xSize = p.getX();
                int ySize = p.getY();

                Iterator<String> iter = tmpList.iterator();
                for (int i = this.x; i < this.x + xSize; i++){
                    for(int j = this.y; j < this.y + ySize; j++){
                        board[i][j].setState(!iter.next().equals("0"));
                    }
                }
            }
        });
    }
}
