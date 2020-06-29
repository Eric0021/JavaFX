package sample.Handler.PlayScreen.KeyPress;

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

import java.util.List;

class PianoPressHandler {
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
    void midiKeyPress(List<byte[]> inputs) {
        // miss height is button's bottom.
        int missHeight = (int) (button1.getLayoutY() + button1.getHeight());

        // Need to determine whether multiple inputs should be treated as a chord, or separate notes.
        // if the next coming beat is not a chord, then separate the inputs (don't treat them as a chord)
        // If the next coming beat is a chord, treat inputs as a chord.

        // tries to find the closest beat across all 4 columns.

        // chord index is the first index which all subsequent inputs should be treated as a single chord.
        int chordIndex = 0;
        int closestY = Integer.MAX_VALUE;
        StackPane closestBeat = null;
        for (int i = 0; i < inputs.size(); i++) {
            for (Node hboxChild : hbox.getChildren()) {
                if (hboxChild instanceof Pane) {
                    for (Node node : ((Pane) hboxChild).getChildren()) {
                        if ((node instanceof StackPane)) {
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
            }

            if (isChord(closestBeat)) {
                chordIndex = i;
                break;
            } else {
                // not a chord, so just treat this input as a separate note.
                System.out.println("single note: " + inputs.get(i)[1]);

                // "press" this beat.
                if (pressedCorrectNote(inputs.get(i), closestBeat)) {
                    closestBeat.setOpacity(0);
                    // Beat's centre Y coordinate is used to determine accuracy. -> beat half height + beat's Y coordinate.
                    ScoreHandler handler = new ScoreHandler(ratingIV, score, combo, (beatHalfHeight + closestBeat.getLayoutY()), button1.getLayoutY());
                    handler.setRating();
                } else {
                    // didn't press the correct notes, should display wrong!
                    System.err.println("wrong");
                }
            }
        }

        // treat the rest of the inputs as one chord.
        if (pressedCorrectChord(inputs.subList(chordIndex, inputs.size()), closestBeat)) {
            String notes = concatAllPressed(inputs.subList(chordIndex, inputs.size()));
            System.out.println(notes);

            closestBeat.setOpacity(0);
            // Beat's centre Y coordinate is used to determine accuracy. -> beat half height + beat's Y coordinate.
            ScoreHandler handler = new ScoreHandler(ratingIV, score, combo, (beatHalfHeight + closestBeat.getLayoutY()), button1.getLayoutY());
            handler.setRating();
        }else{
            System.err.println("wrong chord");
        }
    }

    private boolean isChord(StackPane beat) {
        String beatNote = "";
        for (Node node : beat.getChildren()) {
            if (node instanceof Text) {
                beatNote = ((Text) node).getText();
            }
        }

        return beatNote.contains("+");
    }

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

    private boolean pressedCorrectChord(List<byte[]> inputs, StackPane beat) {
        // treats inputs as a single chord.

        String beatNote = "";
        for (Node node : beat.getChildren()) {
            if (node instanceof Text) {
                beatNote = ((Text) node).getText();
            }
        }

        String notesPressed = concatAllPressed(inputs);

        return notesPressed.equals(beatNote);
    }

    private String concatAllPressed(List<byte[]> inputs){
        int count = 0;
        String notesPressed = "";
        for (byte[] input : inputs) {
            if (count != 0) {
                notesPressed = notesPressed.concat("+");
            }
            String noteRaw = Integer.toString((int) (input[1]));
            String notePress = NoteTranslator.translate(noteRaw);
            notesPressed = notesPressed.concat(notePress);
            count++;
        }

        return notesPressed;
    }
}
