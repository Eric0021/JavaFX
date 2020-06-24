package sample.Handler.SongSelectScreen.Chain;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Handler.SceneTransHandler;
import sample.songs.Cyx;
import sample.songs.Song;

// Receiver class in CoR, transitions song select screen to corresponding play screen, with song name correctly set.
public class CyxSelect extends SelectSongChain {
    private SelectSongChain next;

    public void setNext(SelectSongChain next) {
        this.next = next;
    }

    @Override
    public boolean handle(MouseEvent event) {
        if(((Button)event.getSource()).getText().equals("Cyx")){
            handleHelper(event);
            return true;
        }else{
            if(next!=null){
                return next.handle(event);
            }else{
                return false;
            }
        }
    }

    @Override
    public void handleHelper(MouseEvent event) {
        Song cyx = new Cyx();
        super.setPlayScreenVar(event, cyx);
    }
}
