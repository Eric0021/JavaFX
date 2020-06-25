package sample.Handler.PlayScreen;

import javafx.scene.image.ImageView;
import sample.FlyWeight.Rating.Rating;
import sample.FlyWeight.Rating.RatingImpl;

public class ScoreHandler {
    private double barHeight;
    private ImageView ratingIV;
    private double beatCentreY;
    private double accuracyTolerance = 10;

    public ScoreHandler(ImageView ratingIV, double beatCentreY, double barHeight) {
        this.ratingIV = ratingIV;
        this.beatCentreY = beatCentreY;
        this.barHeight = barHeight;
    }

    public void setRating(){
        // how much the key press was off from the bar height
        double accurateOffset = beatCentreY - barHeight;

        System.out.println("accurateOS: "+accurateOffset);

        int accuracy = (int)(accurateOffset/accuracyTolerance);

        System.out.println("accuracy: "+accuracy);

        Rating rating;
        switch(Math.abs(accuracy)){
            case 0:
                rating = new RatingImpl("perfect", "/sample/Resources/Images/perfect.png");
                ratingIV.setImage(rating.getRatingIV().getImage());
                ratingIV.setOpacity(1);
                System.out.println("perfect");
                System.out.println(ratingIV.getImage());
                break;
            case 1:
                rating = new RatingImpl("Good", "/sample/Resources/Images/good.png");
                ratingIV.setImage(rating.getRatingIV().getImage());
                ratingIV.setOpacity(1);
                break;
            default:
                rating = new RatingImpl("Bad", "/sample/Resources/Images/bad.png");
                ratingIV.setImage(rating.getRatingIV().getImage());
                ratingIV.setOpacity(1);
                System.out.println("bad");
                break;
        }
    }
}
