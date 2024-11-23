package com.designpattern;

import java.util.Scanner;

/**
 * 抽象工厂模式
 *
 * @author Az
 * @date 2024/11/21
 */
public class AbstractFactoryPatternV2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int loop = scanner.nextInt();
        for (int i = 0; i < loop; i++) {
            String furnitureType = scanner.next();
            FurnitureFactory factory = null;
            if ("modern".equals(furnitureType)) {
                factory = new ModernFurnitureFactory();
            }
            if ("classical".equals(furnitureType)) {
                factory = new ClassicalFurnitureFactory();
            }

            Chair chair = factory.createChair();
            Sofa sofa = factory.createSofa();

            chair.getChair();
            sofa.getSofa();
        }

    }
}

interface Sofa {
    /**
     * 生产
     */
    void getSofa();
}

class ModernSofa implements Sofa {

    @Override
    public void getSofa() {
        System.out.println("modern sofa");
    }
}

class ClassicalSofa implements Sofa {

    @Override
    public void getSofa() {
        System.out.println("classical sofa");

    }
}


interface Chair {
    /**
     * 生产
     */
    void getChair();
}


class ModernChair implements Chair {
    @Override
    public void getChair() {
        System.out.println("modern chair");
    }
}


class ClassicalChair implements Chair {

    @Override
    public void getChair() {
        System.out.println("classical chair");
    }
}

interface FurnitureFactory {

    Chair createChair();

    Sofa createSofa();
}


class ModernFurnitureFactory implements FurnitureFactory {

    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}

class ClassicalFurnitureFactory implements FurnitureFactory {

    @Override
    public Chair createChair() {
        return new ClassicalChair();
    }

    @Override
    public Sofa createSofa() {
        return new ClassicalSofa();
    }
}

