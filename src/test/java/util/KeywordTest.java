package util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class KeywordTest extends DFATest{

    @Test
    @DisplayName("Validate IS keyword")
    public void validateIsKeyword(){
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = START_STATE;
            currentState = dfa.getNextState(currentState, "I");
            Assert.assertEquals(new State(14), currentState);

            currentState = dfa.getNextState(currentState, "S");
            Assert.assertEquals(new State(15), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate NOT keyword")
    public void validateNotKeyword(){
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = START_STATE;
            currentState = dfa.getNextState(currentState, "N");
            Assert.assertEquals(new State(16), currentState);

            currentState = dfa.getNextState(currentState, "O");
            Assert.assertEquals(new State(17), currentState);

            currentState = dfa.getNextState(currentState, "T");
            Assert.assertEquals(new State(18), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate NULL keyword")
    public void validateNullKeyword(){
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = START_STATE;
            currentState = dfa.getNextState(currentState, "N");
            Assert.assertEquals(new State(16), currentState);

            currentState = dfa.getNextState(currentState, "U");
            Assert.assertEquals(new State(19), currentState);

            currentState = dfa.getNextState(currentState, "L");
            Assert.assertEquals(new State(20), currentState);

            currentState = dfa.getNextState(currentState, "L");
            Assert.assertEquals(new State(21), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate WHERE keyword")
    public void validateWhereKeyword(){
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = START_STATE;
            currentState = dfa.getNextState(currentState, "W");
            Assert.assertEquals(new State(22), currentState);

            currentState = dfa.getNextState(currentState, "H");
            Assert.assertEquals(new State(23), currentState);

            currentState = dfa.getNextState(currentState, "E");
            Assert.assertEquals(new State(24), currentState);

            currentState = dfa.getNextState(currentState, "R");
            Assert.assertEquals(new State(25), currentState);

            currentState = dfa.getNextState(currentState, "E");
            Assert.assertEquals(new State(26), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate OR keyword")
    public void validateOrKeyword(){
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = START_STATE;
            currentState = dfa.getNextState(currentState, "O");
            Assert.assertEquals(new State(27), currentState);

            currentState = dfa.getNextState(currentState, "R");
            Assert.assertEquals(new State(28), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate AND keyword")
    public void validateANDKeyword(){
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = START_STATE;
            currentState = dfa.getNextState(currentState, "A");
            Assert.assertEquals(new State(29), currentState);

            currentState = dfa.getNextState(currentState, "N");
            Assert.assertEquals(new State(30), currentState);

            currentState = dfa.getNextState(currentState, "D");
            Assert.assertEquals(new State(31), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate FROM keyword")
    public void validateFromKeyword(){
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = START_STATE;
            currentState = dfa.getNextState(currentState, "F");
            Assert.assertEquals(new State(32), currentState);

            currentState = dfa.getNextState(currentState, "R");
            Assert.assertEquals(new State(33), currentState);

            currentState = dfa.getNextState(currentState, "O");
            Assert.assertEquals(new State(34), currentState);

            currentState = dfa.getNextState(currentState, "M");
            Assert.assertEquals(new State(35), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate SELECT keyword")
    public void validateSelectKeyword(){
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = START_STATE;
            currentState = dfa.getNextState(currentState, "S");
            Assert.assertEquals(new State(36), currentState);

            currentState = dfa.getNextState(currentState, "E");
            Assert.assertEquals(new State(37), currentState);

            currentState = dfa.getNextState(currentState, "L");
            Assert.assertEquals(new State(38), currentState);

            currentState = dfa.getNextState(currentState, "E");
            Assert.assertEquals(new State(39), currentState);

            currentState = dfa.getNextState(currentState, "C");
            Assert.assertEquals(new State(40), currentState);

            currentState = dfa.getNextState(currentState, "T");
            Assert.assertEquals(new State(41), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    private void validateFinalState(State finalState){
        Assert.assertTrue(finalState.isGoalState());
        Assert.assertEquals(Classification.KEYWORD, finalState.getStateClassification());
    }
}
