package com.moon.movieReserver;

import com.moon.java.collectionFramework.arrayList.ArrayList;

import java.util.Arrays;

public class DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening){
        for (DiscountCondition each:conditions) {
            if (each.isSatisfiedBy(screening)){
                return getDiscountAmount(screening);
            }
        }
        return Money.Zero;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
