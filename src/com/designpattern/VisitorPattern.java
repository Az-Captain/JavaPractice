package com.designpattern;

import java.util.Scanner;

/**
 * 访问者模式
 *
 * 小明家有一些圆形和长方形面积的土地，请你帮他实现一个访问者模式，使得可以通过访问者计算每块土地的面积。
 * 图形的面积计算规则如下：
 * 圆形的面积计算公式为：3.14 * 半径 * 半径
 * 矩形的面积计算公式为：长 * 宽
 *
 * @author Az
 * @date 2025/4/27
 */
public class VisitorPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        ShapeV[] shapes = new ShapeV[n];

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            if ("Circle".equals(input[0])) {
                int radius = Integer.parseInt(input[1]);
                shapes[i] = new CircleV(radius);
            } else if ("Rectangle".equals(input[0])) {
                int width = Integer.parseInt(input[1]);
                int length = Integer.parseInt(input[2]);
                shapes[i] = new RectangleV(width, length);
            } else {
                System.out.println("Invalid input");
                return;
            }
        }
        Drawing drawing = new Drawing(shapes);
        Visitor areaCalculator = new AreaCalculator();
        drawing.accept(areaCalculator);
    }
}

interface ShapeV {
    void accept(Visitor visitor);
}

class CircleV implements ShapeV {
    private final int radius;

    public CircleV(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class RectangleV implements ShapeV {
    private final int width;
    private final int length;

    public RectangleV(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

interface Visitor {
    void visit(CircleV circle);

    void visit(RectangleV rectagle);
}

class AreaCalculator implements Visitor {

    @Override
    public void visit(CircleV circle) {
        double area = 3.14 * circle.getRadius() * circle.getRadius();
        System.out.println(area);
    }

    @Override
    public void visit(RectangleV rectagle) {
        double area = rectagle.getLength() * rectagle.getWidth();
        System.out.println(area);
    }
}

class Drawing {
    private final ShapeV[] shapes;

    public Drawing(ShapeV[] shapes) {
        this.shapes = shapes;
    }

    public void accept(Visitor visitor) {
        for (ShapeV shape : shapes) {
            shape.accept(visitor);
        }
    }
}
