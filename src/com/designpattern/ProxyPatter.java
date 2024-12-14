package com.designpattern;

import java.util.Scanner;

/**
 * 代理模式
 *
 * 小明想要购买一套房子，他决定寻求一家房屋中介来帮助他找到一个面积超过100平方米的房子，只有符合条件的房子才会被传递给小明查看。
 * @author Az
 * @date 2024/12/14
 */
public class ProxyPatter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Proxy proxy = new Proxy();

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int area = scanner.nextInt();
            proxy.getArea(area);
        }
    }
}

interface HouseSale {
    /**
     * 获取房屋面积
     */
    void getArea(int area);
}

class HouseOwner implements HouseSale {

    @Override
    public void getArea(int area) {
        System.out.println("YES");
    }
}

class Proxy implements HouseSale {
    private HouseOwner houseOwner;

    @Override
    public void getArea(int area) {
        if (houseOwner == null) {
            houseOwner = new HouseOwner();
        }
        if (area >= 100) {
            houseOwner.getArea(area);
        } else {
            System.out.println("NO");
        }
    }
}