package sample.Handler.PlayScreen.KeyPress;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.Handler.PlayScreen.ScoreHandler;

public class KeyboardPressHandler {
    private ImageView ratingIV;
    private double beatHalfHeight;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;

    @FXML
    private Text score;

    @FXML
    private Text combo;

    KeyboardPressHandler(ImageView ratingIV, Text score, Text combo, double beatHalfHeight,
                           Button button1, Button button2, Button button3, Button button4) {
        this.ratingIV = ratingIV;
        this.score = score;
        this.combo = combo;
        this.beatHalfHeight = beatHalfHeight;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
        this.button4 = button4;

        setKeyPress();
    }

    // for handling keyboard presses.
    private void setKeyPress() {
        HBox bp = (HBox) button1.getParent().getParent();
        bp.addEventHandler(KeyEvent.KEY_PRESSED, this::specifyKeys);
    }

    // for handling keyboard presses.
    private void specifyKeys(KeyEvent ev) {
        if (ev.getCode() == KeyCode.D) {
            button1.fire();
        } else if (ev.getCode() == KeyCode.F) {
            button2.fire();
        } else if (ev.getCode() == KeyCode.J) {
            button3.fire();
        } else if (ev.getCode() == KeyCode.K) {
            button4.fire();
        }
        ev.consume();
    }

    // for handling keyboard presses.
    @FXML
    void keyboardPress(ActionEvent event) {
        // miss height is button's bottom.
        Button button = (Button) event.getSource();
        Pane column = (Pane) ((Node) event.getSource()).getParent();
        int missHeight = (int) (button.getLayoutY() + button.getHeight());

        // find the beat that is the closest to the bar in terms of height.
        // only count those not past the miss height.
        int closestY = Integer.MAX_VALUE;
        StackPane closest = null;
        for (Node node : column.getChildren()) {
            if (!(node instanceof StackPane) || !(node.getId() == null)) {
                // should only get beats and text.
                continue;
            }
            StackPane beat = (StackPane) node;
            int distance = (int) Math.abs(button.getLayoutY() - beat.getLayoutY());
            if (distance < closestY) {
                if (beat.getLayoutY() < missHeight) {
                    // if above the miss height

                    closest = beat;
                    closestY = distance;
                }
            }
        }

        if (closest != null) {
            closest.setOpacity(0);
            // Beat's centre Y coordinate is used to determine accuracy. -> beat half height + beat's Y coordinate.
            ScoreHandler handler = new ScoreHandler(ratingIV, score, combo, (beatHalfHeight+closest.getLayoutY()), button.getLayoutY());
            handler.setRating();
        }
    }
}
