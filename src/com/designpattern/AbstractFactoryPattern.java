package com.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 抽象工厂模式（需要改进）
 *
 * @author Az
 * @date 2024/11/21
 */
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        SofaFactory sofaFactory = new SofaFactory();
        Modern modernSofa = sofaFactory.getModern();
        Classical classicalSofa = sofaFactory.getClassical();

        ChairFactory chairFactory = new ChairFactory();
        Modern modernChair = chairFactory.getModern();
        Classical classicalChair = chairFactory.getClassical();

        Scanner scanner = new Scanner(System.in);
        int loop = scanner.nextInt();
        List<String> list = new ArrayList<>(loop);
        while (loop > 0) {
            String style = scanner.next();
            list.add(style);
            loop--;
        }

        for (String style : list) {
            if ("modern".equals(style)) {
                modernChair.getModern();
                modernSofa.getModern();
            }
            if ("classical".equals(style)) {
                classicalChair.getClassical();
                classicalSofa.getClassical();
            }
        }


    }
}

interface Modern {
    /**
     * 生产
     */
    void getModern();
}

interface Classical {
    /**
     * 生产
     */
    void getClassical();
}

class SofaModern implements Modern {

    @Override
    public void getModern() {
        System.out.println("modern sofa");
    }
}

class ChairModern implements Modern {

    @Override
    public void getModern() {
        System.out.println("modern chair");
    }
}

class SofaClassical implements Classical {
    @Override
    public void getClassical() {
        System.out.println("classical sofa");
    }
}

class ChairClassical implements Classical {
    @Override
    public void getClassical() {
        System.out.println("classical sofa");
    }
}

abstract class AbstractFactory {
    /**
     * 生产
     *
     * @return 现代
     */
    public abstract Modern getModern();

    /**
     * 生产
     *
     * @return 古典
     */
    public abstract Classical getClassical();
}

class SofaFactory extends AbstractFactory {

    @Override
    public Modern getModern() {
        return new SofaModern();
    }

    @Override
    public Classical getClassical() {
        return new SofaClassical();
    }
}

class ChairFactory extends AbstractFactory {

    @Override
    public Modern getModern() {
        return new ChairModern();
    }

    @Override
    public Classical getClassical() {
        return new ChairClassical();
    }
}

