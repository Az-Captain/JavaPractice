package com.designpattern;

import java.util.Scanner;

/**
 * 命令模式
 *
 * @author Az
 * @date 2025/1/26
 */
public class CommandPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DrinkMaker drinkMaker = new DrinkMaker();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String drink = scanner.next();
            // 具体命令
            Command command = new OrderCommand(drink, drinkMaker);
            // 执行命令
            OrderMachine orderMachine = new OrderMachine();
            orderMachine.setCommand(command);
            orderMachine.executeOrder();
        }
        scanner.close();
    }
}

/**
 * 命令接口
 */
interface Command {
    void execute();
}

/**
 * 具体命令类
 */
class OrderCommand implements Command {
    private String drinkName;
    private DrinkMaker drinkMaker;

    public OrderCommand(String drinkName, DrinkMaker drinkMaker) {
        this.drinkName = drinkName;
        this.drinkMaker = drinkMaker;
    }

    @Override
    public void execute() {
        drinkMaker.make(drinkName);
    }
}

/**
 * 接受者
 */
class DrinkMaker {
    public void make(String drinkName) {
        System.out.println(drinkName + " is ready!");
    }
}

/**
 * 调用者
 */
class OrderMachine {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeOrder() {
        command.execute();
    }
}