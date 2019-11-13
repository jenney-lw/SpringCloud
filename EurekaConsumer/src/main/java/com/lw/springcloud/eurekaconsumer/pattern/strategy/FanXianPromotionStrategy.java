package com.lw.springcloud.eurekaconsumer.pattern.strategy;

public class FanXianPromotionStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("返现");
    }

}
