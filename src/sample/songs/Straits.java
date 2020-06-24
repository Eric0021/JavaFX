package sample.songs;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Straits implements Song{
    private final String name = "Straits";
    private final int duration = 240; // exactly 4 minutes somehow.
    private final String artist = "Atori";
    private final AudioClip player;

    public Straits(){
        Media media = new Media("file:///C:/Users/eric/Documents/sem 4/SOFT3202/untitled/src/sample/Resources/SongLib/Deadman.mp3".replaceAll(" ", "%20"));
        player = new AudioClip(media.getSource());
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

    public AudioClip getPlayer() {
        return player;
    }
}
