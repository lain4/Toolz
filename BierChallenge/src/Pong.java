package beerchallenge;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class Pong extends Game{
    
        public Pong(Team t1, Team t2) {
            super(t1, t2);
            this.type = Type.PONG;
        }
        
        @Override
        void run() {
            while(this.isPlayable()) {
                
            }
        }
        
        @Override
        Parent showContent() {
            Group g = new Group();
            HBox hbox = new HBox();
            Button test = new Button("test");
            test.setOnAction(e -> { for(Player p : getTeam1())
                                        p.addBeer(1);
                                    //Sample-Action (working)
                                    });
            hbox.setPrefHeight(100);
            hbox.setPrefWidth(100);
            hbox.setStyle("-fx-background-color: green");
            hbox.getChildren().add(test);
            g.getChildren().add(hbox);
            return g;
        }
    
}
