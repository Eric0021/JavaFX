package sample.Controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.Handler.PlayScreen.CountdownHandler;
import sample.Handler.PlayScreen.KeyPressHandler;
import sample.Handler.PlayScreen.PlayHandler;
import sample.songs.Song;

import java.util.Arrays;

public class PlayController {
    private Song song;
    private double beatWidth;
    private double beatHeight;
    private KeyPressHandler keyHandler;

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
    private ImageView ratingIV;

    @FXML
    private Text countDown;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;

    @FXML
    private Text combo;

    @FXML
    void initialize() {
        startGame();
    }

    private void startGame() {
        setBeatDim();

        // first count down, then start the song.
        Task<Void> task = new Task<>() {
            @Override
            public Void call() {
                countDown();
                return null;
            }
        };
        task.setOnSucceeded(e -> startSong());

        new Thread(task).start();
    }

    private void setBeatDim(){
        beatWidth = button1.getPrefWidth();
        beatHeight = button1.getPrefHeight();
    }

    private void countDown() {
        CountdownHandler countDownHandler = new CountdownHandler();
        countDownHandler.countdown(countDown);
    }

    private void startSong() {
        song.getPlayer().play();
        PlayHandler playHandler = new PlayHandler(firstCol.getHeight(), song.getBPM(),
                Arrays.asList(firstCol, secondCol, thirdCol, fourthCol), beatWidth, beatHeight);

        keyHandler = new KeyPressHandler(ratingIV, score, combo,beatHeight/2,
                button1, button2, button3, button4);
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @FXML
    void buttonPress(ActionEvent event) {
        keyHandler.keyPress(event);
    }
}
