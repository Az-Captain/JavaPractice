package com.designpattern;

import java.util.Scanner;

/**
 * 建造者模式
 *
 * 小明家新开了一家自行车工厂，用于使用自行车配件（车架 frame 和车轮 tires ）进行组装定制不同的自行车，包括山地车和公路车。
 * 山地车使用的是Aluminum Frame（铝制车架）和 Knobby Tires（可抓地轮胎），公路车使用的是 Carbon Frame （碳车架）和 Slim Tries。
 * 现在它收到了一笔订单，要求定制一批自行车，请你使用【建造者模式】告诉小明这笔订单需要使用那些自行车配置吧。
 * @author Az
 * @date 2024/11/25
 */
public class BuilderModePattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        BikeDirector bikeDirector = new BikeDirector();

        for (int i = 0; i < n; i++) {
            String type = scanner.nextLine();
            BikeBuilder bikeBuilder = null;
            if ("mountain".equals(type)) {
                bikeBuilder = new MountainBikeBuilder();
            }
            if ("road".equals(type)) {
                bikeBuilder = new RoadBikeBuilder();
            }

            Bike bike = null;
            if (bikeBuilder != null) {
                bike = bikeDirector.construct(bikeBuilder);
            }
            System.out.println(bike);
        }
    }
}

class Bike {
    private String frame;
    private String tires;

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setTires(String tires) {
        this.tires = tires;
    }

    @Override
    public String toString() {
        return frame + " " + tires;
    }
}

interface BikeBuilder {
    void buildFrame();

    void buildTires();

    Bike getBike();
}

class MountainBikeBuilder implements BikeBuilder {
    private Bike bike;

    public MountainBikeBuilder() {
        this.bike = new Bike();
    }

    @Override
    public void buildFrame() {
        bike.setFrame("Aluminum Frame");
    }

    @Override
    public void buildTires() {
        bike.setTires("Knobby Tires");
    }

    @Override
    public Bike getBike() {
        return bike;
    }
}

class RoadBikeBuilder implements BikeBuilder {
    private Bike bike;

    public RoadBikeBuilder() {
        this.bike = new Bike();
    }

    @Override
    public void buildFrame() {
        bike.setFrame("Carbon Frame");
    }

    @Override
    public void buildTires() {
        bike.setTires("Slim Tires");
    }

    @Override
    public Bike getBike() {
        return bike;
    }
}

class BikeDirector {
    public Bike construct(BikeBuilder bikeBuilder) {
        bikeBuilder.buildFrame();
        bikeBuilder.buildTires();
        return bikeBuilder.getBike();
    }
}

