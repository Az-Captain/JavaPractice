package com.designpattern;

import java.util.Scanner;

/**
 * 责任链模式
 * <hr>
 * 使用场景：
 * <li>当有多个对象可以处理请求，且具体由哪个对象处理由运行时决定时</li>
 * <li>当需要向多个对象中的一个提交请求，而不想明确指定接收者时</li>
 * <p>
 *     小明所在的公司请假需要在OA系统上发布申请，整个请求流程包括多个处理者，每个处理者负责处理不同范围的请假天数，如果一个处理者不能处理请求，就会将请求传递给下一个处理者，请你实现责任链模式，可以根据请求天数找到对应的处理者。
 *
 * 审批责任链由主管(Supervisor), 经理(Manager)和董事（Director)组成，他们分别能够处理3天、7天和10天的请假天数。如果超过10天，则进行否决。
 * </p>
 * @author Az
 * @date 2025/2/13
 */
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        // 组装责任链
        LeaveHandler director = new Director();
        LeaveHandler manager = new Manager(director);
        LeaveHandler supervisor = new Supervisor(manager);

        for (int i = 0; i < n; i++) {
            String info = scanner.nextLine();
            String[] split = info.split(" ");
            if (split.length == 2) {
                String name = split[0];
                int days = Integer.parseInt(split[1]);
                LeaveRequest leaveRequest = new LeaveRequest(name, days);
                supervisor.handleRequest(leaveRequest);
            } else {
                System.out.println("Invalid Input");
                return;
            }
        }
    }
}

interface LeaveHandler {
    void handleRequest(LeaveRequest request);
}

class LeaveRequest {
    private String name;
    private int days;

    public LeaveRequest(String name, int days) {
        this.name = name;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public int getDays() {
        return days;
    }

}

class Supervisor implements LeaveHandler {
    private static final int MAX_DAYS = 3;
    private final LeaveHandler nextHandler;

    public Supervisor(LeaveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getDays() <= MAX_DAYS) {
            System.out.println(request.getName() + " Approved by Supervisor.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println(request.getName() + " Denied by Supervisor.");
        }
    }
}

class Manager implements LeaveHandler {
    private static final int MAX_DAYS = 7;
    private final LeaveHandler nextHandler;

    public Manager(LeaveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getDays() <= MAX_DAYS) {
            System.out.println(request.getName() + " Approved by Manager.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println(request.getName() + " Denied by Manager.");
        }
    }
}

class Director implements LeaveHandler {
    private static final int MAX_DAYS = 10;

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getDays() <= MAX_DAYS) {
            System.out.println(request.getName() + " Approved by Director.");
        } else {
            System.out.println(request.getName() + " Denied by Director.");
        }
    }
}