package sample.Handler.PlayScreen.RatingStrategy;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public interface RatingStrategy {
    void handle(ImageView ratingIV, Text combo, Text score, int multipler);
}
