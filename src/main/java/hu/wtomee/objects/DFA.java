package hu.wtomee.objects;

import java.security.InvalidParameterException;
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

        }

        System.out.println("OK! Transitions checked! ");
    }

    public DFA resetState() {
        currentState = initialState;
        return this;
    }

    public DFA input(String input) {
        if (!alphabet.contains(input))
            throw new InvalidParameterException("\"" + input + "\" is not valid");


        if(transitions.stream().noneMatch(transition -> transition.getSymbol().equals(input) && transition.getFromState().equals(currentState)))
            throw new InvalidParameterException("No transition found for input: " + input);

        currentState = transitions.stream()
                .filter(transition -> transition.getSymbol().equals(input) && transition.getFromState().equals(currentState))
                .findFirst()
                .get().nextState;

        return this;
    }

    public boolean isAccepting() {
        return finalStates.contains(currentState);
    }

    public DFA checkStates() {

        // Check if States contains all finalStates
        boolean containsAllFinalStates = states.containsAll(finalStates);
        if (containsAllFinalStates){
            System.out.println("OK! States containing all final states");
        } else {
            throw new InvalidParameterException("States not containing all final states");
        }

        // Check if States contains the initialState
        boolean containsInitialState = states.contains(initialState);
        if (containsInitialState) {
            System.out.println("OK! States containing the initial state");
        } else {
            throw new InvalidParameterException("States not containing the initial state");
        }

        /*
            Check if States contains all the Transitions from and to states.
            Also check if Alphabet contains all Symbols from Transitions.
         */
        boolean containsTransitionStatesAndSymbols = transitions.stream().anyMatch(transition -> !states.containsAll(List.of(transition.getFromState(), transition.getNextState())) || !alphabet.contains(transition.getSymbol()));
        if (!containsTransitionStatesAndSymbols) {
            System.out.println("OK! States containing all transition states and Alphabet contains symbols");
        } else {
            throw new InvalidParameterException("States not containing all the transition states and Alphabet not containning symbols");
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
