package util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class NumberTest extends DFATest {

    @Test
    @DisplayName("Validate single digit integer 1")
    public void validateSingleDigitNumber() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = dfa.getNextState(START_STATE, "1");

            Assert.assertEquals(new State(5), currentState);
            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate multi digit integer: 123")
    public void validateMultiDigitNumber() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());
            State currentState;

            currentState = dfa.getNextState(START_STATE, "1");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, "3");
            Assert.assertEquals(new State(5), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate negative integer: -12")
    public void validateNegativeInteger() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = dfa.getNextState(START_STATE, "-");
            Assert.assertEquals(new State(4), currentState);
            assertInvalidState(currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, "3");
            Assert.assertEquals(new State(5), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate decimal number: 12.12")
    public void validateDecimal() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = dfa.getNextState(START_STATE, "1");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, ".");
            Assert.assertEquals(new State(6), currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(7), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(7), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate negative decimal number: -12.12")
    public void validateNegativeDecimal() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = dfa.getNextState(START_STATE, "-");
            Assert.assertEquals(new State(4), currentState);
            assertInvalidState(currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, ".");
            Assert.assertEquals(new State(6), currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(7), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(7), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate decimal number with no preceding number: .123")
    public void validateDecimalWithNoPrecedingNumber() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = dfa.getNextState(START_STATE, ".");
            Assert.assertEquals(new State(44), currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(6), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(7), currentState);

            currentState = dfa.getNextState(currentState, "3");
            Assert.assertEquals(new State(7), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate decimal number with no succeeding number: 123.")
    public void validateDecimalWithNoSucceedingNumber() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = dfa.getNextState(START_STATE, "1");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, "3");
            Assert.assertEquals(new State(5), currentState);

            currentState = dfa.getNextState(currentState, ".");
            Assert.assertEquals(new State(6), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @DisplayName("Validate negative decimal number: -.123 ")
    public void validateNegativeDecimalWithNoPrecedingNumber() {
        try {
            DFA dfa = new DFA(RESOURCE.getFile());

            State currentState = dfa.getNextState(START_STATE, "-");
            Assert.assertEquals(new State(4), currentState);
            assertInvalidState(currentState);

            currentState = dfa.getNextState(currentState, ".");
            Assert.assertEquals(new State(45), currentState);
            assertInvalidState(currentState);

            currentState = dfa.getNextState(currentState, "1");
            Assert.assertEquals(new State(6), currentState);

            currentState = dfa.getNextState(currentState, "2");
            Assert.assertEquals(new State(7), currentState);

            currentState = dfa.getNextState(currentState, "3");
            Assert.assertEquals(new State(7), currentState);

            validateFinalState(currentState);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    private void validateFinalState(State finalState) {
        Assert.assertTrue(finalState.isGoalState());
        Assert.assertEquals(Classification.NUMBER, finalState.getStateClassification());
    }

}
