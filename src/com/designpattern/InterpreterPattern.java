package com.designpattern;

import java.util.Scanner;
import java.util.Stack;

/**
 * 解释器模式
 * 小明正在设计一个计算器，用于解释用户输入的简单数学表达式，每个表达式都是由整数、加法操作符+、乘法操作符组成的，表达式中的元素之间用空格分隔，请你使用解释器模式帮他实现这个系统。
 * 每行包含一个数学表达式，表达式中包含整数、加法操作符（+）和乘法操作符（*）。 表达式中的元素之间用空格分隔。
 *
 * @author Az
 * @date 2025/3/2
 */
public class InterpreterPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<AbstractExpression> values = new Stack<>();
        Stack<String> ops = new Stack<>();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            AbstractExpression apply = apply(input);
            System.out.println(apply.interpret());
        }
    }

    public static AbstractExpression apply(String input) {
        String[] s = input.split(" ");
        Stack<AbstractExpression> values = new Stack<>();
        Stack<String> ops = new Stack<>();
        for (String in : s) {
            if ("+".equals(in) || "*".equals(in)) {
                while (!ops.isEmpty() && process(ops.peek()) >= process(in)) {
                    processOperator(values, ops.pop());
                }
                ops.push(in);
            } else {
                values.push(new IntegerExpression(Integer.parseInt(in)));
            }
        }
        while (!ops.isEmpty()) {
            processOperator(values, ops.pop());
        }
        return values.pop();
    }

    public static void processOperator(Stack<AbstractExpression> values, String op) {
        AbstractExpression right = values.pop();
        AbstractExpression left = values.pop();
        values.push(
                "+".equals(op) ?
                        new AddExpression(left, right) :
                        new MultiplyExpression(left, right)
        );
    }

    public static int process(String op) {
        return "+".equals(op) ? 1 : 2;
    }
}

// AbstractExpression：抽象表达式
interface AbstractExpression {
    int interpret();
}

// Terminal Expression：终结符表达式
class IntegerExpression implements AbstractExpression {

    private int value;

    public IntegerExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return value;
    }
}

//Nonterminal Expression： 非终结符表达式
// 加法
class AddExpression implements AbstractExpression {

    private AbstractExpression left;
    private AbstractExpression right;

    public AddExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

// 乘法
class MultiplyExpression implements AbstractExpression {

    private AbstractExpression left;
    private AbstractExpression right;

    public MultiplyExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() * right.interpret();
    }
}
