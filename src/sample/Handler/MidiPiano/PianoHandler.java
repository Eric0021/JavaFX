package sample.Handler.MidiPiano;

import sample.Handler.PlayScreen.KeyPress.KeyPressHandler;

import javax.sound.midi.*;

public class PianoHandler {
    public PianoHandler(KeyPressHandler keyPressHandler) {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info: infos) {
            if (info.toString().equals("CASIO USB-MIDI")) {
                try {
                    device = MidiSystem.getMidiDevice(info);

                    // for some reason there are two CASIO midi devices detected by midiSystem.
                    // only the second one can have its transmitter set.
                    Transmitter trans;
                    try {
                        // assuming keyboard only has one MIDI transmitter.
                        trans = device.getTransmitter();
                        MidiReceiver receiver = new MidiReceiver();
                        trans.setReceiver(receiver);
                        keyPressHandler.setReceiver(receiver);
                    } catch (Exception e) {
                        // The first CASIO midi raises an exception here.
                    }

                    device.open();
                } catch (MidiUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
