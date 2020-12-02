/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutdown;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author laith
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField timeInput;
    
    //This allows the textfield to do the even if pressed enter
    @FXML
    private void pressEnter(ActionEvent event) throws IOException {
        handleButtonAction(event);
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        Runtime runtime = Runtime.getRuntime();
        String br = timeInput.getText();
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  
        System.out.print("Enter No. of Seconds after which You want your Computer to Shutdown :");
        long a=Long.parseLong(br);
        a=a*60;
        
        //this line is a command 
        Process proc = runtime.exec("shutdown -s -t " +a);
    
        System.out.println("You clicked me!");
        
        label.setText("Your PC will shut down in "+a+" seconds");
        //------------------------------------------------------------------------
        
        //timer label
        //also opening a new scene for the timer
        //try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLtime.fxml"));
        Pane root = (Pane) fxmlLoader.load();
             Stage stage = new Stage();
             stage.setScene(new Scene(root));  
             stage.setTitle("Timer");
             stage.show();
               Label timeText = new Label("Time till Shut Down: ");
               Label companyName = new Label("DipShipDevs LLC ");
               timeText.setFont(Font.font ("Verdana", 15));
               timeText.setTextFill(Color.web("black", 0.8));
               timeText.relocate(10, 50);
               companyName.relocate(130, 130);
               setTimer(timeText,a);
  
                 root.getChildren().add(timeText);
                 root.getChildren().add(companyName);
                 //} catch(Exception e) {
     //   e.printStackTrace();
  // }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //this is my timer method 
    public void setTimer(Label timeText,long a) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        long interval=a;
        public void run() {
            
            if(interval > 0)
            {
                timeText.setFont(Font.font ("Verdana", 15));
                Platform.runLater(() -> timeText.setText("Time till Shut Down: "+interval+"s"));
                System.out.println(interval);
               interval--;
            }
            else
                timer.cancel();
        }
    }, 1000,1000);
}
    //this is not being used but its good to have it if i want a custom time 
        public int getInterval(){
              int interval=6;
              return interval;
        }
}
