
package beerchallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
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
import javafx.scene.control.Alert;
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
import javafx.scene.text.TextAlignment;

public class BeerChallenge extends Application {

    
    Parent createContent(GameState state, Stage stage) {

        Group g = new Group();
        final BorderPane root = new BorderPane();


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

        Button t1 = new Button("Red score+");
        t1.setOnAction(e -> sc1.set(sc1.getValue()+1));
        t1.setMinWidth(Region.USE_PREF_SIZE);
        Button t2 = new Button("Blue score+");
        t2.setOnAction(e -> sc2.set(sc2.getValue()+1));
        t2.setMinWidth(Region.USE_PREF_SIZE);
        Button sav = new Button("Save/Pause");
        sav.setOnAction(e -> System.out.println("mii"));
        sav.setPrefHeight(50);
        Button tSelect = new Button("Team selection");
        tSelect.setOnAction(e -> stage.setScene(new Scene(provMenu(state, stage))));
        tSelect.setPrefHeight(50);
        Button gSelect = new Button("Game selection");
        gSelect.setOnAction(e -> root.setCenter(teamMenu(state, stage)));
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
    
    Parent teamMenu(GameState state, Stage stage) {
            Group g = new Group();
            HBox hbox = new HBox(10);
            hbox.setPrefSize(100, 100);
            hbox.setAlignment(Pos.CENTER);
            for(int i = 0; i< state.getTeams().size();i++) {
                Button btn = new Button(state.getTeams().get(i).getName());
                btn.setMinWidth(Region.USE_PREF_SIZE);
                final int index = i;
                btn.setOnAction(e -> {
                                                        if(state.getTeam1() == null)
                                                            state.setTeam1(index);
                                                        else if (state.getTeam2() == null)
                                                            state.setTeam2(index);
                                                        else
                                                            state.setTeam1(index);
                                                    });
                hbox.getChildren().add(btn);
            }
            Button con = new Button("Continue");
            con.setMinWidth(Region.USE_PREF_SIZE);
            con.setOnAction(e -> {if(state.teamsChosen())
                                                    stage.setScene(new Scene(createContent(state, stage)));
                                                 
            });
            hbox.getChildren().add(con);
            g.getChildren().add(hbox);
            return g;
    }
    
    Parent provMenu(GameState state, Stage stage) {
        Group g = new Group();
        g.setAutoSizeChildren(true);
        BorderPane border = new BorderPane();
        
        Label header = new Label("BIER-O-LYMPIxXx");
        header.setFont(Font.font("Comic Sans", FontWeight.EXTRA_BOLD, 40));
        header.setTextAlignment(TextAlignment.CENTER);
        header.setTextFill(Color.GREENYELLOW);
        
        FlowPane inner = new FlowPane();
        inner.getChildren().stream().forEach(System.out::println);
        inner.setPrefSize(800, 600);
        inner.setPrefWrapLength(170);     
        for(int i = 0; i < 6; i++) {
            Group grup = new Group();
            HBox player = new HBox();
            player.setStyle("-fx-background-color: aliceblue");
            player.setPadding(new Insets(30));
            if (state.getTeams() != null && state.getTeams().size() > i) {
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
        sav.setOnAction(e -> { if(state.getTeams().size() == 2) {
                                                    state.setTeam1(0);
                                                    state.setTeam2(1);
                                                    stage.setScene(new Scene(createContent(state, stage)));
                                             } else if (state.teamsChosen())
                                                    stage.setScene(new Scene(createContent(state, stage)));
                                             else {
                                                    stage.setScene(new Scene(teamMenu(state, stage)));
                                                    /*Alert alert = new Alert(Alert.AlertType.ERROR);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText("No(t enough) teams selected");
                                                    alert.setContentText("Choose two teams to play!");
                                                    alert.showAndWait();*/
                                             }
                                   });
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
        text.setPromptText("Team name");
        text.setPrefSize(100, 50);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        text.setAlignment(Pos.CENTER);
        outer.getChildren().add(text);
        
        LinkedList<Player> mates = new LinkedList<>();
        TextField[] matez = new TextField[5];
        for(int i = 0; i<5;i++) {
            HBox slot = new HBox(5);
            slot.setPrefSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            TextField btn = new TextField();
            btn.setPromptText("add Teammember");
            btn.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
            btn.setAlignment(Pos.CENTER);
            btn.setMinWidth(Region.USE_PREF_SIZE);
            matez[i] = btn;
            ImageView pic = new ImageView("images/game_" + new Random().nextInt(5) + ".png");
            slot.getChildren().addAll(btn, pic);
            outer.getChildren().add(slot);
        }
        Button submit = new Button("Confirm");
        submit.setMinWidth(Region.USE_PREF_SIZE);
        submit.setLayoutX(100);
        submit.setOnMouseClicked(e -> { for(TextField tf : matez) {
                                                                    mates.add(new Player(tf.getText()));
                                                                    tf.clear();
                                                              }
                                                              state.addTeam(new Team(text.getText(), mates));
                                                              text.clear();
                                                    });
        outer.getChildren().add(submit);
        g.getChildren().addAll(outer);
        return g;
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        GameState state = new GameState();
        
        Scene menu = new Scene(provMenu(state, stage));

        stage.setScene(menu);
        stage.setTitle("Beer-Challenge");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
