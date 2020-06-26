package sample.songs;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Singularity implements Song{
    private final String name = "Singularity";
    private final int duration = 240; // exactly 4 minutes somehow.
    private final String artist = "Atori";
    private final MediaPlayer player;
    private float BPM = 93;
    private double offset = 0;

    public Singularity(){
        Media media = new Media("file:///C:/Users/eric/Documents/sem 4/SOFT3202/untitled/src/sample/Resources/SongLib/Deadman.mp3".replaceAll(" ", "%20"));
        player = new MediaPlayer(media);
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public float getBPM() {
        return BPM;
    }

    @Override
    public double getOffSet() {
        return this.offset;
    }
}
