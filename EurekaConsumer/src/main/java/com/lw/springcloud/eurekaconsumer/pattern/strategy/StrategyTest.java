package com.lw.springcloud.eurekaconsumer.pattern.strategy;

public class StrategyTest {

    public static void main(String[] args) {
        String promotionKey = "FANXIAN";
        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(promotionKey));
        promotionActivity.executePromotionStrategr();
    }

}
