package com.designpattern;

/**
 * 状态模式
 *
 * @author Az
 * @date 2025/2/6
 */
public class StatePattern {
    public static void main(String[] args) {
        StateContext context = new StateContext();

        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }
}

/**
 * 状态
 */
interface State {
    void doAction(StateContext context);
}

/**
 * 具体状态
 */
class StateContext {
    private State state;

    public StateContext() {
        state = null;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

class StartState implements State {

    @Override
    public void doAction(StateContext context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}

class StopState implements State {

    @Override
    public void doAction(StateContext context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}
