package com.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 迭代器模式
 * 小明是一位老师，在进行班级点名时，希望有一个学生名单系统，请你实现迭代器模式提供一个迭代器使得可以按顺序遍历学生列表。
 * <p>
 * 第一行是一个整数 N （1 <= N <= 100), 表示学生的数量。
 * 接下来的 N 行，每行包含一个学生的信息，格式为 姓名 学号
 *
 * @author Az
 * @date 2025/2/5
 */
public class IteratorPatternV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取学生数量
        int n = scanner.nextInt();
        scanner.nextLine(); // 读取换行符
        // 创建具体可迭代对象
        ConcreteStudentCollection studentCollection = new ConcreteStudentCollection();

        // 读取学生信息并添加到集合
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            if (input.length == 2) {
                String name = input[0];
                String studentId = input[1];
                StudentOfMing student = new StudentOfMing(name, studentId);
                studentCollection.addStudent(student);
            } else {
                System.out.println("Invalid input");
                return;
            }
        }

        // 使用迭代器遍历学生集合
        IteratorIntf<StudentOfMing> iterator = studentCollection.iterator();
        while (iterator.hasNext()) {
            StudentOfMing student = iterator.next();
            System.out.println(student.getName() + " " + student.getStudentId());
        }
    }
}

// 迭代器接口
interface IteratorIntf<T> {
    boolean hasNext();

    T next();
}

// 具体迭代器
class ConcreteStudentIterator implements IteratorIntf<StudentOfMing> {
    private List<StudentOfMing> students;
    private int currentIndex = 0;

    public ConcreteStudentIterator(List<StudentOfMing> students) {
        this.students = students;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < students.size();
    }

    @Override
    public StudentOfMing next() {
        if (hasNext()) {
            return students.get(currentIndex++);
        }
        return null;
    }
}

// 可迭代对象接口
interface StudentCollection {
    IteratorIntf<StudentOfMing> iterator();
}

// 具体可迭代对象
class ConcreteStudentCollection implements StudentCollection {
    private List<StudentOfMing> students = new ArrayList<>();

    public void addStudent(StudentOfMing student) {
        students.add(student);
    }

    @Override
    public IteratorIntf<StudentOfMing> iterator() {
        return new ConcreteStudentIterator(students);
    }
}

// 学生类
class StudentOfMing {
    private String name;
    private String studentId;

    public StudentOfMing(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }
}