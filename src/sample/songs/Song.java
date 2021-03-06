package sample.songs;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

public interface Song {
    String getName();

    int getDuration();

    String getArtist();

    MediaPlayer getPlayer();

    float getBPM();

    double getOffSet();

}
