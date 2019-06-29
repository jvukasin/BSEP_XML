package com.megatravel.accommodationservice.dto;

public class RatingAverageDTO {

    private double ratingAvg;
    private int no_ratings;

    public RatingAverageDTO() {
    }

    public RatingAverageDTO(double ratingAvg, int no_ratings) {
        this.ratingAvg = ratingAvg;
        this.no_ratings = no_ratings;
    }

    public double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public int getNo_ratings() {
        return no_ratings;
    }

    public void setNo_ratings(int no_ratings) {
        this.no_ratings = no_ratings;
    }
}
