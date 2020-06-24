package sample.Handler.SongSelectScreen.Chain;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.songs.Cyx;
import sample.songs.Song;
import sample.songs.Straits;

public class StraitsSelect extends SelectSongChain {
    private SelectSongChain next;

    public void setNext(SelectSongChain next) {
        this.next = next;
    }

    @Override
    public boolean handle(MouseEvent event) {
        if(((Button)event.getSource()).getText().equals("Straits")){
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
        Song straits = new Straits();
        super.setPlayScreenVar(event, straits);
    }
}
