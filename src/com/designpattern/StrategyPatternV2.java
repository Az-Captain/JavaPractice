package com.designpattern;

import java.util.Scanner;

/**
 * 策略模式2
 * @author Az
 * @date 2025/1/25
 */
public class StrategyPatternV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取需要计算优惠的次数
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            // 读取商品价格和优惠策略
            String[] input = scanner.nextLine().split(" ");
            int price = Integer.parseInt(input[0]);
            int strategyType = Integer.parseInt(input[1]);

            // 根据优惠策略设置相应的打折策略
            Strategy discountStrategy;
            switch (strategyType) {
                case 1:
                    discountStrategy = new DiscountStrategy1();
                    break;
                case 2:
                    discountStrategy = new DiscountStrategy2();
                    break;
                default:
                    // 处理未知策略类型
                    System.out.println("Unknown strategy type");
                    return;
            }

            // 设置打折策略
            DiscountContext context = new DiscountContext(discountStrategy);
            // 应用打折策略并输出优惠后的价格
            int discountedPrice = context.applyDiscount(price);
            System.out.println(discountedPrice);
        }
    }
}

/**
 * 抽象购物优惠策略接口
 */
interface Strategy {
    /**
     * 申请优惠并返回优惠后价格
     *
     * @param originalPrice 优惠前总价
     * @return 优惠后的价格
     */
    int applyDiscount(int originalPrice);
}

class DiscountStrategy1 implements Strategy {

    @Override
    public int applyDiscount(int originalPrice) {
        return (int) Math.round(originalPrice * 0.9);
    }
}

class DiscountStrategy2 implements Strategy {
    private final int[] thresholds = {100, 150, 200, 300};
    private final int[] discounts = {5, 15, 25, 40};

    @Override
    public int applyDiscount(int originalPrice) {
        for (int i = thresholds.length - 1; i > 0; i--) {
            if (originalPrice >= thresholds[i]) {
                return originalPrice - discounts[i];
            }
        }
        return originalPrice;
    }
}

class DiscountContext {
    private Strategy strategy;

    public DiscountContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public int applyDiscount(int price) {
        return strategy.applyDiscount(price);
    }
}
