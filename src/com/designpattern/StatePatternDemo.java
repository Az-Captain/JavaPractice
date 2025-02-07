package com.designpattern;


import java.util.Scanner;

/**
 * 状态模式
 * <p>
 * 小明家有一个灯泡，刚开始为关闭状态（OffState）。台灯可以接收一系列的指令，包括打开（"ON"）、关闭（"OFF"）和闪烁（"blink"）。
 * 每次接收到一个指令后，台灯会执行相应的操作，并输出当前灯泡的状态。请设计一个程序模拟这个灯泡系统。
 *
 * @author Az
 * @date 2025/2/7
 */
public class StatePatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LampStateContext context = new LampStateContext();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String order = scanner.next();
            if ("ON".equals(order)) {
                context.setState(new OrderOn());
            }
            if ("OFF".equals(order)) {
                context.setState(new OrderOff());
            }
            if ("BLINK".equals(order)) {
                context.setState(new OrderBlink());
            }
            context.getState().execute(context);
        }
    }
}

interface LampState {
    /**
     * 执行命令
     */
    void execute(LampStateContext context);
}

/**
 * 上下文
 */
class LampStateContext {
    private LampState state;

    public LampStateContext() {
        state = new OrderOff();
    }

    public LampState getState() {
        return state;
    }

    public void setState(LampState state) {
        this.state = state;
    }
}

class OrderOn implements LampState {

    @Override
    public void execute(LampStateContext context) {
        System.out.println("Light is ON");
        context.setState(this);
    }
}

class OrderOff implements LampState {

    @Override
    public void execute(LampStateContext context) {
        System.out.println("Light is OFF");
        context.setState(this);
    }
}

class OrderBlink implements LampState {

    @Override
    public void execute(LampStateContext context) {
        System.out.println("Light is Blinking");
        context.setState(this);
    }
}