
package javafxapplication10;

import javafx.application.Application;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(350);
        primaryStage.setMaxWidth(350);
        primaryStage.setHeight(250);
        primaryStage.setMaxHeight(250);
        primaryStage.show();
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Tic Tac Toe Game");

        Welcome.welcomePlayers(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
