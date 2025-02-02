package com.designpattern;

import java.util.Scanner;
import java.util.Stack;

/**
 * 备忘录模式
 *
 * @author Az
 * @date 2025/2/2
 */
public class MementoPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Counter counter = new Counter();

        while (scanner.hasNext()) {
            String operation = scanner.next();
            switch (operation) {
                case "Increment" -> counter.increment();
                case "Decrement" -> counter.decrement();
                case "Undo" -> counter.undo();
                case "Redo" -> counter.redo();
                default -> System.out.println("invalid order");
            }
            System.out.println(counter.getValue());
        }
    }
}

/**
 * 备忘录
 */
class Memento {
    private int value;

    public Memento(int num) {
        this.value = num;
    }

    public int getValue() {
        return value;
    }
}

/**
 * 发起人
 */
class Counter {
    private int value;
    private final Stack<Memento> undoStack = new Stack<>();
    private final Stack<Memento> redoStack = new Stack<>();

    public void increment() {
        redoStack.clear();
        undoStack.push(new Memento(value));
        value++;
    }

    public void decrement() {
        redoStack.clear();
        undoStack.push(new Memento(value));
        value--;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(new Memento(value));
            value = undoStack.pop().getValue();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(new Memento(value));
            value = redoStack.pop().getValue();
        }
    }

    public int getValue() {
        return value;
    }

}
