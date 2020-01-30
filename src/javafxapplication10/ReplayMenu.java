package javafxapplication10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
       
        System.out.println(checkDB());
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
    protected int checkDB()
    {
        int retval = -1;
        
         try
        {
            String url = "jdbc:mysql://localhost:3306/southwind";
            String user = "non";
            String password = "Java123$";
            String query = "select count(distinct(gamecount)) as gameCount from test";
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
           
            
            while(rs.next())
                {
                   retval = rs.getInt("gameCount");
                
               
                }
            
            st.close();
            con.close();
            
        }
        
        catch(SQLException ex)
        {
                ex.printStackTrace();
                
        }
         finally
         {
             return retval;
         }
        
    }
}
