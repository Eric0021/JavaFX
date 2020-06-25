package sample.Handler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.PlayController;
import sample.songs.Song;

public class SceneTransHandler {

    public void transition(Stage primaryStage, String fxmlPath) throws Exception {
        /// Only one scene can be set into a stage.

        Parent start = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene startScene = new Scene(start);
        primaryStage.setScene(startScene);

        primaryStage.show();
    }

    public void playScreenTransition(Stage primaryStage, String fxmlPath, Song song) throws Exception{
        // sets the song for controller and transition to play screen scene.

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent playScreen = (Parent)fxmlLoader.load();

        PlayController controller = fxmlLoader.<PlayController>getController();
        controller.setSong(song);

        Scene scene = new Scene(playScreen);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
