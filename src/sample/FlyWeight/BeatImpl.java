package sample.FlyWeight;

import javafx.scene.image.ImageView;

// Uses the value object factory to retrieve its own ImageView.
public class BeatImpl implements Beat{
    private BeatFactory factory = new BeatFactory();
    private final String colour;

    public BeatImpl(String colour, String beatImage){
        this.colour = colour;
        factory.addBeat(colour, beatImage);
    }

    public String getColour(){
        return this.colour;
    }

    public ImageView getBeatImageView(){
        return factory.getImageView(this.colour);
    }
}
