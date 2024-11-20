package com.designpattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 小明家有两个工厂，一个用于生产圆形积木，一个用于生产方形积木，请你帮他设计一个积木工厂系统，记录积木生产的信息。
 *
 * @author Az
 * @date 2024/11/20
 */
public class Factory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int loop = scanner.nextInt();
        ArrayList<HashMap<String, Integer>> list = new ArrayList<>();
        ShapeFactory factory = new ShapeFactory();

        while (loop > 0) {
            String type = scanner.next();
            int quality = scanner.nextInt();
            HashMap<String, Integer> map = new HashMap<>(1);
            map.put(type, quality);
            list.add(map);
            loop--;
        }

        list.forEach(it -> it.forEach((key, value) -> {
            BlockFactory shape = factory.getShape(key);
            for (int i = 0; i < value; i++) {
                shape.produce();
            }
        }));

    }
}

/**
 * 积木接口
 */
interface BlockFactory {
    /**
     * 生产积木
     */
    void produce();
}

/**
 * 圆积木
 */
class Circle implements BlockFactory {
    @Override
    public void produce() {
        System.out.println("Circle Block");
    }
}

/**
 * 方积木
 */
class Square implements BlockFactory {
    @Override
    public void produce() {
        System.out.println("Square Block");
    }
}

class ShapeFactory {
    public BlockFactory getShape(String type) {
        if ("circle".equalsIgnoreCase(type)) {
            return new Circle();
        }
        if ("square".equalsIgnoreCase(type)) {
            return new Square();
        }
        return null;
    }
}
