package sample.Handler.StartScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartHandler {

    public void startClick(MouseEvent event) throws IOException {
        String fxmlPath = "/sample/Resources/fxml/songSelectFxml.fxml";
        Parent songSelect = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene songSelectScene = new Scene(songSelect);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(songSelectScene);
    }
}
