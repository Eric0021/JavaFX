package sample.Handler;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MidiParseHandler {
    public TreeMap<Double, List<String>> parse(String csvPath) {
        String content = null;
        try {
            content = Files.readString(Paths.get(csvPath), StandardCharsets.US_ASCII);
        } catch (Exception e) {
            e.printStackTrace();
        }

        double msecPerQNote = 0;

        List<String> lines = new ArrayList<>();
        TreeMap<Double, List<String>> beats = new TreeMap<>();

        try {
            Scanner scanner = new Scanner(content);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (Exception e) {

        }

        boolean reading = false;

        // Read each line of text in the file
        for (int i = 0; i < lines.size(); i++) {
            String[] data = lines.get(i).split(",");
            String type = data[2];
            String midiTime = "";
            String note = "";
            if (data.length > 4) {
                midiTime = data[1];
                note = data[4];
            }

            for (int k = 0; k < data.length; k++) {
                if (data[k].equals(" Tempo")) {
                    msecPerQNote = Double.parseDouble(data[k + 1]);
                }

                if (!reading && data[k].equals(" Program_c")) {
                    reading = true;
                }
            }

            if (reading) {
                if (type.equals(" Note_on_c")) {
                    // we have to find the duration of this note, by finding the next note_off_c with same note.
//                    for (int j = i + 1; j < lines.size(); j++) {
//                        String[] nextData = lines.get(j).split(",");
//                        String nextType = nextData[2];
//                        String nextMidiTime = "";
//                        String nextNote = "";
//                        if (nextData.length >= 5) {
//                            nextMidiTime = nextData[1];
//                            nextNote = nextData[4];
//                        }
//
//                        if (nextType.equals(" Note_off_c") && nextNote.equals(note)) {
//                            double midiTicks = Double.parseDouble(nextMidiTime) - Double.parseDouble(midiTime);
//                            double numQNotes = midiTicks / 96;
//                            double duration = numQNotes * msecPerQNote;
//
//                            notes.add(note);
//                            durations.add(Double.toString(duration));
//                        }
//                    }

                    // the beat which the note is hit.
                    double beat = Double.parseDouble(midiTime) / 384;
                    if (beats.get(beat) == null) {
                        List<String> values = new ArrayList<>(Arrays.asList(note));
                        beats.put(beat, values);
                    } else {
                        beats.get(beat).add(note);
                    }
                }
            }
        }

        return beats;
    }
}