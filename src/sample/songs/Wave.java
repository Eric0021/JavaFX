package sample.songs;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Wave implements Song{
    private final String name = "GiornoReal";
    private final int duration = 295;
    private final String artist = "Atori";
    private final MediaPlayer player;
    private float BPM = 135;
    private double offset = 0.14;

    public Wave(){
        String songPath = new File("src/sample/Resources/SongLib/GiornoSolo.mp3").getAbsolutePath();
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
