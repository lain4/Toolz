package sample.Util;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.Model.Cell;
import sample.Pattern.Pattern;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IO {

    public static final String PATH = "./pattern.csv";
    public static final String PATH_BACKUP = "./pattern_backup.csv";
    public static ArrayList<String> list = new ArrayList<>();

    public static void deleteFromFile(Pattern pattern){
        if(Files.exists(Paths.get(PATH))){
            try(BufferedReader br = new BufferedReader(new FileReader(PATH))){
                int i = 0;
                while(br.readLine()!=null){
                    String p = pattern.getName();
                    if(!br.readLine().split(",")[0].equals(p)){
                        list.add(br.readLine());
                        i++;
                    }
                }
            }catch(IOException ex){
                ex.getMessage();
            }
            Paths.get(PATH).toFile().delete();
            try(PrintWriter fw = new PrintWriter(new FileWriter(PATH))){
                int i = 0;
                while(i < list.size()){
                    fw.print(list.get(i));
                    i++;
                }
            }catch(IOException ex){
                ex.getMessage();
            }
        list.clear();
        }
    }


    public static void savePattern(GridPane grid, Pattern pattern){

            if(pattern.getName() != null  || grid.getChildren().size()!=0) {

                if (Files.exists(Paths.get(PATH))) {
                    try (PrintWriter fw = new PrintWriter(new FileWriter(PATH, true))) {
                        fw.print(pattern.getName() + "," + pattern.getX() + "," + pattern.getY() + ",");
                        grid.getChildren().forEach((d -> {
                            fw.print(((Cell) d).getState() ? "1" : "0");
                        }));
                        fw.print("\n");
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                } else {
                    try (PrintWriter fw = new PrintWriter(new FileWriter(PATH, false))) {
                        fw.print(pattern.getName() + "," + pattern.getX() + "," + pattern.getY() + ",");
                        grid.getChildren().forEach((d -> {
                            fw.print(((Cell) d).getState() ? "1" : "0");
                        }));
                        fw.print("\n");
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            }
    }
}
