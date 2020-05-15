package util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class generates a DFA state machine based on the relation provided in the file
 * It has methods too find the next state given the current state and current input
 */
public class DFA {

    private File inputFile;
    private Map<State, Map<String, State>> relationMap;

    /**
     * Constructor for the DFA
     *
     * @param fileName file that contains the relation mapping for the DFA
     * @throws Exception File was not found or error while parsing the file
     */
    public DFA(String fileName) throws Exception {
        inputFile = new File(fileName);
        if (!inputFile.exists()) throw new Exception("No file found");

        generateRelationshipMap();
    }

    /**
     * This method parses the file and creates a mapping for state and input
     * It retrieves the header which contains all possible keys
     * It retrieves the body which contains the current state and the next possible state
     * Using header and body, it defines relation between current state, input key, and next state
     *
     * @throws Exception Error parsing the file
     */
    private void generateRelationshipMap() throws Exception {
        relationMap = new HashMap<>();

        RelationParser parser = new RelationParser(inputFile);
        List<String> headers = parser.getHeaders();

        for (List<String> state : parser.getBody()) {
            int stateNumber = Integer.parseInt(state.get(0));
            State currentState = new State(stateNumber);

            Map<String, State> currentRelation = new HashMap<>();

            for (int i = 1; i < state.size(); i++) {
                if (state.get(i) == null) {
                    continue;
                }

                String inputKey = headers.get(i);
                State nextState = new State(Integer.parseInt(state.get(i)));
                currentRelation.put(inputKey, nextState);
            }

            relationMap.put(currentState, currentRelation);
        }

    }

    /**
     * Return the next state.
     * If it is an invalid move, it returns an invalid state.
     *
     * @param currentState Current state in the state machine
     * @param inputKey     Input key (symbol) in the state machine
     * @return Next state given the current state and input key
     */
    public State getNextState(State currentState, String inputKey) {
        if (currentState == null)
            return State.getInvalidState();

        Map<String, State> currentStateMap = relationMap.getOrDefault(currentState, null);

        if (currentStateMap == null)
            return State.getInvalidState();

        return currentStateMap.getOrDefault(inputKey, State.getInvalidState());
    }

    /**
     * @return all relations in the DFA
     */
    public Map<State, Map<String, State>> getRelationMap() {
        return relationMap;
    }
}
