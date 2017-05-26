package sample.Pattern;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Model.Cell;
import sample.Util.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Stream;

import static sample.Main.Main.patternview;

public class PatternManager {

    private SimpleIntegerProperty xProp, yProp;
    private Stage stage;
    private Scene scene;
    private GridPane grid;
    private VBox newBox;
    private HBox downrow;
    private Button save;
    private Button delete;
    private Button close;
    private static TextField name;
    private TextField xSize, ySize;
    private ComboBox<Pattern> patternBox;
    public static ObservableList<Pattern> patternlist;

    public PatternManager(){
    }

    public void setUp(){
            newBox = new VBox();
            downrow = new HBox();

            grid = new GridPane();

            save = new Button("Save");
            delete = new Button("Delete");
            close = new Button("Close");

            name = new TextField("name");
            xSize = new TextField("x");
            ySize = new TextField("y");

            xProp = new SimpleIntegerProperty();
            yProp = new SimpleIntegerProperty();

            patternlist = FXCollections.observableArrayList();

            patternBox = new ComboBox<>();
            patternBox.setItems(patternlist);
            initPatternView(IO.PATH);

            scene = new Scene(newBox, 500, 500);
            stage = new Stage();


            newBox.setStyle("-fx-background-color: brown");
            save.setLayoutX(400);

            xSize.setPrefWidth(50);
            ySize.setPrefWidth(50);

            downrow.getChildren().addAll(xSize,ySize, name, patternBox, delete,  save, close);
            newBox.getChildren().addAll(downrow, grid);

            showGrid();

            patternBox.setOnAction(e->{
                Pattern p = patternBox.getSelectionModel().getSelectedItem();
                name.setText(p.getName());
                xSize.setText(String.valueOf(p.getX()));
                ySize.setText(String.valueOf(p.getY()));

                grid.getChildren().clear();

                Iterator<String> iter = Arrays.asList(p.getPattern().split("")).iterator();
                for(int x = 0; x < p.getX(); x++){
                    for(int y =0 ; y < p.getY(); y++){
                        Cell cell = new Cell(x,y);
                        grid.add(cell,x,y);
                        cell.setState(!iter.next().equals("0"));
                    }
                }
            });

            delete.setOnAction(e->{
                Pattern p = patternBox.getSelectionModel().getSelectedItem();
                if(patternlist.contains(p)) patternlist.remove(p);
                IO.deleteFromFile(p);

            });

            save.setOnAction(e->{
                if(grid.getChildren().size()!=0){
                    Pattern pattern = createPatternFromStage();
                    if(!patternview.getChildrenUnmodifiable().contains(pattern)){
                        //Pattern serialisieren
                        IO.savePattern(grid, pattern);
                        //Erstellt Laufzeitabbildung der Patterns, wenn noch nicht geschehen und fügt solche hinzu)
                        addToPatterns(pattern);
                    }

                }
                //Pattern erstellen

            });

            close.setOnAction(e->stage.close());

            stage.setTitle("Pattern Crafter");
    }

    private void showGrid(){
        name.setOnAction(e-> {
            grid.getChildren().clear();

            try{
                xProp.setValue(Integer.parseInt(xSize.getText()));
                yProp.setValue(Integer.parseInt(ySize.getText()));

            }catch(NumberFormatException ex){
                ex.getMessage();
            }

            for(int x = 0; x < xProp.get(); x++){
                for(int y =0 ; y < yProp.get(); y++){
                    Cell cell = new Cell(x,y);
                    grid.add(cell,x,y);
                }
            }
        });
    }

    public void  openPatternManager(){
        stage.setScene(scene);
        stage.show();
    }

    //Patternset von file aus füllen
    public void initPatternView(String path){

        try(Stream<String> stream = Files.lines(Paths.get(path))){
            stream.forEach(e->{
                String[] parts = e.split(",");
                Pattern pattern = new Pattern(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
                patternlist.add(pattern);
            });
        }catch(IOException ex){
            ex.getMessage();
        }
    }

    public void addToPatterns(Pattern pattern){
        patternlist.add(pattern);

    }

    public static Pattern getChosenPattern(){
        if(patternview.getSelectionModel().getSelectedItem()==null) System.out.println("Ist null");
        return patternview.getSelectionModel().getSelectedItem();

    }

    private Pattern createPatternFromStage(){
        StringBuilder sb = new StringBuilder();
        grid.getChildren().forEach(d-> {
            sb.append(((Cell) d).getState() ? "1" : "0");
        });
        String p = sb.toString();
        return new Pattern(name.getText(), Integer.parseInt(xSize.getText()), Integer.parseInt(ySize.getText()), p);
    }

}

//ToDo: Keine Duplikate speichern, (ListView im linken Bereich) edit und delete von patterns, Torus beim Einfügen, verschönern, Umstrukturierung
