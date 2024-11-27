package com.designpattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 原型模式
 *
 * @author Az
 * @date 2024/11/26
 */
public class PrototypePattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ShapeCache shapeCache = new ShapeCache();
//        shapeCache.registerShape("default", new Rectangle("red", 10, 5));


        String color = scanner.next();
        int width= scanner.nextInt();
        int height= scanner.nextInt();
        scanner.nextLine();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            shapeCache.registerShape("test",new Rectangle(color,width,height));
            Shape test = shapeCache.getShape("test");
            if(test != null){
                System.out.println("color:"+test.getColor()+", width:"+test.getWidth()+", height:"+test.getHeight());
            }
        }

    }
}

abstract class Shape {
    private String color;
    private int width;
    private int height;

    public Shape() {
    }

    public Shape(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    @Override
    public abstract Shape clone();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

class Rectangle extends Shape {
    public Rectangle(String color, int width, int height) {
        super(color, width, height);
    }

    @Override
    public Shape clone() {
        return new Rectangle(this.getColor(), this.getWidth(), this.getHeight());
    }
}

class ShapeCache {
    private final Map<String, Shape> shapeMap = new HashMap<>();

    public void registerShape(String shapeId, Shape shape) {
        shapeMap.put(shapeId, shape);
    }

    public Shape getShape(String shapeId) {
        Shape shape = shapeMap.get(shapeId);
        return shape != null ? shape.clone() : null;
    }
}


