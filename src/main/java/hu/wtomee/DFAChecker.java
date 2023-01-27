package hu.wtomee;


import com.fasterxml.jackson.databind.ObjectMapper;
import hu.wtomee.objects.DFA;
import hu.wtomee.objects.Transition;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class DFAChecker {
    static DFA dfa;

    public static void main(String[] args) {

        getDFAFromJsonFile();
        checkDFA(dfa);

    }

    private static void getDFAFromJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            dfa = mapper.readValue(Paths.get("C:\\Users\\WTomee\\school\\fonya-22o-dfa-minimization\\src\\main\\resources\\dfa.json").toFile(), DFA.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(dfa);
    }


    private static void checkDFA(DFA dfa) {
        HashSet<String> states =  new HashSet<>(dfa.getStates());
        HashSet<String> alphabet = new HashSet<>(dfa.getAlphabet());
        HashSet<Transition> transitions = new HashSet<>(dfa.getTransitions());

        // Check if States contains all finalStates
        boolean containsAllFinalStates = states.containsAll(dfa.getFinalStates());
        System.out.println("States contains all final states: " + containsAllFinalStates);

        // Check if States contains the initialState
        boolean containsInitialState = states.contains(dfa.getInitialState());
        System.out.println("States contains the initial state: " + containsInitialState);

        /*
            Check if States contains all the Transitions from and to states.
            Also check if Alphabet contains all Symbols from Transitions.
         */
        boolean containsTransitionStatesAndSymbols = transitions.stream().anyMatch(transition -> !states.containsAll(List.of(transition.getFromState(), transition.getNextState())) || !alphabet.contains(transition.getSymbol()));
        System.out.println("States contains all transition states and Alphabet contains symbols: " + !containsTransitionStatesAndSymbols);

    }
}
