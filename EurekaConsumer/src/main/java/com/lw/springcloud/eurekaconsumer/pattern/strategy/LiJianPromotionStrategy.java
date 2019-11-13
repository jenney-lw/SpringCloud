package com.lw.springcloud.eurekaconsumer.pattern.strategy;

public class LiJianPromotionStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("立减");
    }

}
