package com.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 外观模式
 * <p>
 * 接下来的 N 行，每行包含一个数字，表示对应设备的开关操作（1表示关闭空调，2表示关闭台灯，3表示关闭电视机，4表示关闭所有设备）。
 *
 * @author Az
 * @date 2024/12/24
 */
public class FacadePatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> orders = new ArrayList<>(n);
        Facade facade = new Facade();
        for (int i = 0; i < n; i++) {
            orders.add(scanner.nextInt());
        }

        for (Integer order : orders) {
            switch (order) {
                case 1 -> facade.turnOffAirConditioner();
                case 2 -> facade.turnOffDeskLamp();
                case 3 -> facade.turnOffTelevision();
                case 4 -> System.out.println("All devices are off");
                default -> System.out.println("Invalid device code");
            }
        }

    }
}

interface Device {
    /**
     * 关闭
     */
    void turnOff();
}

class AirConditioner implements Device {

    @Override
    public void turnOff() {
        System.out.println("Air Conditioner is turned off.");
    }
}

class DeskLamp implements Device {

    @Override
    public void turnOff() {
        System.out.println("Desk Lamp is turned off.");
    }
}

class Television implements Device {

    @Override
    public void turnOff() {
        System.out.println("Television is turned off.");
    }
}

class Facade {
    private final AirConditioner airConditioner;
    private final DeskLamp deskLamp;
    private final Television television;

    public Facade() {
        airConditioner = new AirConditioner();
        deskLamp = new DeskLamp();
        television = new Television();
    }

    public void turnOffAirConditioner() {
        airConditioner.turnOff();
    }

    public void turnOffDeskLamp() {
        deskLamp.turnOff();
    }

    public void turnOffTelevision() {
        television.turnOff();
    }
}
