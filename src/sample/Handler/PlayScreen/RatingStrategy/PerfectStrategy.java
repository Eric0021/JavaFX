package sample.Handler.PlayScreen.RatingStrategy;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.FlyWeight.Rating.Rating;
import sample.FlyWeight.Rating.RatingImpl;

public class PerfectStrategy implements RatingStrategy{
    public void handle(ImageView ratingIV, Text combo, Text score, int multiplier){
        Rating rating = new RatingImpl("perfect", "/sample/Resources/Images/perfect.png");
        ratingIV.setImage(rating.getRatingIV().getImage());
        ratingIV.setOpacity(1);
        int comboInt = Integer.parseInt(combo.getText())+1;
        combo.setText(Integer.toString(comboInt));
        int scoreInt = Integer.parseInt(score.getText())+10 + comboInt * 5;
        score.setText(Integer.toString(scoreInt));
    }
}
