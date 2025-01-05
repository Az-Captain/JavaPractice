package com.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式简易实现
 * @author Az
 * @date 2025/1/1
 */
public class CompositeDemo {
    public static void main(String[] args) {
        // 创建叶子节点
        Leaf leaf = new Leaf();
        // 创建组合节点，并添加叶子节点
        Composite composite = new Composite();
        composite.add(leaf);

        composite.operation(); // 统一调用
    }
}

/**
 * 组件接口
 */
interface Component2 {
    void operation();
}

/**
 * 叶子节点
 */
class Leaf implements Component2 {
    @Override
    public void operation() {
        System.out.println("Leaf operation");
    }
}

/**
 * 组合节点：包含叶子节点的操作行为
 */
class Composite implements Component2 {
    private List<Component2> components = new ArrayList<>();

    public void add(Component2 component) {
        components.add(component);
    }

    public void remove(Component2 component) {
        components.remove(component);
    }

    @Override
    public void operation() {
        System.out.println("Composite operation");
        for (Component2 component : components) {
            component.operation();
        }
    }
}

