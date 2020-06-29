package sample.Handler.MidiPiano;

import javax.sound.midi.*;

public class MidiReceiver implements Receiver {
    private MidiObserver observer;

    public void send(MidiMessage msg, long timeStamp) {
        // midi info is sent here through msg.
        byte[] input = msg.getMessage();

        // input[2] gives the event type.
        // 64 means it was a release key event.
        // otherwise it is a press-key event, then input[2] tells the velocity of the key press.
        if(input[2]!=64){
            observer.update(input);
        }
    }

    public void registerObserver(MidiObserver observer){
        this.observer = observer;

    }

    public void close() {
    }
}


