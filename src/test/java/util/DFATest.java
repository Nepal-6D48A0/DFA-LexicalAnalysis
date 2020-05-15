package util;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.Map;

public class DFATest {

    public static final URL RESOURCE = FileParserTest.class.getResource("state-relation.csv");
    public static final State START_STATE = new State(0);

    @Test
    public void getAllKeys() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            Map<State, Map<String, State>> relationMap = dfa.getRelationMap();
            Assert.assertEquals(46, relationMap.keySet().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printRelationForState() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            Map<State, Map<String, State>> relationMap = dfa.getRelationMap();
            for (State currentState : relationMap.keySet()) {
                Map<String, State> currentRelation = relationMap.get(currentState);
                for (String inputKey : currentRelation.keySet()) {
//                    System.out.printf("%3s (%s) -> %s\n", currentState, inputKey, currentRelation.get(inputKey));
                }
//                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void validateStateTransition() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            Assert.assertEquals(new State(1), dfa.getNextState(START_STATE, "<"));
            Assert.assertEquals(new State(2), dfa.getNextState(START_STATE, ">"));
            Assert.assertEquals(new State(3), dfa.getNextState(START_STATE, ";"));
            Assert.assertEquals(new State(8), dfa.getNextState(START_STATE, "="));
            Assert.assertEquals(new State(9), dfa.getNextState(START_STATE, ","));
            Assert.assertEquals(new State(10), dfa.getNextState(START_STATE, "("));
            Assert.assertEquals(new State(11), dfa.getNextState(START_STATE, ")"));
            Assert.assertEquals(new State(12), dfa.getNextState(START_STATE, "*"));
            Assert.assertEquals(new State(13), dfa.getNextState(START_STATE, "'"));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    public void assertInvalidState(State finalState){
        Assert.assertFalse(finalState.isGoalState());
        Assert.assertEquals(Classification.INVALID, finalState.getStateClassification());
    }


}
