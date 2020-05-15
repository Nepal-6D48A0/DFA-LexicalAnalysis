package util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TokenTest {

    @Test
    public void testForToken(){
        String testString = "SELECT * FROM TEST.TEST_TABLE WHERE A IS NOT NULL AND B <= 12.123";
        List<String> tokens = TokenGenerator.getTokens(testString);
        System.out.println(tokens);

        Assert.assertEquals(16, tokens.size());
    }
}
