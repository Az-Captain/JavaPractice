package com.designpattern;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 享元模式
 *
 * @author Az
 * @date 2025/1/17
 */
public class FlyWeightPatternV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShapeDrawFactory shapeDrawFactory = new ShapeDrawFactory();

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] split = command.split(" ");
            String type = split[0];
            ShapeType shapeType = ShapeType.valueOf(type);
            ShapeDraw shape = shapeDrawFactory.getShape(shapeType);
            Position position = new Position(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            shape.draw(position);
            ((ConcreteShape) shape).setFirstTime(false);
        }
    }
}

enum ShapeType {
    /* 圆 */
    CIRCLE,
    /* 矩形 */
    RECTANGLE,
    /* 三角形 */
    TRIANGLE;
}

class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

interface ShapeDraw {
    void draw(Position position);
}

class ConcreteShape implements ShapeDraw {
    private ShapeType shapeType;

    public ConcreteShape(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public void draw(Position position) {
        System.out.println(shapeType + (isFirstTime ? " drawn" : " shared") + " at (" + position.getX() + ", " + position.getY() + ")");

    }

    private boolean isFirstTime = true;

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }
}

class ShapeDrawFactory {
    private HashMap<ShapeType, ShapeDraw> shapes = new HashMap<>();

    //    public ShapeDraw getShape(ShapeType type) {
//        if (!shapes.containsKey(type)) {
//            shapes.put(type, new ConcreteShape(type));
//        }
//        return shapes.get(type);
//    }
    public ShapeDraw getShape(ShapeType type) {
        if (shapes.containsKey(type)) {
            return shapes.get(type);
        }

        ConcreteShape shape = new ConcreteShape(type);
        shapes.put(type, shape);
        return shape;
    }
}