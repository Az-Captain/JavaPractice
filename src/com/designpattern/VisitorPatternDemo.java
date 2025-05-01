package com.designpattern;

/**
 * 访问者模式
 *
 * @author Az
 * @date 2025/5/1
 */
public class VisitorPatternDemo {
    public static void main(String[] args) {
        ComputerVi computer = new ComputerVi();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}

interface ComputerPart {
    public void accept(ComputerPartVisitor visitor);
}

class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class ComputerVi implements ComputerPart {
    ComputerPart[] parts;

    public ComputerVi() {
        parts = new ComputerPart[]{new Mouse(), new Keyboard(), new Monitor()};
    }

    @Override
    public void accept(ComputerPartVisitor visitor) {
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(visitor);
        }
        visitor.visit(this);
    }
}

interface ComputerPartVisitor {
    public void visit(ComputerVi computer);

    public void visit(Mouse mouse);

    public void visit(Keyboard keyboard);

    public void visit(Monitor monitor);
}

class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(ComputerVi computer) {
        System.out.println("Displaying Computer.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying mouse.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying monitor.");
    }
}