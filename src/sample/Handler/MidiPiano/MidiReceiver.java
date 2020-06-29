package sample.Handler.MidiPiano;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class MidiReceiver implements Receiver {
    private String name;
    private MidiObserver observer;

    public MidiReceiver(String name) {
        this.name = name;
    }

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


