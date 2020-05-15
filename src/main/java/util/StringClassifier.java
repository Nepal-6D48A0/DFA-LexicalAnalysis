package util;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class StringClassifier {

    private static final URL RESOURCE = StringClassifier.class.getResource("state-relation.csv");

    /**
     * This method takes the input string and gets individual token from it.
     * It classifies each token by moving the characters in the state machine.
     * It stores the end state to a list for each token and returns the list to the user.
     *
     * @param inputString String to be parsed
     * @return List of Tokens based on the input string
     * @throws Exception Error if failed to find or parse the relation mapping data
     */
    public static List<Token> classifyForTokens(String inputString) throws Exception {
        List<Token> classifiedTokens = new LinkedList<>();
        List<String> tokens = TokenGenerator.getTokens(inputString);

        DFA dfa;
        try {
            dfa = new DFA(RESOURCE.getFile());
        } catch (Exception e) {
            throw new Exception("Failed to load the state transition data");
        }

        for (String token : tokens) {
            // Case insensitive so change everything to uppercase
            String formattedToken = token.toUpperCase();
            State currentState = new State(0);

            List<State> visitedStates = new LinkedList<>();
            visitedStates.add(currentState);

            for (int i = 0; i < formattedToken.length(); i++) {
                currentState = dfa.getNextState(currentState, formattedToken.charAt(i) + "");
                visitedStates.add(currentState);
                if (currentState.equals(State.getInvalidState())) {
                    break;
                }
            }

            Token parsedToken = new Token(token, currentState.getStateClassification(), visitedStates);
            classifiedTokens.add(parsedToken);
        }

        return classifiedTokens;

    }
}
