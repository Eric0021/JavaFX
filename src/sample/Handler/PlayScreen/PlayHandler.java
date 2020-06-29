package sample.Handler.PlayScreen;

import javafx.animation.AnimationTimer;
import javafx.scene.CacheHint;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import sample.FlyWeight.Beat.Beat;
import sample.FlyWeight.Beat.BeatImpl;
import sample.Handler.NoteTranslator;
import sample.songs.Song;

import java.util.*;

public class PlayHandler {
    private double endHeight;
    private Song song;
    private double speedMultiplier = 0.7;

    // all existing beats
    private HashMap<Pane, List<StackPane>> beatSPs = new HashMap<>();

    private double songPositionInBeats;
    private double secPerBeat;

    private List<Pane> columns;

    private double beatWidth;
    private double beatHeight;

    private TreeMap<Double, List<String>> notes;

    private double nextNoteBeat;
    private double startingTime;
    private double offsetTime = 0;

    public PlayHandler(double endHeight, Song song, TreeMap<Double, List<String>> notes,
                       List<Pane> columns, double beatWidth, double beatHeight) {
        this.song = song;
        this.endHeight = endHeight;
        this.columns = columns;
        this.beatWidth = beatWidth;
        this.beatHeight = beatHeight;

        this.notes = notes;
        nextNoteBeat = notes.firstKey();

        secPerBeat = 60f / song.getBPM();

        startingTime = System.currentTimeMillis();
        fall();
        spawnBeats();

        Thread thread = new Thread(this::play);
        thread.start();
    }

    private void play() {
        // play the song at a delay, so that beats spawn first.
        try {
            // *4 for 4 quarter beats.
            long delay = (long) (secPerBeat * 4 * (1 / speedMultiplier) * 1000);
            Thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        song.getPlayer().play();
    }

    private void spawnBeats() {
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                // whenever the current beat reaches/passes the beat to spawn the next notes, spawn those notes.
                if (songPositionInBeats >= nextNoteBeat) {
                    List<String> notesOnbeat = notes.get(nextNoteBeat);

                    Random rand = new Random();
                    // bound between 1 and 4, inclusive.
                    makeBeat(rand.nextInt((4 - 1) + 1) + 1, notesOnbeat);

                    nextNoteBeat = notes.higherKey(nextNoteBeat);
                }
            }
        };

        animator.start();
    }

    private void makeBeat(int colNum, List<String> notes) {
        // colNum needs to be 1 - 4.
        if (colNum > 4 || colNum < 1) {
            return;
        }

        StackPane beatSP = new StackPane();
        makeAndSetIV(beatSP);
        makeAndSetText(notes, beatSP);

        Pane column = getColumn(colNum);
        if (column != null) {
            column.getChildren().add(beatSP);

            if (beatSPs.get(column) == null) {
                beatSPs.put(column, new ArrayList<>(Arrays.asList(beatSP)));
            } else {
                beatSPs.get(column).add(beatSP);
            }
        }
    }

    private Pane getColumn(int colNum) {
        switch (colNum) {
            case 1:
                return columns.get(0);
            case 2:
                return columns.get(1);
            case 3:
                return columns.get(2);
            case 4:
                return columns.get(3);
            default:
                return null;
        }
    }

    private void makeAndSetText(List<String> notes, StackPane stackPane) {
        int count = 0;
        String noteStringFull = "";
        for (String noteString : notes) {
            if (count != 0) {
                noteStringFull = noteStringFull.concat("+");
            }
            noteStringFull = noteStringFull.concat(NoteTranslator.translate(noteString));
            count++;
        }
        Text noteText = new Text(noteStringFull);
        stackPane.getChildren().add(noteText);

        int centreX = 17;
        noteText.setTranslateX(centreX - count * 10);
        noteText.setFill(Paint.valueOf("white"));
    }

    private void makeAndSetIV(StackPane stackPane) {
        Beat beat = new BeatImpl("yellow", "/sample/Resources/Images/beat.png");
        ImageView beatIV = beat.getBeatImageView();
        beatIV.setCache(true);
        beatIV.setCacheHint(CacheHint.SPEED);
        beatIV.setFitWidth(beatWidth);
        beatIV.setFitHeight(beatHeight);

        stackPane.getChildren().add(beatIV);
    }

    private void fall() {
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
        // how many seconds since song has started, adjusting for offset (offset is in seconds).
        double songPosition = song.getPlayer().getCurrentTime().toMillis() - song.getOffSet() * 1000;

        // which beat the song is currently at.
        if (song.getPlayer().getCurrentTime().toSeconds() == 0) {
            offsetTime = System.currentTimeMillis() - startingTime;
        }

        songPositionInBeats = ((songPosition + offsetTime) / secPerBeat) / 1000;

        HashMap<Pane, ArrayList<StackPane>> finishedBeats = new HashMap<>();
        for (Pane column : beatSPs.keySet()) {
            for (StackPane beat : beatSPs.get(column)) {
                // if the beat has reached the endHeight, remove it.
                if (beat.getLayoutY() >= endHeight || beat.getOpacity() == 0) {
                    beat.setOpacity(0);

                    // if the beat needs to be removed, add it to a list and remove them all at the end.
                    if (finishedBeats.get(column) == null) {
                        finishedBeats.put(column, new ArrayList<>(Arrays.asList(beat)));
                    } else {
                        finishedBeats.get(column).add(beat);
                    }
                } else {
                    beat.setLayoutY(beat.getLayoutY() + (766 / (4 * secPerBeat) / 60) * (speedMultiplier));
                }
            }
        }

        for (Pane column : finishedBeats.keySet()) {
            for (StackPane beat : finishedBeats.get(column)) {
                beatSPs.get(column).remove(beat);

                // remove the imageView from the column as well.
                column.getChildren().remove(beat);
            }
        }
    }
}
