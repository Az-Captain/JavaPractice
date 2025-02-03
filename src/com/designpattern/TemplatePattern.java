package com.designpattern;

import java.util.Optional;
import java.util.Scanner;

/**
 * 模板模式
 *
 * @author Az
 * @date 2025/2/3
 */
public class TemplatePattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TempCoffee tempCoffee = null;
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if (n == 1) {
                tempCoffee = new AmericanCoffee();
            } else if (n == 2) {
                tempCoffee = new LatteCoffee();
            } else {
                System.out.println("Wrong Coffee Type");
                continue;
            }
            Optional.ofNullable(tempCoffee).ifPresent(TempCoffee::make);
        }

    }
}

abstract class TempCoffee {
    private String coffeeType;

    public TempCoffee(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    abstract void grinding();

    abstract void brewing();

    void condiments() {
        System.out.println("Adding condiments");
    }

    public final void make() {
        System.out.println("Making" + coffeeType + ":");
        grinding();
        brewing();
        condiments();
    }

}

class AmericanCoffee extends TempCoffee {

    public AmericanCoffee() {
        super("American Coffee");
    }

    @Override
    void grinding() {
        System.out.println("Grinding coffee beans");
    }

    @Override
    void brewing() {
        System.out.println("Brewing coffee");
    }

}

class LatteCoffee extends TempCoffee {

    public LatteCoffee() {
        super(" Latte");
    }

    @Override
    void grinding() {
        System.out.println("Grinding coffee beans");
    }

    @Override
    void brewing() {
        System.out.println("Brewing coffee");
    }

    @Override
    void condiments() {
        System.out.println("Adding milk");
        System.out.println("Adding condiments");
    }

}