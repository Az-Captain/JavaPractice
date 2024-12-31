package com.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 组合模式
 * <hr>
 * 小明所在的公司内部有多个部门，每个部门下可能有不同的子部门或者员工。
 * 请你设计一个组合模式来管理这些部门和员工，实现对公司组织结构的统一操作。部门和员工都具有一个通用的接口，可以获取他们的名称以及展示公司组织结构。
 * <p>
 * 第一行是一个整数 N（1 <= N <= 100），表示后面有 N 行输入。
 * 接下来的 N 行，每行描述一个部门或员工的信息。部门的信息格式为 D 部门名称，员工的信息格式为 E 员工名称，其中 D 或 E 表示部门或员工。
 *
 * @author Az
 * @date 2024/12/31
 */
public class CompositePatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入公司名：");
        String companyName = scanner.nextLine();
        Company company = new Company(companyName);

        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.nextLine().trim();

            if("D".equals(type)){
                Department department = new Department(name);
                company.add(department);
            }else if("E".equals(type)){
                Employee employee = new Employee(name);
                company.add(employee);
            }
        }
        company.display();
    }
}

interface Component {
    void getInfo(int depth);
}



class Department implements Component {
    private String deptName;
    private List<Component> children;

    public Department(String deptName) {
        this.deptName = deptName;
        this.children = new ArrayList<>();
    }

    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void getInfo(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append(" ");
        }
        System.out.println(indent + deptName);
        for (Component child : children) {
            child.getInfo(depth + 1);
        }
    }
}

class Employee implements Component {
    private final String epmName;

    public Employee(String epmName) {
        this.epmName = epmName;
    }

    @Override
    public void getInfo(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append(" ");
        }
        System.out.println(indent + " "+ epmName);
    }
}

class Company {
    private String name;
    private Department root;

    public Company(String name) {
        this.name = name;
        this.root = new Department(name);
    }

    public void add(Component component) {
        root.add(component);
    }

    public void display() {
        System.out.println("Company Structure:");
        root.getInfo(0);
    }

}

//class Composite implements Component {
//    private final List<Component> components = new ArrayList<>();
//
//    public void add(Component component) {
//        components.add(component);
//    }
//
//    public void remove(Component component) {
//        components.remove(component);
//    }
//
//    @Override
//    public void getInfo() {
//        for (Component component : components) {
//            component.getInfo();
//        }
//    }
//}

