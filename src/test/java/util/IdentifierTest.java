package util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class IdentifierTest extends DFATest {

    @Test
    @DisplayName("Test for identifier with alphabets")
    public void validateIdentifiersWithAlphabets() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState;
            currentState = dfa.getNextState(START_STATE, "A");
            Assert.assertEquals(new State(29), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "N");
            Assert.assertEquals(new State(30), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "T");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Test for identifier with alphabets and digits")
    public void validateIdentifiersWithAlphabetsDigits() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState;
            currentState = dfa.getNextState(START_STATE, "X");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "A");
            Assert.assertEquals(new State(43), currentState);

            assertIdentifier(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Test for identifier with alphabets, digits, and underscore")
    public void validateIdentifiersWithAlphaDigitUnderscore() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState;
            currentState = dfa.getNextState(START_STATE, "A");
            Assert.assertEquals(new State(29), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "_");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "B");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Test for identifier that begin with underscore")
    public void validateIdentifiersWithUnderscorePrefix() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState;
            currentState = dfa.getNextState(START_STATE, "_");
            Assert.assertEquals(new State(42), currentState);
            assertInvalidState(currentState);

            currentState = dfa.getNextState(currentState, "A");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

            currentState = dfa.getNextState(currentState, "B");
            Assert.assertEquals(new State(43), currentState);
            assertIdentifier(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void assertIdentifier(State finalState){
        Assert.assertTrue(finalState.isGoalState());
        Assert.assertEquals(Classification.IDENTIFIER, finalState.getStateClassification());
    }
}
