package sample.Main;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Model.*;
import sample.Model.Cell;
import sample.Pattern.Pattern;
import sample.Pattern.PatternManager;
import sample.Util.IO;

import java.util.Arrays;
import java.util.HashMap;

public class Main extends Application {

    public static HashMap<String, Button> buttons = new HashMap<>();
    public static ListView<Pattern> patternview = new ListView<>();
    public static TextField tf = new TextField();
    public static ColorPicker picker = new ColorPicker();


    @Override
    public void start(Stage primaryStage) throws Exception{



        BorderPane root = new BorderPane();
        HBox menu = new HBox();
        initButtons();
        Board board = new Board();

        Scene scene = new Scene(root);

        GameLoop loop = new GameLoop(board);

        Blend b = new Blend();

        root.setStyle("-fx-background-color:lightgrey;");
        menu.setSpacing(10);

        PatternManager manager = new PatternManager();
        manager.setUp();

        patternview.setItems(PatternManager.patternlist);

        buttons.get("start").setOnAction(e -> loop.launchOrStop());
        buttons.get("reset").setOnAction(e -> loop.reset());

        buttons.get("create").setOnAction(e->{
            manager.openPatternManager();
        });

        buttons.get("faster").setOnAction(e->{
            loop.increase();
            loop.changeDuration();
        });
        buttons.get("slower").setOnAction(e->{
            loop.decrease();
            loop.changeDuration();
        });

        buttons.get("backup").setOnAction(e->{
            manager.initPatternView(IO.PATH_BACKUP);
        });


        menu.getChildren().addAll(buttons.get("start"),buttons.get("reset"), buttons.get("faster"), buttons.get("slower"), buttons.get("create"), tf, picker, buttons.get("backup"));

        root.setTop(menu);
        root.setCenter(board);
        root.setLeft(patternview);

        primaryStage.setTitle("GameOfLife");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initButtons(){
        Button start = new Button("Start"), reset = new Button("Reset"), create = new Button("Create your Pattern!"), faster = new Button("+"), slower = new Button("-"), backup = new Button("Add Backup-Patterns");
        buttons.put("start", start);
        buttons.put("reset", reset);
        buttons.put("create", create);
        buttons.put("faster", faster);
        buttons.put("slower", slower);
        buttons.put("backup", backup);

        buttons.values().forEach(e-> e.setStyle("-fx-background-color:grey;"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
