package hu.wtomee.objects;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DFA {

    List<String> states;

    List<String> alphabet;

    List<Transition> transitions;

    String initialState;

    List<String> finalStates;

    String currentState;

    public DFA() {
    }

    public DFA(List<String> states, List<String> alphabet, List<Transition> transitions, String initialState, List<String> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(List<String> finalStates) {
        this.finalStates = finalStates;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
    public void checkTransitions() {
        for(Transition t : transitions) {
            if (!states.contains(t.nextState) || ! states.contains(t.fromState)) {
                throw new InvalidParameterException("State doesnt exists in transition: " +  t.toString());
            }
            if (!alphabet.contains(t.symbol)){
                throw new InvalidParameterException("Symbol doesnt exists in transition: " + t.toString());
            }

            // Check if deterministic
            int counter = 0;
            for (Transition t2 : transitions) {
                if (t.symbol.equals(t2.symbol) && t.fromState.equals(t2.fromState)) {
                    if (t.nextState.equals(t2.nextState) && counter < 2) {
                        counter++;
                    } else {
                        throw new InvalidParameterException("DFA is a not deterministic because of these transitions:" + t + "\n" + t2);
                    }
                }
            }
        }

        System.out.println("OK! Transitions checked! ");
    }

    public DFA resetState() {
        currentState = initialState;
        return this;
    }

    public void getAccessiblePart() {
        List<String> accessibleStates = new ArrayList<>();
        accessibleStates.add(initialState);
        currentState = initialState;
        for (int i = 0; i < transitions.size(); i++) {
            currentState = transitions.stream()
                    .filter(transition -> transition.getFromState().equals(currentState))
                    .findFirst()
                    .get().getNextState();
            if(!accessibleStates.contains(currentState))
                accessibleStates.add(currentState);
        }
        states = accessibleStates;

        System.out.println("Accessible states: " + states);

        transitions = transitions.stream()
                .filter(transition -> accessibleStates.contains(transition.getNextState())).toList();
        System.out.println("Transitions: ");
        for (Transition t : transitions) {
            System.out.println(t);
        }
    }
    public DFA transitionWithSymbol(String symbol) {
        if (!alphabet.contains(symbol))
            throw new InvalidParameterException("\"" + symbol + "\" is not valid");

        if(transitions.stream().noneMatch(transition -> transition.getSymbol().equals(symbol) && transition.getFromState().equals(currentState)))
            throw new InvalidParameterException("No transition found for symbol: " + symbol);

        currentState = transitions.stream()
                .filter(transition -> transition.getSymbol().equals(symbol) && transition.getFromState().equals(currentState))
                .findFirst()
                .get().nextState;

        return this;
    }

    public boolean isAccepting() {
        return finalStates.contains(currentState);
    }

    public DFA checkInitState() {

        // Check if States contains the initialState
        if (states.contains(initialState)) {
            System.out.println("OK! States containing the initial state");
        } else {
            throw new InvalidParameterException("States not containing the initial state");
        }

        return this;
    }

    public DFA checkFinalStates() {
        // Check if States contains all finalStates. States wrapped in HashSet for better performance
        if (new HashSet<>(states).containsAll(finalStates)){
            System.out.println("OK! States containing all final states");
        } else {
            throw new InvalidParameterException("States not containing all final states");
        }

        return this;
    }

    @Override
    public String toString() {
        return "DFA{" +
                "states=" + states +
                ", alphabet=" + alphabet +
                ", transitions=" + transitions +
                ", initialState='" + initialState + '\'' +
                ", finalStates=" + finalStates +
                '}';
    }
}
