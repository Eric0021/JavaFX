package sample.Controllers;

import sample.Handler.SongSelectScreen.Chain.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.List;

public class SongSelectController {
    @FXML
    public void selectSong(MouseEvent event){
        List<SelectSongChain> chain = Arrays.asList(
                new CyxSelect(),
                new WaveSelect(),
                new LightPollutionSelect(),
                new MaskOffSelect(),
                new StraitsSelect(),
                new AlitaSelect(),
                new MissionSelect(),
                new SingularitySelect(),
                new IllusionSelect()
        );

        // sets the next in chain for each chain receiver.
        for(int i =0 ; i<chain.size(); i++){
            if(i==chain.size()-1){
                chain.get(i).setNext(null);
                break;
            }
            chain.get(i).setNext(chain.get(i+1));
        }

        // start from first receiver in chain.
        SelectSongChain start = chain.get(0);

        start.handle(event);
    }

}
