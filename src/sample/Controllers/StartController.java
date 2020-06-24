package sample.Controllers;

import sample.Handler.StartScreen.StartHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class StartController {
    private StartHandler startHandler = new StartHandler();


    @FXML
    void creditClick(MouseEvent event) {

    }

    @FXML
    void quitClick(MouseEvent event) {

    }

    @FXML
    void settingsClick(MouseEvent event) {

    }

    @FXML
    void startClick(MouseEvent event) {
        try{
            startHandler.startClick(event);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
