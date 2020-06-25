package sample.FlyWeight.Beat;

import javafx.scene.image.ImageView;

import java.util.HashMap;

public class BeatFactory {
    // Factory that stores the flyweight/value objects for beats.
    private static HashMap<String, Beat> beats = new HashMap<>();

    public static void addBeat(String colour, String beatImage) {
        // add beat to factory list if it is not already stored.
        // only one image shall be stored for each colour.

        if (!beats.containsKey(colour)) {
            beats.put(colour, new BeatValueObject(beatImage, colour));
        }
    }

    public static ImageView getImageView(String colour){
        return beats.get(colour).getBeatImageView();
    }
}
