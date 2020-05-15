package util;

import java.util.*;

/**
 * Helper class to generate tokens from the input string
 */
public class TokenGenerator {

    final static Character[] DELIMITERS_ARRAY = {',', '<', '>', '=', ';', '(', ')', '\'', '\t', ' ', '.'};
    final static Set<Character> DELIMITERS = new HashSet<>(Arrays.asList(DELIMITERS_ARRAY));

    /**
     * Not allowed to use regular expression so have to manually parse it :(
     * It stores two pointers to identify start and end of a token
     * The end pointer moves from left to right until it hits a delimiter or end of string
     * It stores the substring of start and end position to a list
     * It updates the start position with end position and repeats the process.
     *
     * @param inputString String to get tokens from
     * @return List of tokens from the input string
     */
    public static List<String> getTokens(String inputString) {

        String originalString = inputString;
        inputString = inputString.toUpperCase();

        int startPos = 0;
        int endPos = 0;
        String currentToken;

        List<String> allTokens = new LinkedList<>();

        int totalLength = inputString.length();

        while (endPos < totalLength) {

            // Check for numbers beginning with .
            if (inputString.charAt(endPos) == '.' && isPartOfDecimal(inputString, startPos, endPos)) {
                endPos++;
            }

            // move the end cursor until it is not at the end of the input string or hits a delimiter
            while (endPos < totalLength && !DELIMITERS.contains(inputString.charAt(endPos))) {
                endPos++;

                // If the next character is . check if it part of number. If true, skip it as it is not considered a delimiter
                if (endPos < totalLength && inputString.charAt(endPos) == '.' && isPartOfDecimal(inputString, startPos, endPos)) {
                    endPos++;
                }
            }

            // the cursor will not move it is is already in delimiter
            if (startPos == endPos) {
                currentToken = Character.toString(originalString.charAt(startPos));
                endPos++;
            } else {
                currentToken = originalString.substring(startPos, endPos);
            }

            // Ignore for any white spaces
            if (currentToken.trim().length() > 0) {
                allTokens.add(currentToken.trim());
            }

            startPos = endPos;
        }

        return allTokens;
    }

    /**
     * Helper method that checks if the current token is part of number (decimal)
     * This method is called when the we encounter . while parsing the token
     *
     * @param inputString the complete input string
     * @param startPos    start position of the current token
     * @param endPos      current end position of the token
     * @return true if the token is a decimal
     */
    private static boolean isPartOfDecimal(String inputString, int startPos, int endPos) {
        char startingChar = inputString.charAt(startPos);

        // If the start character is digit, it is either number or invalid token
        // If the start character is -, it is negative number or invalid token
        if (Character.isDigit(startingChar) || startingChar == '-')
            return true;

        // Check for token with not preceding digits. Eg: .123
        return startingChar == '.' && (endPos < inputString.length() - 1)
                && Character.isDigit(inputString.charAt(endPos + 1));

    }

}
