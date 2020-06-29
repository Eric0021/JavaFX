package sample.Handler.MidiPiano;

import sample.Handler.PlayScreen.KeyPress.KeyPressHandler;

import java.util.ArrayList;
import java.util.List;

public class MidiObserver {
    private KeyPressHandler subject;
    private List<byte[]> inputs = new ArrayList<>();
    private boolean waiting = false;

    public MidiObserver(KeyPressHandler subject) {
        this.subject = subject;
    }

    // tells the KeyPressHandler to handle the midi key press.
    void update(byte[] input){
        inputs.add(input);
        if(!waiting){
            // catch all inputs within 0.1 seconds, for chords.

            waiting = true;
            Thread thread = new Thread(()->{
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                subject.midiKeyPress(inputs);
                waiting = false;
                inputs.clear();
            });
            thread.start();
        }
    }

}
