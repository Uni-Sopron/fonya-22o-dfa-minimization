package hu.wtomee;


import com.fasterxml.jackson.databind.ObjectMapper;
import hu.wtomee.objects.DFA;

import java.io.*;
import java.nio.file.Paths;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static DFA dfa;

    public static void main(String[] args) {

        String dfaPath = readDfaJsonPath();

        getDfaFromJsonFile(dfaPath);

        checkDfa();

        String inputString = readInputString();

        runDfaWithInput(inputString);
        dfa.getAccessiblePart();

    }



    private static void runDfaWithInput(String inputString) {
        CharacterIterator it = new StringCharacterIterator(inputString);
        dfa.resetState();
        while(it.current() != CharacterIterator.DONE) {
            if(dfa.transitionWithSymbol(String.valueOf(it.current())).isAccepting()) {
                System.out.println("ACCEPTED");
            };
            it.next();
        }
    }

    private static void checkDfa() {
        dfa.checkInitState().checkFinalStates().checkTransitions();
    }

    private static String readInputString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input string");

        return scanner.nextLine();
    }

    private static String readDfaJsonPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter absolute path of DFA.json");

        return scanner.nextLine();
    }

    private static void getDfaFromJsonFile(String dfaPath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            dfa = mapper.readValue(Paths.get(dfaPath).toFile(), DFA.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(dfa);
    }

}
