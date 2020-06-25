package sample.Handler.PlayScreen;

import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;

public class CountdownHandler {

    public void countdown(Text countDown){
        // count down from 3 to start the game.

        countDown.setOpacity(1);
        countDown.setText("READY");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // count down from 3
        for (int i = 3; i > 0; i--) {
            countDown.setText(Integer.toString(i));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countDown.setOpacity(0);
    }
}
