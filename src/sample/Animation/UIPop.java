package sample.Animation;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Control;
import sample.Animation.MyAnimation;

public class UIPop implements MyAnimation {
    @Override
    public void play(Node node) {
        // type casting, this can cause errors.
        Control control = (Control)node;

        double animationDuration = 1;
        double maxSizeMultipler = 1.3;

        double originalWidth = control.getWidth();
        double originalHeight = control.getHeight();

        AnimationTimer animator = new AnimationTimer() {
            double currentMultipler = 1;
            @Override
            public void handle(long arg0) {
                // every update (60 times a second), the UI will increase/decrease its size by a fixed amount.
                // Size change multipler every second = (maxSizeMultipler - 1)/(60 * animationDuration/2))
                // -> /2 because half of time is spent on increasing/decreasing respectively.
                currentMultipler += (maxSizeMultipler -1)/(60 * animationDuration/2);
                control.setPrefSize(originalWidth * currentMultipler, originalHeight * currentMultipler);
            }
        };

        animator.start();
    }
}
