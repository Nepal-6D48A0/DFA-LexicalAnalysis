package util;

/**
 * This class represents a state in the state machine
 * Override hashCode and equals to make it usable for hashmaps.
 */
public class State {

    private static final int invalidStateNumber = -1;
    private int stateNumber;

    public State(int stateNumber) {
        this.stateNumber = stateNumber;
    }

    /**
     * @return Invalid state in the state machine
     */
    public static State getInvalidState() {
        return new State(invalidStateNumber);
    }

    public String getState() {
        return "q" + this.stateNumber;
    }

    /**
     * Check if the current state is a terminal state
     *
     * @return Boolean value indicating if the current state is a terminal state
     */
    public boolean isGoalState() {
        return this.stateNumber != 4    // Cannot have - only
                && this.stateNumber != 45  // All . must be followed by at least one digit for numbers
                && this.stateNumber != 42  // All _ must be followed by at least an alphabet or digit
                && (this.stateNumber > 0 && this.stateNumber <= 45);    // Ensure the state number is between 0 and 45
    }

    /**
     * In the state machine, different state represent different token types.
     * Classify the token type based on the current state.
     *
     * @return Classification of the state
     */
    public Classification getStateClassification() {
        if (!isGoalState()) {
            return Classification.INVALID;
        }


        switch (this.stateNumber) {
            // Check for delimiters and special symbols
            case 1:
            case 2:
            case 3:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 44:
                return Classification.SYMBOL;

            // Check for numbers
            case 5:
            case 6:
            case 7:
                return Classification.NUMBER;

            // Check for keywords
            case 15:
            case 18:
            case 21:
            case 26:
            case 28:
            case 31:
            case 35:
            case 41:
                return Classification.KEYWORD;

            // All other states are identifiers
            default:
                return Classification.IDENTIFIER;
        }
    }

    @Override
    public String toString() {
        return "q" + this.stateNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof State)) {
            return false;
        }

        State compareState = (State) o;
        return this.toString().equals(compareState.toString());
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.stateNumber);
    }
}
