package com.designpattern;

import java.util.*;

/**
 * <strong>桥接模式</strong>
 * <hr>
 * 小明家有一个万能遥控器，能够支持多个品牌的电视。每个电视可以执行开机、关机和切换频道的操作，请你使用桥接模式模拟这个操作。
 * <p>
 * 第一行是一个整数 N（1 <= N <= 100），表示后面有 N 行输入。
 * 接下来的 N 行，每行包含两个数字。第一个数字表示创建某个品牌的遥控和电视，第二个数字表示执行的操作。
 * 其中，0 表示创建 Sony 品牌的电视，1 表示创建 TCL 品牌的遥控和电视；
 * 2 表示开启电视、3表示关闭电视，4表示切换频道。
 *
 * @author Az
 * @date 2024/12/29
 */
public class BridgePattern {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int len = n * 2;
        Integer[] operations = new Integer[len];
        for (int i = 0; i < n * 2; i += 2) {
            String s = scanner.nextLine();
            String[] s1 = s.split(" ");
            operations[i] = Integer.parseInt(s1[0]);
            operations[i + 1] = Integer.parseInt(s1[1]);
        }
//        System.out.println(Arrays.toString(operations));
        for (int i = 0; i < len; i += 2) {
            int brand = operations[i];
            int op = operations[i + 1];

            Tv tv = null;
            if (brand == 0) {
                tv = new SonyTV();
            }
            if (brand == 1) {
                tv = new TCLTV();
            }

            UniversalRemoteControl control = null;
            if (op == 2) {
                control = new TurnOn(tv);
            }
            if (op == 3) {
                control = new TurnOff(tv);
            }
            if (op == 4) {
                control = new SwitchChannel(tv);
            }
            boolean exists = Optional.ofNullable(control).isPresent();
            if (exists) {
                control.operation();
            }

        }
    }
}

interface Tv {
    /**
     * 关闭
     */
    void turnOff();

    /**
     * 打开
     */
    void turnOn();

    /**
     * 切换频道
     */
    void switchChannel();
}

class SonyTV implements Tv {

    @Override
    public void turnOff() {
        System.out.println("Sony TV is OFF");
    }

    @Override
    public void turnOn() {
        System.out.println("Sony TV is ON");
    }

    @Override
    public void switchChannel() {
        System.out.println("Switching Sony TV channel");
    }
}

class TCLTV implements Tv {

    @Override
    public void turnOff() {
        System.out.println("TCL TV is OFF");
    }

    @Override
    public void turnOn() {
        System.out.println("TCL TV is ON");
    }

    @Override
    public void switchChannel() {
        System.out.println("Switching TCL TV channel");
    }


}

abstract class UniversalRemoteControl {
    protected Tv tv;

    protected UniversalRemoteControl(Tv tv) {
        this.tv = tv;
    }

    /**
     * 执行开启，关闭，切换频道操作
     */
    public abstract void operation();
}

class TurnOn extends UniversalRemoteControl {
    protected TurnOn(Tv tv) {
        super(tv);
    }

    @Override
    public void operation() {
        tv.turnOn();
    }
}

class TurnOff extends UniversalRemoteControl {
    protected TurnOff(Tv tv) {
        super(tv);
    }

    @Override
    public void operation() {
        tv.turnOff();
    }
}

class SwitchChannel extends UniversalRemoteControl {
    protected SwitchChannel(Tv tv) {
        super(tv);
    }

    @Override
    public void operation() {
        tv.switchChannel();
    }
}








