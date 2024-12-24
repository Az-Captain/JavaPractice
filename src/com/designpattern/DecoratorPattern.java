package com.designpattern;

import java.util.Scanner;

/**
 * 装饰器模式
 * <p>
 * 请设计一个简单的咖啡制作系统，使用装饰者模式为咖啡添加不同的调料。系统支持两种咖啡类型：黑咖啡（Black Coffee）和拿铁（Latte）。
 * 多行输入，每行包含两个数字。第一个数字表示咖啡的选择（1 表示黑咖啡，2 表示拿铁），第二个数字表示要添加的调料类型（1 表示牛奶，2 表示糖）。
 *
 * @author Az
 * @date 2024/12/22
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Coffee coffee;
        while (scanner.hasNext()) {
            int coffeeType = scanner.nextInt();
            int condimentType = scanner.nextInt();

            // 咖啡类型
            if (coffeeType == 1) {
                coffee = new BlackCoffee();

            } else if (coffeeType == 2) {
                coffee = new Latte();
            } else {
                System.out.println("invalid Type");
                continue;
            }

//            if (condimentType == 1) {
//                MilkDecorator milkDecorator = new MilkDecorator(coffee);
//                milkDecorator.make();
//            } else if (condimentType == 2) {
//                SugarDecorator sugarDecorator = new SugarDecorator(coffee);
//                sugarDecorator.make();
//            } else {
//                System.out.println("invalid condimentType");
//                continue;
//            }

            // 添加的调料类型
            if (condimentType == 1) {
                coffee = new MilkDecorator(coffee);
            } else if (condimentType == 2) {
                coffee = new SugarDecorator(coffee);
            } else {
                System.out.println("invalid condimentType");
                continue;
            }

            coffee.make();
        }
    }
}

interface Coffee {
    /**
     * 制作咖啡
     */
    void make();
}

class BlackCoffee implements Coffee {

    @Override
    public void make() {
        System.out.println("Brewing Black Coffee");
    }
}

class Latte implements Coffee {
    @Override
    public void make() {
        System.out.println("Brewing Latte");
    }
}

class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void make() {
        coffee.make();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void make() {
        super.make();
        System.out.println("Adding Milk");
    }
}

class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void make() {
        super.make();
        System.out.println("Adding Sugar");
    }
}

