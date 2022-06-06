package com.moon.movieReserver;


import com.moon.java.shareStudioReservation.Reservation;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    private Money calculateFee(int audienceCount){
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public Reservation reserve(Customer customer, int audienceCount){
        return new Reservation(customer, this, calculateFee(audienceCount),audienceCount);
    }

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartedTime(){
        return whenScreened;
    }

    public boolean isSequence(int sequence){
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee;
    }

}
