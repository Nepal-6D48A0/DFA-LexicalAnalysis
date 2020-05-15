package parser.dfa_sql;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import util.StringClassifier;
import util.Token;

import java.util.List;

@RestController
public class ParseController {

    @GetMapping("/api/parse")
    public ResultResponse parseTokens(@RequestParam(value = "inputString") String inputString) {
        try {
            List<Token> tokenList = StringClassifier.classifyForTokens(inputString);
            return new ResultResponse(tokenList);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}


class ResultResponse {
    private List<Token> tokenList;

    public ResultResponse(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public List<Token> getResult() {
        return this.tokenList;
    }
}