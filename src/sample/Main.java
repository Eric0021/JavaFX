package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Handler.SceneTransHandler;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /// Only one scene can be set into a stage.

        SceneTransHandler helper = new SceneTransHandler();
        helper.transition(primaryStage, "/sample/Resources/fxml/start.fxml");
    }

    public Main() {
        super();
    }

    @Override
    public void init() throws Exception {
        /// this method runs before start, i.e. before the window comes up
    }

    @Override
    public void stop() throws Exception {
        /// this method runs after start() ends
    }

    public static void main(String[] args) {
        launch(args);
    }
}
