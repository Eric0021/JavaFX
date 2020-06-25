package sample.Handler.PlayScreen.RatingStrategy;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.HashMap;

public class StrategyHandler {
    private HashMap<RatingEnum, RatingStrategy> strategies = new HashMap<>();
    private HashMap<RatingEnum, Integer> multipliers = new HashMap<>();

    public StrategyHandler(int perfectScMul, int goodScMul, int badScMul) {
        multipliers.put(RatingEnum.PERFECT, perfectScMul);
        multipliers.put(RatingEnum.GOOD, goodScMul);
        multipliers.put(RatingEnum.BAD, badScMul);

        strategies.put(RatingEnum.PERFECT, new PerfectStrategy());
        strategies.put(RatingEnum.GOOD, new GoodStrategy());
        strategies.put(RatingEnum.BAD, new BadStrategy());

    }

    public void handle(RatingEnum ratingEnum, ImageView ratingIV, Text combo, Text score){
        RatingStrategy strategy = strategies.get(ratingEnum);
        strategy.handle(ratingIV, combo, score, multipliers.get(ratingEnum));
    }


}
