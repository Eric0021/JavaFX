package sample.Handler.PlayScreen;

import javafx.animation.AnimationTimer;
import javafx.scene.CacheHint;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.FlyWeight.Beat.Beat;
import sample.FlyWeight.Beat.BeatImpl;
import sample.Handler.MidiParseHandler;
import sample.songs.Song;

import java.util.*;

public class PlayHandler {
    private double endHeight;
    private Song song;
    private double speedMultiplier = 0.7;

    // all existing beats
    private HashMap<Pane, List<ImageView>> beats = new HashMap<>();

    private double songPositionInBeats;
    private double secPerBeat;
    double songPosition;

    private List<Pane> columns;
    private int prevBeat = 0;

    private double beatWidth;
    private double beatHeight;

    private MidiParseHandler parser = new MidiParseHandler();
    private TreeMap<Double, List<String>> notes;

    private double nextNoteBeat;
    private double startingTime;
    private double offsetTime = 0;

    public PlayHandler(double endHeight, Song song, List<Pane> columns, double beatWidth, double beatHeight) {
        this.song = song;
        this.endHeight = endHeight;
        this.columns = columns;
        this.beatWidth = beatWidth;
        this.beatHeight = beatHeight;

        notes = parser.parse("C:/Users/eric/Documents/sem 4/SOFT3202/untitled/src/sample/Resources/Midi/New folder/data.csv");
        nextNoteBeat = notes.firstKey();

        secPerBeat = 60f / song.getBPM();

        startingTime = System.currentTimeMillis();
        fall();
        spawnBeats();

        Thread thread = new Thread(this::play);
        thread.start();
    }

    private void play(){
        try {
            long delay = (long)(secPerBeat*4*(1/speedMultiplier)*1000);
            System.out.println(delay);
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
                    nextNoteBeat = notes.higherKey(nextNoteBeat);
                    List<String> notesOnbeat = notes.get(nextNoteBeat);

                    Random rand = new Random();
                    // bound between 1 and 4, inclusive.
                    makeBeat(rand.nextInt((4 - 1) + 1) + 1);

//                    System.out.println("spawning notes at beat: "+nextNoteBeat);
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
        beatIV.setFitWidth(beatWidth);
        beatIV.setFitHeight(beatHeight);

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

        if (beats.get(column) == null) {
            beats.put(column, new ArrayList<>(Arrays.asList(beatIV)));
        } else {
            beats.get(column).add(beatIV);
        }
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
        if(song.getPlayer().getCurrentTime().toSeconds()==0){
            offsetTime= System.currentTimeMillis() - startingTime;
        }

        songPositionInBeats = ((songPosition+offsetTime) / secPerBeat) / 1000;
//        System.out.println(songPositionInBeats);

        HashMap<Pane, ArrayList<ImageView>> finishedBeats = new HashMap<>();

        for (Pane column : beats.keySet()) {
            for (ImageView beat : beats.get(column)) {
                // if the beat has reached the endHeight, remove it.
                if (beat.getY() >= endHeight || beat.getOpacity() == 0) {
                    beat.setOpacity(0);

                    // if the beat needs to be removed, add it to a list and remove them all at the end.
                    if (finishedBeats.get(column) == null) {
                        finishedBeats.put(column, new ArrayList<>(Arrays.asList(beat)));
                    } else {
                        finishedBeats.get(column).add(beat);
                    }
                } else {
                    beat.setY(beat.getY() + (766/(4*secPerBeat)/60)*(speedMultiplier));
                }
            }
        }

        for (Pane column : finishedBeats.keySet()) {
            for (ImageView beat : finishedBeats.get(column)) {
                beats.get(column).remove(beat);

                // remove the imageView from the column as well.
                column.getChildren().remove(beat);
            }
        }
    }
}
