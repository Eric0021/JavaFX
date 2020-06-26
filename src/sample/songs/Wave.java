package sample.songs;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Wave implements Song{
    private final String name = "Wave";
    private final int duration = 295;
    private final String artist = "Atori";
    private final MediaPlayer player;
    private float BPM = 135;
    private double offset = 0.14;

    public Wave(){
        Media media = new Media("file:///C:/Users/eric/Documents/sem 4/SOFT3202/untitled/src/sample/Resources/SongLib/GiornoSolo.mp3".replaceAll(" ", "%20"));
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
