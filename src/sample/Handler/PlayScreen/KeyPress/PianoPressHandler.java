package sample.Handler.PlayScreen.KeyPress;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.Handler.NoteTranslator;
import sample.Handler.PlayScreen.ScoreHandler;

public class PianoPressHandler {
    private ImageView ratingIV;
    private double beatHalfHeight;
    private HBox hbox;

    @FXML
    private Button button1;
    @FXML
    private Text score;

    @FXML
    private Text combo;

    PianoPressHandler(ImageView ratingIV, Text score, Text combo, double beatHalfHeight,
                      Button button1, HBox hbox) {
        this.ratingIV = ratingIV;
        this.score = score;
        this.combo = combo;
        this.beatHalfHeight = beatHalfHeight;
        this.button1 = button1;
        this.hbox = hbox;
    }

    // for handling midi key presses.
    void midiKeyPress(byte[] input) {
        System.out.println("pressing");

        // miss height is button's bottom.
        int missHeight = (int) (button1.getLayoutY() + button1.getHeight());

        // tries to find the closest beat across all 4 columns.
        int closestY = Integer.MAX_VALUE;
        StackPane closestBeat = null;
        for (Node hboxChild : hbox.getChildren()) {
            if (hboxChild instanceof Pane) {
                for (Node node : ((Pane) hboxChild).getChildren()) {
                    if (!(node instanceof StackPane) || !(node.getId() == null)) {
                        // should only get beats.
                        continue;
                    }
                    StackPane beat = (StackPane) node;
                    int distance = (int) Math.abs(button1.getLayoutY() - beat.getLayoutY());
                    if (distance < closestY) {
                        if (beat.getLayoutY() < missHeight) {
                            // if above the miss height

                            closestBeat = beat;
                            closestY = distance;
                        }
                    }
                }
            }
        }

        // "press" this beat.
        if (closestBeat != null) {
            if (pressedCorrectNote(input, closestBeat)) {
                closestBeat.setOpacity(0);
                // Beat's centre Y coordinate is used to determine accuracy. -> beat half height + beat's Y coordinate.
                ScoreHandler handler = new ScoreHandler(ratingIV, score, combo, (beatHalfHeight + closestBeat.getLayoutY()), button1.getLayoutY());
                handler.setRating();
            } else {
                // didn't press the correct notes, should display wrong!
                System.out.println("wrong");
            }
        }
    }

    // for handling midi key presses.
    private boolean pressedCorrectNote(byte[] input, StackPane beat) {
        String beatNote = "";
        for (Node node : beat.getChildren()) {
            if (node instanceof Text) {
                beatNote = ((Text) node).getText();
            }
        }

        String noteRaw = Integer.toString((int) (input[1]));
        String notePress = NoteTranslator.translate(noteRaw);
        return notePress.equals(beatNote);
    }
}
