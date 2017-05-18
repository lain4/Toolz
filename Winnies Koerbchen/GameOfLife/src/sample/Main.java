package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

import java.util.HashMap;

public class Main extends Application {

    private static Timeline loop;
    private static HashMap<String, Button> buttons = new HashMap<>();
    private static SimpleBooleanProperty start = new SimpleBooleanProperty();    private static boolean _pause = false;

    @Override
    public void start(Stage primaryStage) throws Exception{

        initButtons();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setStyle("-fx-background-color:lightgrey;");
        HBox menu = new HBox();

        menu.setSpacing(10);

        Board grid = new Board();
        Scene scene = new Scene(root);

        menu.getChildren().addAll(buttons.get("start"),buttons.get("reset"));
        root.getChildren().addAll(menu, grid);
        
        Blend b = new Blend();
        b.setOpacity(0);
        
        grid.effectProperty()
                .bind(Bindings
                        .when(start)
                        .then(new Blend())
                        .otherwise(b));


        loop(grid);
        reset(grid);


        primaryStage.setTitle("GameOfLife");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initButtons(){
        Button start = new Button("Start"), reset = new Button("Reset");
        buttons.put("start", start);
        buttons.put("reset", reset);

        buttons.values().forEach(e-> e.setStyle("-fx-background-color:grey;"));
    }

    private void loop(Board grid){
        buttons.get("start").setOnAction(e-> {
            start.setValue(true);
            loop = new Timeline(new KeyFrame(Duration.millis(300), el -> {
                grid.update();
            }));
            loop.setCycleCount(Animation.INDEFINITE);
            loop.play();

        });
    }

    private void reset(Board grid){
        buttons.get("reset").setOnAction(e->{
            start.setValue(false);
            if(loop!=null) {
                loop.stop();
            }
            grid.reset();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
