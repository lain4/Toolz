
package beerchallenge;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Männersahne
 */
public class Stemmen extends Game {
    
    public Stemmen(Team t1, Team t2){
        super(t1, t2);
        this.type = Type.STEMMEN;
    }
    
    @Override
    void run() {
        while(this.isPlayable()) {
                
        }
    }
    
    @Override
    Parent showContent() {
        Group g = new Group();
        VBox v = new VBox();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        Button test = new Button("Go");
        Button stop = new Button("Stop");
        Button reset= new Button("R");
        Button save = new Button("Save");
        TextField time = new TextField("0");
        TextField tmp = new TextField("0");

        int[] iar = {0};
        
        //Timer
        test.setOnAction(e ->{
             Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                    ae -> { 
                       time.setText(String.valueOf(iar[0]++)); 
                    }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
           
            stop.setOnAction(event ->{
                
                timeline.stop();
            
            });
            
            tmp.setOnAction(event ->{
                tmp.setText(String.valueOf(iar[0]));
            });
        });
        
        reset.setOnAction(e->{
            time.setText("0");
            iar[0] = 0;
        });

        hbox.setPrefHeight(100);
        hbox.setPrefWidth(100);
        hbox2.setPrefHeight(100);
        hbox2.setPrefWidth(100);
        hbox.setStyle("-fx-background-color: burlywood");
        hbox2.setStyle("-fx-background-color:burlywood");
        hbox.getChildren().addAll(test, time, save);
        hbox2.getChildren().addAll(stop, reset, tmp);
        v.getChildren().addAll(hbox,hbox2);
        g.getChildren().add(v);
        return g;
        }
}

