package sample.FlyWeight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class BeatValueObject implements Beat{
    private final String beatImage;
    private final ImageView beatImageView;
    private final String colour;

    public BeatValueObject(String beatImage, String colour) {
        this.beatImage = beatImage;
        this.colour = colour;

        this.beatImageView =  new ImageView(new Image(beatImage));
    }

    public ImageView getBeatImageView() {
        return beatImageView;
    }

    public String getColour() {
        return colour;
    }

    public String getBeatImage(){
        return this.beatImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeatValueObject that = (BeatValueObject) o;
        return Objects.equals(beatImage, that.beatImage) &&
                Objects.equals(beatImageView, that.beatImageView) &&
                Objects.equals(colour, that.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beatImage, beatImageView, colour);
    }
}
