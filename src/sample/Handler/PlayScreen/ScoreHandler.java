package sample.Handler.PlayScreen;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.Handler.PlayScreen.RatingStrategy.RatingEnum;
import sample.Handler.PlayScreen.RatingStrategy.StrategyHandler;

public class ScoreHandler {
    private double barHeight;
    private ImageView ratingIV;
    private double beatCentreY;
    private double accuracyTolerance = 14;
    private Text score;
    private Text combo;
    private StrategyHandler strategyHandler;

    public ScoreHandler(ImageView ratingIV, Text score, Text combo, double beatCentreY, double barHeight) {
        this.ratingIV = ratingIV;
        this.combo = combo;
        this.beatCentreY = beatCentreY;
        this.barHeight = barHeight;
        this.score = score;

        strategyHandler = new StrategyHandler(10, 5, 1);
    }

    public void setRating(){
        combo.setOpacity(1);

        // how much the key press was off from the bar height
        double accurateOffset = beatCentreY - barHeight;

        int accuracy = (int)(accurateOffset/accuracyTolerance);

        switch(Math.abs(accuracy)){
            case 0:
                strategyHandler.handle(RatingEnum.PERFECT, ratingIV, combo, score);
                break;
            case 1:
                strategyHandler.handle(RatingEnum.GOOD, ratingIV, combo, score);
                break;
            default:
                strategyHandler.handle(RatingEnum.BAD, ratingIV, combo, score);
                break;
        }
    }
}
