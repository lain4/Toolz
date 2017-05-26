package sample.Main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;
import sample.Model.Board;

public class GameLoop {

    private Board board;
    private static Timeline loop;
    private SimpleBooleanProperty paused = new SimpleBooleanProperty();
    public static SimpleDoubleProperty speed = new SimpleDoubleProperty(300);


    public GameLoop(Board board){
        this.board = board;
        loop =  new Timeline(new KeyFrame(Duration.millis(300), el -> {
            board.update();
        }));
        loop.setCycleCount(Animation.INDEFINITE);
    }


    public void launchOrStop(){
        if(paused.getValue() ) {
            loop.play();
            paused.setValue(false);
        }
        else {
            loop.pause();
            paused.setValue(true);
        }
    }

    public void reset(){
        loop.stop();
        board.reset();
        paused.setValue(true);
    }

    public SimpleBooleanProperty getPausedProperty()
    {
        return paused;
    }

    public void changeDuration(){
        loop.stop();
        loop =  new Timeline(new KeyFrame(Duration.millis(speed.getValue()), el -> {
            board.update();
        }));
        loop.setCycleCount(Animation.INDEFINITE);

        loop.play();
    }

    public void decrease(){
        speed.set(speed.get()+25);
    }

    public void increase(){
        if(speed.get()-25>0) speed.set(speed.get()-25);
    }


}
