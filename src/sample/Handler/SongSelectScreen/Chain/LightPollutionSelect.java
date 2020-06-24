package sample.Handler.SongSelectScreen.Chain;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.songs.Cyx;
import sample.songs.LightPollution;
import sample.songs.Song;

public class LightPollutionSelect extends SelectSongChain {
    private SelectSongChain next;

    public void setNext(SelectSongChain next) {
        this.next = next;
    }

    @Override
    public boolean handle(MouseEvent event) {
        if(((Button)event.getSource()).getText().equals("LightPollution")){
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
        Song lightPollution = new LightPollution();
        super.setPlayScreenVar(event, lightPollution);
    }
}
