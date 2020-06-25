package sample.Handler.PlayScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class KeyPressHandler {
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

    public KeyPressHandler(ImageView ratingIV, double beatHalfHeight,
                           Button button1, Button button2, Button button3, Button button4) {
        this.ratingIV = ratingIV;
        this.beatHalfHeight = beatHalfHeight;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
        this.button4 = button4;

        setKeyPress();
    }

    private void setKeyPress() {
        HBox bp = (HBox) button1.getParent().getParent();
        bp.addEventHandler(KeyEvent.KEY_PRESSED, this::specifyKeys);
    }

    private void specifyKeys(KeyEvent ev) {
        if (ev.getCode() == KeyCode.D) {
            button1.fire();
        } else if (ev.getCode() == KeyCode.F) {
            button2.fire();
        } else if (ev.getCode() == KeyCode.G) {
            button3.fire();
        } else if (ev.getCode() == KeyCode.H) {
            button4.fire();
        }
        ev.consume();
    }

    @FXML
    public void keyPress(ActionEvent event) {
        // miss height is button's bottom.
        Button button = (Button) event.getSource();
        Pane column = (Pane) ((Node) event.getSource()).getParent();
        int missHeight = (int) (button.getLayoutY() + button.getHeight());

        // find the beat that is the closest to the bar in terms of height.
        // only count those not past the miss height.
        int closestY = Integer.MAX_VALUE;
        ImageView closest = null;
        for (Node node : column.getChildren()) {
            if (!(node instanceof ImageView) || !(node.getId() == null)) {
                // should only get beats.
                continue;
            }
            ImageView beat = (ImageView) node;
            int distance = (int) Math.abs(button.getLayoutY() - beat.getY());
            if (distance < closestY) {
                if (beat.getY() < missHeight) {
                    // if above the miss height

                    closest = beat;
                    closestY = distance;
                }
            }
        }

        if (closest != null) {
            closest.setOpacity(0);
            // Beat's centre Y coordinate is used to determine accuracy. -> beat half height + beat's Y coordinate.
            ScoreHandler handler = new ScoreHandler(ratingIV, (beatHalfHeight+closest.getY()), button.getLayoutY());
            handler.setRating();
        }
    }
}
