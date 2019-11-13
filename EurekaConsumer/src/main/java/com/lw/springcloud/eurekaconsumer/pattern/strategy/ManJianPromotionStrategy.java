package com.lw.springcloud.eurekaconsumer.pattern.strategy;

public class ManJianPromotionStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("满减");
    }

}
