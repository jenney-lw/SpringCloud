package com.lw.springcloud.eurekaconsumer.pattern.strategy;

import java.util.HashMap;
import java.util.Map;

public class PromotionStrategyFactory {

    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

    private final static PromotionStrategy emptyPromotionStrategy = new EmptyPromotionStrategy();

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.MANJIAN, new ManJianPromotionStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.LIJIAN, new LiJianPromotionStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.FANXIAN, new FanXianPromotionStrategy());
    }

    private PromotionStrategyFactory() {

    }

    public static PromotionStrategy getPromotionStrategy(String promotionKey) {
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return promotionStrategy == null ? emptyPromotionStrategy : promotionStrategy;
    }


    public interface PromotionStrategyKey {

        String LIJIAN = "LIJIAN";
        String MANJIAN = "MANJIAN";
        String FANXIAN = "FANXIAN";

    }

}
