package util;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class FileParserTest {

    @Test
    public void searchForFile() {
        URL resource = FileParserTest.class.getResource("state-relation.csv");
        File testFile = new File(resource.getFile());
        Assert.assertTrue(testFile.exists());
    }

    @Test
    public void testForHeaders() {
        URL resource = FileParserTest.class.getResource("state-relation.csv");
        File testFile = new File(resource.getFile());

        try {
            RelationParser parser = new RelationParser(testFile);

            List<String> headers = parser.getHeaders();
            System.out.println(headers);

            Assert.assertEquals(50, headers.size());

        } catch (Exception ex) {
            Assert.fail();
        }
    }

    @Test
    public void testForBody() {
        URL resource = FileParserTest.class.getResource("state-relation.csv");
        File testFile = new File(resource.getFile());

        try {
            RelationParser parser = new RelationParser(testFile);
            List<List<String>> body = parser.getBody();
//            for (List<String> state : body){
//                System.out.println(state);
//            }

            Assert.assertEquals(46, body.size());
            Assert.assertEquals(50, body.get(0).size());

        } catch (Exception ex) {
            Assert.fail();
        }
    }


}
