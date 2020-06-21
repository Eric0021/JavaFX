package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /// Only one scene can be set into a stage.

        /// Scene hierarchy:
        //  Root node (fxml and VBox) -> Branch node (Another VBox/HBox) -> Leaf node (like labels and buttons)

        primaryStage.setTitle("Super hot");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);


        /// This controls the way components in the scene are laid out.
        VBox parent = new VBox();

        /// The position of this label is controlled by parent.
        Label label1 = new Label("Hello!!");
        Button button = new Button("Start");
        parent.getChildren().addAll(label1, button);

        Scene scene = new Scene(parent);
        scene.setCursor(Cursor.HAND);

        primaryStage.setScene(scene);



        primaryStage.show();




//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    }

    public Main() {
        super();
    }

    @Override
    public void init() throws Exception {
        /// this method runs before start, i.e. before the window comes up
        System.out.println("Before");
    }

    @Override
    public void stop() throws Exception {
        /// this method runs after start() ends
        System.out.println("After");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
