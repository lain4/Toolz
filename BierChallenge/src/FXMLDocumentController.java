/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beerchallenge;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    public TextField t1,t2,t3,t4,t5,t6;
    
    @FXML 
    private AnchorPane an;
    
    @FXML
    private VBox v1,v2,v3,v4,v5,v6;
    
    @FXML
    private Button gameButton;
    
    @FXML
    private ComboBox<String> combi;
    
    
    @FXML
    private void toGame(ActionEvent event) throws IOException{
            //catch teamnames, teamspieler und irgendwo speichern
            TextField[] teamFields = {t1,t2,t3,t4,t5,t6};
            //String[] teamNames = getNames(teamFields);
            //Teams teams = new Teams(teamNames);
            
            Stage stage = (Stage) an.getScene().getWindow();
            BierChallenge challenge = new BierChallenge();
            Scene scene = stage.getScene();
            scene.setRoot(challenge.createContent());
        
    }
    
    @FXML
    private void addTeam(ActionEvent event) throws IOException {
        VBox[] textboxes = {v1,v2,v3,v4,v5,v6};
        String s = combi.getValue();
        for(VBox v: textboxes) v.setVisible(false);
        for(int i = 0; i<Integer.valueOf(s);i++){
            textboxes[i].setVisible(true);
        }
    }
    
    /*public static String[] getNames(TextField[] tf){
        String[] teamnames = new String[tf.length];
        for(int i = 0; i < tf.length;i++){
            if(tf[i].getText()==null)
            teamnames[i] = tf[i].getText();
        }
        return teamnames;
        
    }*/
    
    /*TODO:
    
    
    
    
    */
    
 

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
    }
    
    
}
