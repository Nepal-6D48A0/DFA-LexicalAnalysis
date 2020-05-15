package util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * It is a description of a token
 * It stores the token, its type and the states the token visits in the state machine to be classified.
 */
public class Token {

    private String tokenText;
    private Classification tokenType;
    private List<State> visitedStates;

    public Token(String tokenText, Classification tokenType, List<State> visitedStates) {
        this.tokenText = tokenText;
        this.tokenType = tokenType;
        this.visitedStates = visitedStates;
    }

    public String getTokenText() {
        return tokenText;
    }

    public Classification getTokenType() {
        return tokenType;
    }

    public List<State> getVisitedStates() {
        return visitedStates;
    }

    @Override
    public String toString() {
        List<String> states = visitedStates.stream().map(State::toString).collect(Collectors.toList());
        return String.format("%s -> [%s] -> %s ", tokenText, String.join("-", states), tokenType);
    }

}
