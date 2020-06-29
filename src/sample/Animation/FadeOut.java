package sample.Animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeOut implements MyAnimation {
    @Override
    public void play(Node node) {
        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setNode(node);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDuration(new Duration(1200));
        fadeOut.play();
    }
}
