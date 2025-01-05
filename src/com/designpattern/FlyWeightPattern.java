package com.designpattern;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

/**
 * 享元模式
 * <p>
 * 在一个图形编辑器中，用户可以绘制不同类型的图形，包括圆形（CIRCLE）、矩形（RECTANGLE）、三角形（TRIANGLE）等。现在，请你实现一个图形绘制程序，
 * 要求能够共享相同类型的图形对象，以减少内存占用。
 *
 * @author Az
 * @date 2025/1/5
 */
public class FlyWeightPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GraphicFactory graphicFactory = new GraphicFactory();
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            String type = split[0];
            int x = Integer.parseInt(split[1]);
            int y = Integer.parseInt(split[2]);

            if ("CIRCLE".equals(type)) {
                graphicFactory.getCircle(type, x, y);
            }
            if ("RECTANGLE".equals(type)) {
                graphicFactory.getRectangle(type, x, y);
            }
            if ("TRIANGLE".equals(type)) {
                graphicFactory.getTriangle(type, x, y);
            }
        }
        System.out.println();
    }

}

interface Graphic {
    /**
     * 绘制图形
     */
    void draw();
}

class Circle12 implements Graphic {
    private int x;
    private int y;

    public Circle12(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println("CIRCLE share at (" + x + ", " + y + ")");
    }
}

class Rectangle12 implements Graphic {
    private int x;
    private int y;

    public Rectangle12(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println("RECTANGLE share at (" + x + ", " + y + ")");
    }
}

class Triangle12 implements Graphic {
    private int x;
    private int y;

    public Triangle12(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println("TRIANGLE share at (" + x + ", " + y + ")");
    }
}

class GraphicFactory {
    public static HashMap<String, Graphic> graphicCache = new HashMap<>();


    public void getCircle(String type, int x, int y) {
        Circle12 circle12 = (Circle12) graphicCache.get(type);
        if (circle12 == null) {
            graphicCache.put("CIRCLE", new Circle12(x, y));
            System.out.println("CIRCLE drawn at (" + x + ", " + y + ")");
        } else {
            circle12.draw();
        }
    }

    public void getRectangle(String type, int x, int y) {
        Rectangle12 rectangle12 = (Rectangle12) graphicCache.get(type);
        if (rectangle12 == null) {
            graphicCache.put("RECTANGLE", new Rectangle12(x, y));
            System.out.println("RECTANGLE drawn at (" + x + ", " + y + ")");
        } else {
            rectangle12.draw();
        }
    }

    public void getTriangle(String type, int x, int y) {
        Triangle12 triangle = (Triangle12) graphicCache.get(type);
        if (triangle == null) {
            graphicCache.put("TRIANGLE", new Triangle12(x, y));
            System.out.println("TRIANGLE drawn at (" + x + ", " + y + ")");
        } else {
            triangle.draw();
        }
    }

}




