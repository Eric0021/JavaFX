package sample.Handler.MidiPiano;

import sample.Handler.PlayScreen.KeyPress.KeyPressHandler;

public class MidiObserver {
    private KeyPressHandler subject;

    public MidiObserver(KeyPressHandler subject) {
        this.subject = subject;
    }

    // tells the KeyPressHandler to handle the midi key press.
    public void update(byte[] input){
        subject.midiKeyPress(input);
    }

}
