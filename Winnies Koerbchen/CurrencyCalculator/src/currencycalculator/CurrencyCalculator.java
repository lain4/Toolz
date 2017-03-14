package currencycalculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CurrencyCalculator extends Application {
    
    public static ArrayList<String> fillComboBox(){
        String file = "src/currencyCalculator/bist.csv";
        String line;
        ArrayList<String> list = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            while((line = br.readLine()) != null) {
                list.add(line);
            }   
            br.close();         
        }
        catch(FileNotFoundException e) {
            System.out.println("Cannot open File" + file);
        }
        catch(IOException e){
            System.out.println("Error reading File" + file);
        }
        System.out.println(list.size());
        return list;
    }
    
    @Override
    public void start(Stage primaryStage) {
                
        ComboBox l1 = new ComboBox();
        Button sw = new Button("Switch");
        ComboBox l2 = new ComboBox();
 
        ArrayList<String> liste = fillComboBox();
      
        liste.forEach((s) -> {
                 l1.getItems().add(s);
                 l2.getItems().add(s);
        });
   
        sw.setOnAction(e -> {
            String combo1 =(String) l1.getValue();
            l1.setValue(l2.getValue());
            l2.setValue(combo1);
        });
      
        
        TextField t1 = new TextField("Betrag");
        Button calc = new Button();
        TextField t2 = new TextField("Ergebnis");
        t2.setEditable(false);

        calc.setOnAction(e -> {
            
            String first = (((String) l1.getValue()).split(","))[1];
            String second = (((String) l2.getValue()).split(","))[1];
            
            float factor = Float.parseFloat(t1.getText());
            
            Converter c = new Converter( factor, first, second);
            String r =Float.toString(c.result);
            t2.setText(r);
            
        });
        
       
        VBox vbox = new VBox();
        HBox h1 = new HBox();
        HBox h2 = new HBox();

        h1.getChildren().addAll(l1,sw,l2);
        h2.getChildren().addAll(t1,calc, t2);
        vbox.getChildren().addAll(h1,h2);

        Scene scene = new Scene(vbox, 300, 80, Color.BROWN);
        primaryStage.setTitle("Currency Magic");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
