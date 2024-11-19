package com.designpattern;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 小明去了一家大型商场，拿到了一个购物车，并开始购物。
 * 请你设计一个购物车管理器，记录商品添加到购物车的信息（商品名称和购买数量），
 * 并在购买结束后打印出商品清单。（在整个购物过程中，小明只有一个购物车实例存在）
 *
 * @author Az
 * @date 2024/11/19
 */
public class Singleton {
    public static void main(String[] args) {
//        ShoppingCartManagerLazy cart = ShoppingCartManagerLazy.getInstance();
        ShoppingCartManager cart = ShoppingCartManager.getInstance();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String merchandise = scanner.next();
            int quantity = scanner.nextInt();
            cart.addToCart(merchandise, quantity);
        }
        cart.getMerchandise();
    }
}

/**
 * 饿汉模式
 */
class ShoppingCartManager {
    private static ShoppingCartManager instance = new ShoppingCartManager();
    private final HashMap<String, Integer> cartMap;

    private ShoppingCartManager() {
        cartMap = new LinkedHashMap<>();
    }

    public static ShoppingCartManager getInstance() {
        return instance;
    }

    public void addToCart(String merchandise, Integer quantity) {
        cartMap.put(merchandise, cartMap.getOrDefault(merchandise, 0) + quantity);
    }

    public void getMerchandise() {
        for (Map.Entry<String, Integer> entry : cartMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

/**
 * 懒汉模式
 */
class ShoppingCartManagerLazy {
    private static ShoppingCartManagerLazy instance;
    private final HashMap<String, Integer> cartMap;

    private ShoppingCartManagerLazy() {
        cartMap = new LinkedHashMap<>();
    }

    public static ShoppingCartManagerLazy getInstance() {
        if (instance == null) {
            instance = new ShoppingCartManagerLazy();
        }
        return instance;
    }

    public void addToCart(String merchandise, Integer quantity) {
        cartMap.put(merchandise, quantity);
    }

    public void getMerchandise() {
        for (Map.Entry<String, Integer> entry : cartMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}