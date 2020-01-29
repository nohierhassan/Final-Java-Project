package javafxapplication10;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public  class ReplayMenu extends AnchorPane {

    protected final MenuBar menuBar;
    protected final Menu replayMenu;
    protected final MenuItem item0;
    protected final Menu exitMenu;
    protected Scene scene;
    public ReplayMenu(Stage stage) {

        menuBar = new MenuBar();
        replayMenu = new Menu();
        item0 = new MenuItem();
        exitMenu = new Menu();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        menuBar.setPrefHeight(29.0);
        menuBar.setPrefWidth(582.0);

        replayMenu.setId("replayMenu");
        replayMenu.setMnemonicParsing(false);
        replayMenu.setText("Replay ");

        item0.setId("item0");
        item0.setMnemonicParsing(false);
        item0.setText("None");
        item0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new replayGame((stage));
                
            }
        });

        exitMenu.setId("exitMenu");
        exitMenu.setMnemonicParsing(false);
        exitMenu.setText("Exit");

        replayMenu.getItems().add(item0);
        menuBar.getMenus().add(replayMenu);
        menuBar.getMenus().add(exitMenu);
        getChildren().add(menuBar);
        scene = new Scene(this);
        stage.setScene(scene);
        stage.show();

    }
}
