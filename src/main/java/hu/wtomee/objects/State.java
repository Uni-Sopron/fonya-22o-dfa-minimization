package hu.wtomee.objects;

import java.util.List;

public class State {

    boolean isInit;

    boolean isFinal;

    List<Transition> transitions;

    String name;

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public State(boolean isInit, boolean isFinal, List<Transition> transitions) {
        this.isInit = isInit;
        this.isFinal = isFinal;
        this.transitions = transitions;
    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    @Override
    public String toString() {
        return "State{" +
                "isInit=" + isInit +
                ", isFinal=" + isFinal +
                ", transitions=" + transitions +
                '}';
    }
}
