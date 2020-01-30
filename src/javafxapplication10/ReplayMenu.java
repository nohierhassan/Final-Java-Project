package javafxapplication10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
     Queue<MenuItem> games = new LinkedList<>();
     Queue<String> fplayers = new LinkedList<>();
      Queue<String> splayers = new LinkedList<>();
    //protected final MenuItem item0;
    protected final Menu exitMenu;
    Stage stage;
    protected Scene scene;
    public ReplayMenu(Stage stage) {
       this.stage = stage;
        addNames();
        setOptions(checkDB());
        menuBar = new MenuBar();
        replayMenu = new Menu();
        addOptions(replayMenu);
        //item0 = new MenuItem();
        exitMenu = new Menu();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        menuBar.setPrefHeight(29.0);
        menuBar.setPrefWidth(582.0); 
        
        replayMenu.setId("replayMenu");
        replayMenu.setMnemonicParsing(false);
        replayMenu.setText("Replay ");

//        item0.setId("item0");
//        item0.setMnemonicParsing(false);
//        item0.setText("None");
//        item0.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                new replayGame((stage));
//                
//            }
//        });

        exitMenu.setId("exitMenu");
        exitMenu.setMnemonicParsing(false);
        exitMenu.setText("Exit");

        //replayMenu.getItems().add(item0);
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
            String query1 = "select count(distinct(gameorder)) as gameorder from games";
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);
            
           
            
            while(rs1.next())
                {
                   retval = rs1.getInt("gameorder");
            
                 
                }
            
            st1.close();
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
    protected void setOptions(int num)
    {
        for(int i = 0; i< num; i++)
        {
           games.add(new MenuItem());
        }
    }
     protected void addOptions(Menu menu)
    {
        int i=0;
        for(MenuItem item : games)
        {
            
           item.setId(Integer.toString(i));
           item.setMnemonicParsing(false);
           item.setText(fplayers.remove() +" vs "+ splayers.remove() );
           item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                
                 new replayGame((stage),Integer.parseInt(item.getId()) );
                
            }
        });
           menu.getItems().add(item);
           i++;
           
        }
    }
        protected void addNames()
    {
        // try to access the DB to get all the names of the played games.
        try
        {
            String url = "jdbc:mysql://localhost:3306/southwind";
            String user = "non";
            String password = "Java123$";
            String query1 = "select * from names";
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);
            
           
            
            while(rs1.next())
                {
//                   retval = rs1.getInt("gameCount");
                    fplayers.add(rs1.getString("fplayer"));
                    splayers.add(rs1.getString("splayer"));
   
                }
            
            st1.close();
            con.close();
            
        }
        catch(SQLException ex)
        {
                ex.printStackTrace();
                
        }
    }

}
