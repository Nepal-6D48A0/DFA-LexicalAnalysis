package util;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.List;

public class StringParserTest {

    @Test
    public void testForStringParsing() {
        String testString = "SELECT TEST.TEST_TABLE WHERE A IS not NULL and B >= .123 OR C <= 12.123;";
        List<Token> classifiedTokens = null;
        try {
            classifiedTokens = StringClassifier.classifyForTokens(testString);
        } catch (Exception e) {
            Assert.fail();
        }

        for(Token token : classifiedTokens) {
            System.out.println(token);
        }
    }
}
