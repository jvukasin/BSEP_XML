package com.xml.MegaTravelAgent.dto;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDTO {

    List<RatingDTO> ratings = new ArrayList<>();
    double ratingAvg;

    public FeedbackDTO() {
    }

    public FeedbackDTO(List<RatingDTO> ratings, double ratingAvg) {
        this.ratings = ratings;
        this.ratingAvg = ratingAvg;
    }

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDTO> ratings) {
        this.ratings = ratings;
    }

    public double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }
}
