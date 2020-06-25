package sample.FlyWeight.Beat;

import javafx.scene.image.ImageView;

// Uses the value object factory to retrieve its own ImageView.
public class BeatImpl implements Beat{
    private final String colour;

    public BeatImpl(String colour, String beatImage){
        this.colour = colour;
        BeatFactory.addBeat(colour, beatImage);
    }

    public String getColour(){
        return this.colour;
    }

    public ImageView getBeatImageView(){
        return BeatFactory.getImageView(this.colour);
    }
}
