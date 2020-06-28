package sample.Handler;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class MidiParseHandler {
    private String midiName;

    public MidiParseHandler(String midiName) {
        this.midiName = midiName;
    }

    public TreeMap<Double, List<String>> parse() {
        convertMidiCsv();
        String content = null;
        try {
            content = Files.readString(Paths.get("src/sample/Resources/Midi/Csv/" + midiName + ".csv"), StandardCharsets.US_ASCII);
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
            e.printStackTrace();
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

    private void convertMidiCsv() {
        try {
            String batPath = new File("src/sample/Resources/Midi/MidiCsv/ex.bat").getAbsolutePath();
            String midicsvPath = new File("src/sample/Resources/Midi/MidiCsv/Midicsv.exe").getAbsolutePath();
            String midiPath = new File("src/sample/Resources/Midi/"+midiName+".mid").getAbsolutePath();
            String csvPath = new File("src/sample/Resources/Midi/Csv/"+midiName+".csv").getAbsolutePath();

            // edit the .bat to parse the correct midi.
            FileWriter fileW = new FileWriter(batPath);
            String exWrite = '"' + midicsvPath + '"' + " " + '"' + midiPath + '"' + " " + '"' + csvPath + '"';
            fileW.write(exWrite);
            fileW.close();

            Process p = null;
            try {
                // the dir paramter should be the directory of the bat file, not the path of the bat file itself.
                File bat = new File("src/sample/Resources/Midi/MidiCsv");
                p = Runtime.getRuntime().exec("cmd /c ex.bat", null, bat);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}