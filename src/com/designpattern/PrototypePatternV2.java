package com.designpattern;

import java.util.Scanner;

/**
 * 原型模式2
 *
 * @author Az
 * @date 2024/12/01
 */
public class PrototypePatternV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String color = scanner.next();
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            RectanglePrototype prototype = new RectanglePrototype(color, width, height);
            Prototype clone = prototype.clone();
            System.out.println(clone.getDetails());
        }

    }
}

abstract class Prototype implements Cloneable {
    @Override
    public abstract Prototype clone();

    public abstract String getDetails();

    public Prototype clonePrototype() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class RectanglePrototype extends Prototype {

    private String color;
    private int width;
    private int height;

    public RectanglePrototype(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    @Override
    public Prototype clone() {
        return clonePrototype();
    }

    @Override
    public String getDetails() {
        return "Color: " + color + ", Width: " + width + ", Height: " + height;
    }
}





