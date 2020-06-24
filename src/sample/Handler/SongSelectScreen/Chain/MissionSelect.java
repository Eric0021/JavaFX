package sample.Handler.SongSelectScreen.Chain;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.songs.Cyx;
import sample.songs.Mission;
import sample.songs.Song;

public class MissionSelect extends SelectSongChain {
    private SelectSongChain next;

    public void setNext(SelectSongChain next) {
        this.next = next;
    }

    @Override
    public boolean handle(MouseEvent event) {
        if(((Button)event.getSource()).getText().equals("Mission")){
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
        Song mission = new Mission();
        super.setPlayScreenVar(event, mission);
    }
}
