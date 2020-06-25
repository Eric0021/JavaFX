package sample.FlyWeight.Rating;

import javafx.scene.image.ImageView;

public class RatingImpl implements Rating {
    private final String rating;

    public RatingImpl(String rating, String ratingImagePath){
        this.rating = rating;
        RatingFactory.addRating(rating, ratingImagePath);
    }

    public String getRating(){
        return this.rating;
    }

    public ImageView getRatingIV(){
        return RatingFactory.getImageView(this.rating);
    }
}
