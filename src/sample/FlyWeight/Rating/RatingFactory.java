package sample.FlyWeight.Rating;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.FlyWeight.Beat.Beat;
import sample.FlyWeight.Beat.BeatValueObject;

import java.util.HashMap;

public class RatingFactory {
    // Factory that stores the flyweight/value objects for ratings.
    private static HashMap<String, ImageView> ratings = new HashMap<>();

    public static void addRating(String rating, String ratingImagePath) {
        // add beat to factory list if it is not already stored.
        // only one image shall be stored for each rating.

        if (!ratings.containsKey(rating)) {
            ratings.put(rating, new ImageView(new Image(ratingImagePath)));
        }
    }

    public static ImageView getImageView(String rating){
        return ratings.get(rating);
    }


}
