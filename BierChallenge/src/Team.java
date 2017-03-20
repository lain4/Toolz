
package beerchallenge;

import java.util.LinkedList;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Team {
        private final String name;
        private final LinkedList<Player> team;
        private final IntegerProperty allDrunk = new SimpleIntegerProperty();
        private final GridPane avatar;

        Team(String name, LinkedList<Player> team) {
            this.name = name;
            this.team = team;
            for(Player p : team)
                allDrunk.add(p.beerDone);
            avatar = genGrid();
            avatar.setAlignment(Pos.BASELINE_CENTER);
        }
        
        private GridPane genGrid() {
                GridPane layout = new GridPane();
                Label drunkel = new Label();
                drunkel.textProperty().bind(Bindings.convert(allDrunk));
                drunkel.setTextFill(Color.CHOCOLATE);
                drunkel.setFont(Font.font("Deja Vu Mono", FontWeight.BOLD, 40));
                Label namel = new Label(getName());
                namel.setTextFill(Color.DARKCYAN);
                namel.setFont(Font.font("Deja Vu Mono", FontWeight.SEMI_BOLD, 20));
                namel.setMinWidth(Region.USE_PREF_SIZE);
                layout.add(namel, 0, 0);
                layout.add(drunkel, 2, 0);
                layout.add(team.get(0).getAvatar(), 0, 1);
                layout.add(team.get(1).getAvatar(), 1, 1);
                layout.add(team.get(2).getAvatar(), 0, 2);
                layout.add(team.get(3).getAvatar(), 1, 2);
                try{
                    layout.add(team.get(4).getAvatar(), 2, 1);
                } catch(IndexOutOfBoundsException ib){}
                layout.setAlignment(Pos.CENTER);
                layout.setPrefHeight(300);
                layout.setPrefWidth(100);
                return layout;
        }    
       
        public final void addPlayer(Player p) {
            team.add(p);
        }
        
        public final LinkedList<Player> getMates() {
            return team;
        }
        
        public final GridPane getAvatar() {
            return avatar;
        }

        public final IntegerProperty getDrunk() {
            return allDrunk;
        }
        
        public final String getName() {
            return name;
        }
}
