package sample.Controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import sample.FlyWeight.Beat;
import sample.FlyWeight.BeatImpl;
import sample.Handler.PlayScreen.FallHandler;
import sample.songs.Song;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PlayController {
    private Song song;
    private FallHandler handler;

    @FXML
    private Text score;

    @FXML
    private Pane firstCol;

    @FXML
    private Pane secondCol;

    @FXML
    private Pane thirdCol;

    @FXML
    private Pane fourthCol;

    @FXML
    private Text rating;

    @FXML
    private Text countDown;

    @FXML
    private Line bar;

    @FXML
    void initialize() {
        startGame();
    }

    private void startGame(){
        // first count down, then spawn beats.
        Task<Void> task = new Task<>(){
            @Override
            public Void call(){
                countDown();
                return null;
            }
        };
        task.setOnSucceeded(e -> startSong());

        new Thread(task).start();
    }

    private void countDown() {
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

    private void startSong(){
        song.getPlayer().play();
        FallHandler handler = new FallHandler(bar.getStartY(), firstCol.getHeight(), song.getBPM(),
                Arrays.asList(firstCol, secondCol, thirdCol, fourthCol));
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
