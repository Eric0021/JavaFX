package sample.songs;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Cyx implements Song{
    private final String name = "Cyx";
    private final int duration = 240; // exactly 4 minutes somehow.
    private final String artist = "Atori";
    private final MediaPlayer player;
    private float BPM = 93;
    // offset of song in seconds.
    private double offset = -0.1;

    public Cyx(){
        String songPath = new File("src/sample/Resources/SongLib/Deadman.mp3").getAbsolutePath();
        songPath = songPath.replace("\\","/");
        Media media = new Media("file:///"+songPath.replaceAll(" ", "%20"));
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
