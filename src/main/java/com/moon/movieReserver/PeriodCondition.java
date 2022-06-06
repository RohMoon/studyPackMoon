package com.moon.movieReserver;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition{

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.getStartedTime().getDayOfWeek().equals(dayOfWeek) &&
                startTime.compareTo(screening.getStartedTime().toLocalTime()) <= 0 &&
                endTime.compareTo(screening.getStartedTime().toLocalTime()) >= 0;
    }
}
