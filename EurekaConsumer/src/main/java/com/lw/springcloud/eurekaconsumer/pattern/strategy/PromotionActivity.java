package com.lw.springcloud.eurekaconsumer.pattern.strategy;

public class PromotionActivity {

    private PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void executePromotionStrategr() {
        this.promotionStrategy.doPromotion();
    }

}
