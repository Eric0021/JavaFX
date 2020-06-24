package sample.Handler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneTransHandler {

    public void transition(Stage primaryStage, String fxmlPath) throws Exception {
        /// Only one scene can be set into a stage.

        Parent start = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene startScene = new Scene(start);
        primaryStage.setScene(startScene);

        primaryStage.show();
    }
}
