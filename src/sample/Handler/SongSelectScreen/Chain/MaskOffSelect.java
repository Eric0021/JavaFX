package sample.Handler.SongSelectScreen.Chain;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.songs.Cyx;
import sample.songs.MaskOff;
import sample.songs.Song;

public class MaskOffSelect extends SelectSongChain {
    private SelectSongChain next;

    public void setNext(SelectSongChain next) {
        this.next = next;
    }

    @Override
    public boolean handle(MouseEvent event) {
        if(((Button)event.getSource()).getText().equals("MaskOff")){
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
        Song maskOff = new MaskOff();
        super.setPlayScreenVar(event, maskOff);
    }
}
