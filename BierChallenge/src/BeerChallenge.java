
package beerchallenge;

import java.io.IOException;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class BeerChallenge extends Application {
    
    
    Parent createContent(GameState state) {
        /////    Sample teams         /////////

        ///////////////////////////////////////////////
        
        
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
        pages[0].setOnMouseClicked(e -> root.setCenter(state.game.showContent()));
        pages[1].setOnMouseClicked(e -> root.setCenter(new Text("yeah")));
        pages[2].setOnMouseClicked(e -> root.setCenter(new Text("yeah")));
        pages[3].setOnMouseClicked(e -> root.setCenter(new Text("yeah")));
        pages[4].setOnMouseClicked(e -> root.setCenter(new Text("yeah")));
        pages[5].setOnMouseClicked(e -> root.setCenter(new Text("yeah")));
        
        menu.getChildren().addAll(pages);
        
        
        //TOP
        Label score1 = new Label();
        score1.textProperty().bind(Bindings.convert(state.game.getScore1()));
        score1.setTextFill(Color.RED);
        score1.setFont(Font.font("Comic Sans", FontWeight.BOLD, 35));
        
        Label score2 = new Label();
        score2.textProperty().bind(Bindings.convert(state.game.getScore2()));
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
        
        Button nxt = new Button("End turn/Next");
        nxt.setOnAction(e -> { if (!state.game.getTurn())
                                                state.game.setScore1(1);
                                            else
                                                state.game.setScore2(1);
                                            state.game.endTurn();
                                          });
        nxt.setPrefHeight(40);
        Button bck = new Button("Back/Previous");
        bck.setOnAction(e -> System.out.println("mii"));
        bck.setPrefHeight(50);
        Button sav = new Button("Save/Pause");
        sav.setOnAction(e -> System.out.println("mii"));
        sav.setPrefHeight(50);
        Button tSelect = new Button("Team selection");
        
        tSelect.setOnAction(e -> {
                        //Zur Teamauswahl
            Stage stage = (Stage) g.getScene().getWindow();
            //Teams team = new Teams();
            //String[] tmp = FXMLDocumentController.getNames(team.fields);

            //for(int i = 0; i < tmp.length; i++){
            //    team.fields[i].setText(tmp[0]);
            //}
            Scene scene = stage.getScene();
            try{
                scene.setRoot(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")));
            }catch(IOException exception){
                exception.printStackTrace();
            }
        });
        tSelect.setPrefHeight(50);
        Button gSelect = new Button("Game selection");
        gSelect.setOnAction(e -> root.setCenter(menu));
        gSelect.setPrefHeight(50);
        Button hlp = new Button("Help");
        hlp.setOnAction(e -> System.out.println("mii"));
        hlp.setPrefHeight(50);
        
        rightBox.getChildren().addAll(nxt, bck, sav, tSelect, gSelect, hlp);
        
        
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
    
    @Override
    public void start(Stage stage) throws Exception {
        Player p = new Player("Winnie");
        LinkedList<Player> mates1 = new LinkedList<>();
        mates1.add(p);
        mates1.add(new Player("Franz-Walter"));
        mates1.add(new Player("katzi"));
        mates1.add(new Player("milcher"));
        mates1.add(new Player("butti"));
        LinkedList<Player> mates2 = new LinkedList<>();
        mates2.add(new Player("Töter"));
        mates2.add(new Player("Kill-kun"));
        mates2.add(new Player("Schlachti"));
        mates2.add(new Player("Mord-chan"));
        Team team1 = new Team("SüßmilchFelinchen", mates1);
        Team team2 = new Team("MilkPizza", mates2);
        LinkedList<Team> teams = new LinkedList<>();
        teams.add(team1);
        teams.add(team2);
        GameState state = new GameState(teams, 0, 1);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        
        
        Scene start = new Scene(root);
        Scene menu = new Scene(createContent(state));
       
        stage.setScene(menu);
        stage.setTitle("Beer-Challenge");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
