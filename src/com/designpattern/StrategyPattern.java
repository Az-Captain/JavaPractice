package com.designpattern;

import java.util.Scanner;

/**
 * 策略模式
 * <p>
 * 小明家的超市推出了不同的购物优惠策略，你可以根据自己的需求选择不同的优惠方式。其中，有两种主要的优惠策略：
 * <p>
 * 1. 九折优惠策略：原价的90%。
 * <p>
 * 2. 满减优惠策略：购物满一定金额时，可以享受相应的减免优惠。
 * 具体的满减规则如下：
 * 满100元减5元
 * 满150元减15元
 * 满200元减25元
 * 满300元减40元
 * 请你设计一个购物优惠系统，用户输入商品的原价和选择的优惠策略编号，系统输出计算后的价格。
 *
 * @author Az
 * @date 2025/1/25
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        DiscountStrategy strategy = null;
        for (int i = 0; i < n; i++) {
            String info = scanner.nextLine();
            String[] split = info.split(" ");
            int price = Integer.parseInt(split[0]);
            if ("1".equals(split[1])) {
                strategy = new Discount();
                System.out.println(strategy.getRes(price));
            } else if ("2".equals(split[1])) {
                strategy = new FullReduction();
                System.out.println(strategy.getRes(price));
            }
        }
    }
}


interface DiscountStrategy {
    /**
     * 获取优惠后的价格
     *
     * @param price 原来总额
     * @return 优惠后的价格
     */
    int getRes(int price);
}

/**
 * 打折优惠
 */
class Discount implements DiscountStrategy {

    @Override
    public int getRes(int price) {
        return (int) (price * 0.9);
    }
}

/**
 * 满减优惠
 */
class FullReduction implements DiscountStrategy {

    @Override
    public int getRes(int price) {
        if (price >= 100 && price < 150) {
            return price - 5;
        }
        if (price >= 150 && price < 200) {
            return price - 15;
        }
        if (price >= 200 && price < 300) {
            return price - 25;
        }
        if (price >= 300) {
            return price - 40;
        }
        return price;
    }
}

class Context {
    private DiscountStrategy discountStrategy;

    public Context(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public int giveDiscount(int price) {
        return discountStrategy.getRes(price);
    }
}

