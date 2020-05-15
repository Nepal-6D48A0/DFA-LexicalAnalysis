package util;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a helper class that parses the CSV file which is the mapping of the relation
 */
public class RelationParser {

    private File inputFile;

    private List<String> headers;
    private List<List<String>> body;

    public RelationParser(File inputFile) throws Exception {
        this.inputFile = inputFile;
        generateHeaders();
        generateBody();
    }

    /**
     * Read the header and return it. It contains all possible input keys.
     *
     * @throws Exception Error parsing the input file
     */
    private void generateHeaders() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(inputFile));

        String[] rawHeaders = reader.readNext();
        if (rawHeaders == null) {
            throw new Exception("No header found in the input file");
        }

        headers = Arrays.asList(rawHeaders);
    }

    /**
     * Parse the CSV excluding the header. It contains the mapping between current state and next possible states.
     * Invalid states are represented with - in csv file so treat them as null values.
     *
     * @throws Exception Error parsing the input file
     */
    private void generateBody() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        body = new LinkedList<>();

        reader.readNext(); // Skip header

        for (String[] row : reader.readAll()) {
            List<String> currentBody = Arrays.stream(row).map(x -> x.equals("-") ? null : x).collect(Collectors.toList());
            body.add(currentBody);
        }
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<List<String>> getBody() {
        return body;
    }
}
