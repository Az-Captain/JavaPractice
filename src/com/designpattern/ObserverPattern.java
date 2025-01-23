package com.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 观察者模式例子
 * <p>
 * 小明所在的学校有一个时钟（主题），每到整点时，它就会通知所有的学生（观察者）当前的时间，请你使用观察者模式实现这个时钟通知系统。
 * <p>
 * 注意点：时间从 0 开始，并每隔一个小时更新一次。
 *
 * @author Az
 * @date 2025/1/23
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Clock clock = new Clock();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            Student student = new Student(name);
            clock.register(student);
        }

        int updateTimes = scanner.nextInt();
        for (int i = 0; i < updateTimes; i++) {
            clock.tick();
        }
    }
}

//主题
interface Subject {
    void register(Observer observer);

    void remote(Observer observer);

    void notifyObservers();
}

//观察者
interface Observer {
    void update(int hour);
}

//主题具体实现
class Clock implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int hour;

    @Override
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void remote(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(it -> it.update(hour));
    }

    public void tick() {
        hour = (hour + 1) % 24;
        notifyObservers();
    }
}

//观察者具体实现
class Student implements Observer {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void update(int hour) {
        System.out.println(name + " " + hour);
    }
}