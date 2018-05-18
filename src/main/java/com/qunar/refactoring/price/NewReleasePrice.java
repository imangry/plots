package com.qunar.refactoring.price;

import com.qunar.refactoring.Movie;

public class NewReleasePrice extends Price {

    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    public int getFrequentRenterPoints(int daysRented){
        return (daysRented > 1)? 2 : 1;
    }
}