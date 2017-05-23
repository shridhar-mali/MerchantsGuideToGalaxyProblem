import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Shridhar on 5/23/2017.
 */
public class Problem {

    private Map<StatementType, List<String>> statementMap = new HashMap<>();

    public Problem(String fileName) throws IOException, URISyntaxException {
        extractStatementsByType(Files.lines(Paths.get(Problem.class.getResource(fileName).toURI())));
    }

    private void extractStatementsByType(Stream<String> statements){
        statements.forEach((String line) -> {
              if (line.endsWith("Credits")) {
                if(statementMap.containsKey(StatementType.UNKNOWN_CREDITS)) {
                    statementMap.get(StatementType.UNKNOWN_CREDITS).add(line);
                }
                else {
                    List<String> creditsStatements = new ArrayList<>();
                    creditsStatements.add(line);
                    statementMap.put(StatementType.UNKNOWN_CREDITS, (creditsStatements));
                }

            }
            else if (line.contains(" is ") && !line.endsWith("?")) {
                if(statementMap.containsKey(StatementType.ASSIGNMENT)) {
                    statementMap.get(StatementType.ASSIGNMENT).add(line);
                }
                else {
                    List<String> assignmentStatements = new ArrayList<>();
                    assignmentStatements.add(line);
                    statementMap.put(StatementType.ASSIGNMENT, (assignmentStatements));
                }

            }
            else {
                if(statementMap.containsKey(StatementType.QUESTIONS)) {
                    statementMap.get(StatementType.QUESTIONS).add(line);
                }
                else {
                    List<String> questionStatements = new ArrayList<>();
                    questionStatements.add(line);
                    statementMap.put(StatementType.QUESTIONS, (questionStatements));
                }

            }

        });
    }
        public List<String> getStatements(StatementType statementType) {
            return new ArrayList<>(statementMap.get(statementType));
        }
}


