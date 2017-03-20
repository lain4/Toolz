
package beerchallenge;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BeerChallenge extends Application {

    
    Parent createContent(GameState state, Stage stage) {

        Group g = new Group();
        BorderPane root = new BorderPane();


        //CENTER/GAME
        FlowPane menu = new FlowPane();
        menu.setPadding(new Insets(80));
        menu.setVgap(100);
        menu.setHgap(100);
        menu.setPrefWrapLength(170);
        menu.setStyle("-fx-background-color: white;");

        ImageView pages[] = new ImageView[6];
        for (int i=0; i< 6; i++) {
            pages[i] = new ImageView("images/game_" + i + ".png");
            pages[i].setX(50);
            pages[i].setY(50);
        }

        //navigate to game
        pages[0].setOnMouseClicked(e -> root.setCenter(state.showGame(0)));
        pages[1].setOnMouseClicked(e -> root.setCenter(state.showGame(1)));
        pages[2].setOnMouseClicked(e -> root.setCenter(state.showGame(2)));
        pages[3].setOnMouseClicked(e -> root.setCenter(state.showGame(3)));
        pages[4].setOnMouseClicked(e -> root.setCenter(state.showGame(4)));
        pages[5].setOnMouseClicked(e -> root.setCenter(state.showGame(5)));

        menu.getChildren().addAll(pages);

        //TOP
        IntegerProperty sc1 = new SimpleIntegerProperty();
        IntegerProperty sc2 = new SimpleIntegerProperty();
        Label score1 = new Label();
        score1.textProperty().bind(Bindings.convert(sc1));
        score1.setTextFill(Color.RED);
        score1.setFont(Font.font("Comic Sans", FontWeight.BOLD, 35));

        Label score2 = new Label();
        score2.textProperty().bind(Bindings.convert(sc2));
        score2.setTextFill(Color.LIGHTBLUE);
        score2.setFont(Font.font("Comic Sans", FontWeight.BOLD, 35));

        HBox topBox = new HBox(20);
        topBox.setPrefHeight(65);
        topBox.getChildren().addAll(score1, score2);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-background-color: black");


        //LEFT
        SplitPane leftBox = new SplitPane();
        leftBox.setOrientation(Orientation.VERTICAL);

        leftBox.getItems().addAll(state.getTeam1().getAvatar(), state.getTeam2().getAvatar());

        //RIGHT
        VBox rightBox = new VBox(40);
        rightBox.setStyle("-fx-background-color: royalblue");
        rightBox.setPadding(new Insets(10));

        Button t1 = new Button("Red has scored!");
        t1.setOnAction(e -> sc1.set(sc1.getValue()+1));
        t1.setMinWidth(Region.USE_PREF_SIZE);
        Button t2 = new Button("Blue has scored!");
        t2.setOnAction(e -> sc2.set(sc2.getValue()+1));
        t2.setMinWidth(Region.USE_PREF_SIZE);
        Button sav = new Button("Save/Pause");
        sav.setOnAction(e -> System.out.println("mii"));
        sav.setPrefHeight(50);
        Button tSelect = new Button("Team selection");
        tSelect.setOnAction(e -> stage.setScene(new Scene(provMenu(state, stage))));
        tSelect.setPrefHeight(50);
        Button gSelect = new Button("Game selection");
        gSelect.setOnAction(e -> root.setCenter(menu));
        gSelect.setPrefHeight(50);
        Button hlp = new Button("Help");
        hlp.setOnAction(e -> System.out.println("mii"));
        hlp.setPrefHeight(50);

        rightBox.getChildren().addAll(t1, t2, sav, tSelect, gSelect, hlp);


        //BOT
        HBox botBox = new HBox();

        //ROOT MERGE
        root.setPrefWidth(1200);
        root.setPrefHeight(950);

        root.setCenter(menu);
        root.setTop(topBox);
        root.setLeft(leftBox);
        root.setRight(rightBox);
        root.setBottom(botBox);
        g.getChildren().add(root);

        return g;
    }
    
    Parent provMenu(GameState state, Stage stage) {
        Group g = new Group();
        g.setAutoSizeChildren(true);
        BorderPane border = new BorderPane();
        
        Label header = new Label("BIER-O-LYMPIxXx");
        header.setFont(Font.font("Comic Sans", FontWeight.EXTRA_BOLD, 40));
        header.setAlignment(Pos.BASELINE_CENTER);
        header.setTextFill(Color.GREENYELLOW);
        
        FlowPane inner = new FlowPane();
        inner.setPrefSize(800, 600);
        inner.setPrefWrapLength(170);     
        for(int i = 0; i < 6; i++) {
            Group grup = new Group();
            HBox player = new HBox();
            player.setStyle("-fx-background-color: aliceblue");
            player.setPadding(new Insets(30));
            if (state.getTeams().size() > i) {
                grup.getChildren().add(state.getTeams().get(i).getAvatar());
            }else {
            Label label = new Label("add Team");
            label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
            label.setAlignment(Pos.CENTER);
            label.setTextFill(Color.LIGHTGRAY);
            label.setMinWidth(Region.USE_PREF_SIZE);
            player.setOnMouseClicked(e -> border.setCenter(teamBuild(state)));
            player.getChildren().add(label);
            grup.getChildren().add(player);
            }
            inner.getChildren().add(grup);
        }
        
        VBox rightBox = new VBox(40);
        rightBox.setStyle("-fx-background-color: royalblue");
        rightBox.setPadding(new Insets(10));

        Button sav = new Button("Game selection");
        sav.setOnAction(e -> stage.setScene(new Scene(createContent(state, stage))));
        sav.setPrefHeight(50);
        Button tSelect = new Button("Team selection");
        tSelect.setOnAction(e -> stage.setScene(new Scene(provMenu(state, stage))));
        tSelect.setPrefHeight(50);
        Button hlp = new Button("Help");
        hlp.setOnAction(e -> System.out.println("mii"));
        hlp.setPrefHeight(50);

        rightBox.getChildren().addAll(sav, tSelect, hlp);
        
        
        border.setRight(rightBox);
        border.setCenter(inner);
        border.setTop(header);
        g.getChildren().add(border);
        return g;
    }
    
    Parent teamBuild(GameState state) {
        Group g = new Group();
        VBox outer = new VBox();
        outer.setPrefSize(500, 500);
        outer.setAlignment(Pos.TOP_CENTER);
        TextField text = new TextField();
        text.setPrefSize(100, 50);
        text.setAlignment(Pos.TOP_LEFT);
        outer.getChildren().add(text);
        
        g.getChildren().add(outer);
        return g;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Sample-Teams
        LinkedList<Player> mates1 = new LinkedList<>();
        mates1.add(new Player("A"));
        mates1.add(new Player("B"));
        mates1.add(new Player("C"));
        mates1.add(new Player("D"));
        LinkedList<Player> mates2 = new LinkedList<>();
        mates2.add(new Player("E"));
        mates2.add(new Player("F"));
        mates2.add(new Player("G"));
        mates2.add(new Player("H"));
        Team team1 = new Team("Letrons", mates1);
        Team team2 = new Team("Katzen", mates2);
        LinkedList<Team> teams = new LinkedList<>();
        teams.add(team1);
        teams.add(team2);

        GameState state = new GameState(teams, 0, 1);
        
        Scene menu = new Scene(provMenu(state, stage));

        stage.setScene(menu);
        stage.setTitle("Beer-Challenge");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
