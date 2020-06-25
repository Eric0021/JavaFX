package sample.FlyWeight.Beat;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class BeatValueObject implements Beat{
    // The image is cached, but each beat is a new imageView.

    private final Image beatImage;
    private final String colour;

    public BeatValueObject(String beatImage, String colour) {
        this.colour = colour;

        this.beatImage =  new Image(beatImage);
    }

    public ImageView getBeatImageView() {
        return new ImageView(beatImage);
    }

    public String getColour() {
        return colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeatValueObject that = (BeatValueObject) o;
        return Objects.equals(beatImage, that.beatImage) &&
                Objects.equals(colour, that.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beatImage, colour);
    }
}
