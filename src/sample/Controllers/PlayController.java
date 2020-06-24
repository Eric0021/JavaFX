package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;

public class PlayController {
    @FXML
    private Text songName;

    @FXML
    private Text score;

    @FXML
    private Pane firstCol;

    @FXML
    private ImageView secondCol;

    @FXML
    private ImageView thirdCol;

    @FXML
    private ImageView fourthCol;

    @FXML
    private Text rating;

    @FXML
    private Text countDown;

    @FXML
    void initialize() {
        countDown();
    }

    private void countDown(){
        // count down from 3 to start the game.

        Thread thread = new Thread(()->{
            countDown.setOpacity(1);
            countDown.setText("READY");
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            // count down from 3
            for(int i = 3; i>0; i--){
                countDown.setText(Integer.toString(i));
                System.out.println(i);
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            countDown.setOpacity(0);
        });

        thread.start();
    }

    private void startGame(){

    }

}
