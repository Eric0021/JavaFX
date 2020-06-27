package sample.FlyWeight.Notes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.HashMap;

public class NoteFactory {
    // Factory that stores the flyweight/value objects for ratings.
    private static HashMap<String, Text> notes = new HashMap<>();

    public static void addNote(String note) {
        // add beat to factory list if it is not already stored.
        // only one image shall be stored for each note.

        if (!notes.containsKey(note)) {
            notes.put(note, new Text(note));
        }
    }

    public static Text getNote(String note){
        return notes.get(note);
    }


}
