package sample.Handler.PlayScreen;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.CacheHint;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.FlyWeight.Beat;
import sample.FlyWeight.BeatImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FallHandler {
    private double barHeight;
    private double endHeight;
    private float BPM;
    private List<ImageView> beats = new ArrayList<>();
    private Thread fallThread;
    private Thread spawnBeatsThread;

    private float songPosition;
    private float songPositionInBeats;
    private long startTime;
    private float secPerBeat;

    private List<Pane> columns = new ArrayList<>();
    int prevBeat = 0;

    public FallHandler(double barHeight, double endHeight, float BPM, List<Pane> columns) {
        this.barHeight = barHeight;
        this.BPM = BPM;
        this.endHeight = endHeight;
        this.columns = columns;

        secPerBeat = 60f / BPM;
        startTime = System.currentTimeMillis();

        fall();
        spawnBeats();
    }

    private void spawnBeats() {
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                if (prevBeat != (int)songPositionInBeats) {
                    Random rand = new Random();
                    makeBeat(rand.nextInt((4-1)+1) + 1);
                    prevBeat = (int) songPositionInBeats;
                }
            }
        };

        animator.start();
    }

    private void makeBeat(int col_num) {
        // col_num needs to be 1 - 4.
        if (col_num > 4 || col_num < 1) {
            return;
        }

        Beat beat = new BeatImpl("yellow", "/sample/Resources/Images/beat.png");
        ImageView beatIV = beat.getBeatImageView();
        beatIV.setCache(true);
        beatIV.setCacheHint(CacheHint.SPEED);
        beatIV.setY(0);
        beatIV.setX(2);
        beatIV.setFitWidth(221);
        beatIV.setFitHeight(48);

        Pane column = null;
        switch (col_num) {
            case 1:
                column = columns.get(0);
                break;
            case 2:
                column = columns.get(1);
                break;
            case 3:
                column = columns.get(2);
                break;
            case 4:
                column = columns.get(3);
                break;
            default:
                break;
        }
        if (column != null) {
            column.getChildren().add(beatIV);
        }

        beats.add(beatIV);
    }

    public void fall() {
        // make every beat fall.

        // the animator aims to hit 60 fps.
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                update_beats();
            }
        };

        animator.start();
    }

    private void update_beats() {
        // how many seconds since song has started.
        songPosition = System.currentTimeMillis() - startTime;

        // which beat the song is currently at.
        songPositionInBeats = (songPosition / secPerBeat) / 1000;

        List<ImageView> finishedBeats = new ArrayList<>();
        for (ImageView beat : beats) {
            // if the beat has reached the endHeight, remove it.
            if (beat.getY() >= endHeight) {
                beat.setOpacity(0);
                finishedBeats.add(beat);
            } else {
                beat.setY(beat.getY() + BPM / 60f);
            }
        }

        for (ImageView beat : finishedBeats) {
            beats.remove(beat);
            System.out.println("removed");
        }
    }
}
