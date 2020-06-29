package sample.Handler.PlayScreen.KeyPress;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import sample.Handler.MidiPiano.MidiObserver;
import sample.Handler.MidiPiano.MidiReceiver;

import java.util.List;

public class KeyPressHandler {
    private MidiReceiver receiver;
    private KeyboardPressHandler keyboardHandler;
    private PianoPressHandler pianoHandler;

    public KeyPressHandler(ImageView ratingIV, Text score, Text combo, double beatHalfHeight,
                           Button button1, Button button2, Button button3, Button button4, HBox hbox) {

        this.keyboardHandler =
                new KeyboardPressHandler(ratingIV, score, combo, beatHalfHeight, button1, button2, button3, button4);
        this.pianoHandler =
                new PianoPressHandler(ratingIV, score, combo, beatHalfHeight, button1, hbox);
    }

    public void setReceiver(MidiReceiver receiver) {
        this.receiver = receiver;
        registerObserver();
    }

    private void registerObserver() {
        MidiObserver observer = new MidiObserver(this);
        receiver.registerObserver(observer);
    }

    public void keyboardPress(ActionEvent event){
        keyboardHandler.keyboardPress(event);
    }

    public void midiKeyPress(List<byte[]> inputs){
        pianoHandler.midiKeyPress(inputs);
    }
}
