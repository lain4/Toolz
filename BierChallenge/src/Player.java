package beerchallenge;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Player extends Entity{
    
        private final ImageView avatar;

        public Player(String name) {
            super(name);
            this.avatar = new ImageView("/images/cat.png");
        }

        public Player(String name, int drunk, int pong, int klim, int pHits, int kHits) {
            super(name, drunk, pong, klim, pHits, kHits);
            this.avatar = new ImageView("/images/cat.png");
        }

        public final ImageView getPic() {
            return avatar;
        }

        public final Group getAvatar() {
            Group g = new Group();
            BorderPane root = new BorderPane();
            root.setPrefWidth(20);
            root.setPrefHeight(20);
            Label drunkel = new Label();
            drunkel.textProperty().bind(Bindings.convert(beerDone));
            drunkel.setTextFill(Color.RED);
            drunkel.setAlignment(Pos.TOP_CENTER);
            drunkel.setFont(Font.font("Deja Vu Mono", FontWeight.BOLD, 30));
            Label namel = new Label(getName());
            namel.setTextFill(Color.BLACK);
            namel.setFont(Font.font("Comic Sans", FontWeight.SEMI_BOLD, 20));
            namel.setMinWidth(Region.USE_PREF_SIZE);
            root.setTop(namel);
            root.setRight(drunkel);
            avatar.setOnMouseClicked(e -> addBeer(1));
            root.setCenter(avatar);

            g.getChildren().add(root);
            return g;
        }
    
}
