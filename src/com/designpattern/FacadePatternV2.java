package com.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 外观模式
 *
 * @author Az
 * @date 2024/12/26
 */
public class FacadePatternV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // N（1 <= N <= 100）
        int n = scanner.nextInt();
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextByte());
        }

        FacadeSwitch facadeSwitch = new FacadeSwitch();
        for (Byte a : list) {
            facadeSwitch.superSwitch(a);
        }


    }
}


class AirConditioner2 {
    public void turnOff() {
        System.out.println("Air Conditioner is turned off.");
    }
}

class DeskLamp2 {
    public void turnOff() {
        System.out.println("DeskLamp is turned off.");
    }
}

class Television2 {
    public void turnOff() {
        System.out.println("Television is turned off.");
    }
}

class FacadeSwitch {
    private final AirConditioner2 airConditioner;
    private final DeskLamp2 deskLamp;
    private final Television2 television;

    public FacadeSwitch() {
        airConditioner = new AirConditioner2();
        deskLamp = new DeskLamp2();
        television = new Television2();
    }

    public void superSwitch(int n) {
        switch (n) {
            case 1 -> airConditioner.turnOff();
            case 2 -> deskLamp.turnOff();
            case 3 -> television.turnOff();
            case 4 -> System.out.println("All devices are off.");
            default -> {
                System.out.println("Invalid device code.");
            }
        }
    }
}