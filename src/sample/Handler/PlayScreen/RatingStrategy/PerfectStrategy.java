package sample.Handler.PlayScreen.RatingStrategy;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.FlyWeight.Rating.Rating;
import sample.FlyWeight.Rating.RatingImpl;

public class PerfectStrategy extends RatingStrategyAbstract{
    public void handle(ImageView ratingIV, Text combo, Text score, int multiplier){
        Rating rating = new RatingImpl("perfect", "/sample/Resources/Images/perfect.png");
        ratingIV.setImage(rating.getRatingIV().getImage());
        super.fadeOut(ratingIV);
        int comboInt = Integer.parseInt(combo.getText())+1;
        combo.setText(Integer.toString(comboInt));
        int scoreInt = Integer.parseInt(score.getText())+10 + comboInt * 5;
        score.setText(Integer.toString(scoreInt));
    }
}
