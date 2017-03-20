package beerchallenge;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Pong extends Game{
    
        private final IntegerProperty streak = new SimpleIntegerProperty();
    
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
            VBox outer = new VBox(30);
            HBox lower = new HBox();
            streak.set(0);
            
            Label combo = new Label();
            combo.textProperty().bind(Bindings.convert(streak));
            combo.setTextFill(Color.RED);
            combo.setFont(Font.font("Arial", FontWeight.BOLD, 35));
            outer.getChildren().add(combo);
                                
            for(int i = -3; i <= 3; i++) {
                HBox row = new HBox(20);
                row.setAlignment(Pos.CENTER);
                for(int j = Math.abs(i); j > 0; j--) {
                    Circle circle = new Circle(30, Color.RED);
                    circle.setOnMouseClicked(e -> { circle.setFill(Color.BLACK);
                                                                       streak.set(streak.getValue() + 1);
                                                            });
                    row.getChildren().add(circle);
                }
                outer.getChildren().add(row);
            }
           
            Button comBreak = new Button("Failed");        
            comBreak.setOnAction(e -> streak.set(0));
            comBreak.setMinWidth(Region.USE_PREF_SIZE);
            outer.getChildren().add((comBreak));
            
            Button sub1 = new Button(getT1().getName() + " won" );
            sub1.setMinWidth(Region.USE_PREF_SIZE);
            sub1.setOnAction(e -> { this.setScore1(streak.getValue());
                                                   streak.set(0);
                                        });
            sub1.setAlignment(Pos.TOP_LEFT);
            Button sub2 = new Button(getT2().getName() + " won");
            sub2.setMinWidth(Region.USE_PREF_SIZE);
            sub2.setOnAction(e -> { this.setScore2(streak.getValue());
                                                   streak.set(0);
                                        });
            sub1.setAlignment(Pos.TOP_RIGHT);
            lower.getChildren().addAll(sub1, sub2);
            outer.getChildren().add(lower);
            
            outer.setPrefSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);         
            g.getChildren().add(outer);
            return g;
        }
    
}
