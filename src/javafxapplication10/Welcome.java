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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class Welcome {
    static String firstPlayerName;
    static String secondPlayerName;
    
    public static void welcomePlayers(Stage primaryStage) {
        
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(30));

        Label firstPlayerLabel = new Label("Player 1");
        TextField firstPlayerTxt = new TextField();
        Label secondPlayerLabel = new Label("Player 2");
        TextField secondPlayerTxt = new TextField();
        Label errorLabel = new Label("");
        Button letsPlayBtn = new Button("Let's Play !");

        GridPane.setMargin(firstPlayerLabel, new Insets(10));
        GridPane.setMargin(firstPlayerTxt, new Insets(10));
        GridPane.setMargin(secondPlayerLabel, new Insets(10));
        GridPane.setMargin(secondPlayerTxt, new Insets(10));
        GridPane.setMargin(letsPlayBtn, new Insets(10));
        GridPane.setMargin(errorLabel, new Insets(10));
       

        grid.add(firstPlayerLabel, 0, 0);
        grid.add(firstPlayerTxt, 1, 0);
        grid.add(secondPlayerLabel, 0, 1);
        grid.add(secondPlayerTxt, 1, 1);
        grid.add(errorLabel, 0, 2);
        grid.add(letsPlayBtn, 0, 3);
        GridPane.setColumnSpan(errorLabel, 2);
        GridPane.setColumnSpan(letsPlayBtn, 2);
        
        Scene firstScene = new Scene(grid);
       
        primaryStage.setScene(firstScene);

        letsPlayBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                if (firstPlayerTxt.getText().isEmpty()
                        || secondPlayerTxt.getText().isEmpty()) {
                    errorLabel.setText("Names are mandatory !");
                    errorLabel.setStyle("-fx-text-fill: red");
                    firstPlayerTxt.requestFocus();
                    return;
                }
                firstPlayerName =  firstPlayerTxt.getText();
                secondPlayerName =  secondPlayerTxt.getText();
                addInDB();

                new GameBoard(primaryStage);
            }

        });
        
        firstPlayerTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent arg0) {
                errorLabel.setText("");
            }
        });
        
        secondPlayerTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent arg0) {
                errorLabel.setText("");
            }
        });
        
    }
       private static void addInDB()
    {
        try
        {
             String url = "jdbc:mysql://localhost:3306/southwind";
            String user = "non";
            String password = "Java123$";
            
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt =con.prepareStatement("insert into names (gameorder,fplayer,splayer) values (?,?,?)");
            stmt.setInt(1,PopUp.gameNumber);
            stmt.setString(2,Welcome.firstPlayerName);
            stmt.setString(3,Welcome.secondPlayerName);
            
            
            
            int rs = stmt.executeUpdate();
            con.close();
            
        }
        catch(SQLException ex)
        {
                ex.printStackTrace();
                
        }
      
    }

}
