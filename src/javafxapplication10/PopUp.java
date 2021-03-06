/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication10;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUp {
    
    private Label message;
    private Button quitButton;
    private Button replayButton;
    private Button continueButton;
    private Scene scene;
    protected static int gameNumber;
    
    private VBox layout;
    
    public PopUp(Stage stage) {
        message = new Label();
        message.setStyle("-fx-font-color:red");
        quitButton = new Button("Quit");
        replayButton  = new Button("Replay the Game");
        continueButton = new Button("Continue Playing");
        
        layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        VBox.setMargin(message, new Insets(10));
        VBox.setMargin(quitButton, new Insets(10));
        VBox.setMargin(replayButton, new Insets(10));
        VBox.setMargin(continueButton, new Insets(10));
        layout.getChildren().add(message);
        layout.getChildren().add(quitButton);
        layout.getChildren().add(replayButton);
        layout.getChildren().add(continueButton);
        
        scene = new Scene(layout);
        
        stage.setScene(scene);
        
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent arg0) {
                stage.close();
            }
        });
         replayButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent arg0) {
                //System.out.println("replay is pressed!");
               new  ReplayMenu(stage);
            }
        });
        
        continueButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                
                PopUp.gameNumber++;
                Welcome.welcomePlayers(stage);
            }
        });
    }
    

    public void setMessage(String passedMessage) {
        message.setText(passedMessage);
    }
    
   
}
