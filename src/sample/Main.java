package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /// Only one scene can be set into a stage.

        /// Scene hierarchy:
        //  Root node (fxml and VBox) -> Branch node (Another VBox/HBox) -> Leaf node (like labels and buttons)

        Parent start = FXMLLoader.load(getClass().getResource("fxml/start.fxml"));
        Scene startScene = new Scene(start);
        primaryStage.setScene(startScene);


//        primaryStage.setTitle("Super hot");
//        primaryStage.setWidth(800);
//        primaryStage.setHeight(800);


//        /// This controls the way components in the scene are laid out.
//        VBox parent = new VBox();
//
//
//        // can also use local images
////        Image image = new Image();
//        // ImageView iv = new ImageView(image);
//
//        /// The position of this label is controlled by parent.
//        Label label1 = new Label("Hello!!");
//        Button button = new Button("Start");
//        ImageView imageView  = new ImageView("https://is2-ssl.mzstatic.com/image/thumb/Purple113/v4/8a/b0/8d/8ab08d69-4c8b-05a5-bfd6-0d2d446a1f42/source/512x512bb.jpg");
//        Label label2 = new Label("Hype", imageView);
//
//        parent.getChildren().addAll(label1, button, label2);
//
//        Scene scene = new Scene(parent);
//        scene.setCursor(Cursor.HAND);
//
//        primaryStage.setScene(scene);



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
