package com.moon.movieReserver;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
