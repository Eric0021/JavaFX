package sample.Handler.PlayScreen.RatingStrategy;

import javafx.scene.Node;
import sample.Animation.FadeOut;
import sample.Animation.MyAnimation;

public abstract class RatingStrategyAbstract implements RatingStrategy {
    void fadeOut(Node node){
        MyAnimation fadeOut = new FadeOut();
        fadeOut.play(node);
    }
}
