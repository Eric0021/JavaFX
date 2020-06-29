package sample.Handler.PlayScreen.RatingStrategy;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.FlyWeight.Rating.Rating;
import sample.FlyWeight.Rating.RatingImpl;

public class BadStrategy extends RatingStrategyAbstract{
    public void handle(ImageView ratingIV, Text combo, Text score, int multiplier){
        Rating rating = new RatingImpl("Bad", "/sample/Resources/Images/bad.png");
        ratingIV.setImage(rating.getRatingIV().getImage());
        ratingIV.setOpacity(1);
        super.fadeOut(ratingIV);
        combo.setText("0");
        int scoreInt = Integer.parseInt(score.getText())+1;
        score.setText(Integer.toString(scoreInt));
    }
}
