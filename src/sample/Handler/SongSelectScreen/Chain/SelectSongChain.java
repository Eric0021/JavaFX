package sample.Handler.SongSelectScreen.Chain;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Controllers.PlayController;
import sample.Controllers.SongSelectController;
import sample.Handler.SceneTransHandler;
import sample.songs.Song;

public abstract class SelectSongChain {
    public abstract boolean handle(MouseEvent event);

    public abstract void handleHelper(MouseEvent event);

    public abstract void setNext(SelectSongChain next);

    void setPlayScreenVar(MouseEvent event, Song song) {
//        cyx.getPlayer().play();
        SceneTransHandler handler = new SceneTransHandler();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            handler.transition(stage, "/sample/Resources/fxml/playScreen.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((Text)(stage.getScene().lookup("#songName"))).setText(song.getName());

    }
}
