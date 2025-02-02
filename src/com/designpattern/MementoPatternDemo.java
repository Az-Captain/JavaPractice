package com.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 * 来自菜鸟网站例子
 *
 * @author Az
 * @date 2025/2/2
 */
public class MementoPatternDemo {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());

    }
}

class MementoDemo {
    private String state;

    public MementoDemo(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public MementoDemo saveStateToMemento() {
        return new MementoDemo(state);
    }

    public void getStateFromMemento(MementoDemo memento) {
        state = memento.getState();
    }
}

class CareTaker {
    private List<MementoDemo> mementoList = new ArrayList<MementoDemo>();

    public void add(MementoDemo state) {
        mementoList.add(state);
    }

    public MementoDemo get(int index) {
        return mementoList.get(index);
    }
}