import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Shridhar on 5/23/2017.
 */
public class Merchant {

    private Problem problem;

    private Map<String, RomanNumeral> knownUnits = new HashMap<>();

    private Map<String, Double> unknownUnits = new HashMap<>();

    private Map<String, Integer> calculatedCredits = new LinkedHashMap<>();

    public Merchant(Problem problem) {
        this.problem = problem;
    }

    private void extractKnownUnits() {
        problem.getStatements(StatementType.ASSIGNMENT).forEach((String line) -> {
                String galaticNumeral = line.substring(0, line.indexOf(" "));
                char romanNumeral = line.charAt(line.length() - 1);
                knownUnits.put(galaticNumeral, RomanNumeral.get(romanNumeral));
        });
    }

    private  void extractUnknownUnits() {
        problem.getStatements(StatementType.UNKNOWN_CREDITS).forEach((String line) -> {
                line = line.replaceFirst("is ", "").replaceFirst(" Credits", "");
                String[] galaticNumerals = line.split(" ");
                String romanNumber = "";
                String unknownNumeral = "";
                for (int i = 0; i < galaticNumerals.length - 1; i++) {

                    if (knownUnits.containsKey(galaticNumerals[i])) {
                        romanNumber += convertToRomanNumeral(galaticNumerals[i]);
                    } else {
                        unknownNumeral = galaticNumerals[i];
                    }
                }
                Double totalCredits = Double.parseDouble(galaticNumerals[galaticNumerals.length - 1]);
                Double value = totalCredits / (double) (new RomanNumber(romanNumber).value());
                unknownUnits.put(unknownNumeral, value);
        });

    }

    private String convertToRomanNumeral(String galaticNumeral) {
        return String.valueOf(knownUnits.get(galaticNumeral).numeral);
    }

    private void calculateCredits() {
        problem.getStatements(StatementType.QUESTIONS).forEach((String line) -> {
                if (line.contains("how much is ")) {
                    line = line.replaceFirst("how much is ", "").replaceFirst(" \\?", "");
                    String[] galaticNumerals = line.split(" ");
                    int romanValue = (new RomanNumber(String.valueOf(convertToRomanNumeral(galaticNumerals[0])) + String.valueOf(convertToRomanNumeral(galaticNumerals[1]) + String.valueOf(convertToRomanNumeral(galaticNumerals[2]) + String.valueOf(convertToRomanNumeral(galaticNumerals[3]))))).value());
                    calculatedCredits.put(line + " is ", romanValue);
                }
            else if (line.contains("how many Credits is ")) {
                line = line.replaceFirst("how many Credits is ", "").replaceFirst(" \\?", "");
                String[] galaticNumerals = line.split(" ");
                int romanValue = (int) ((new RomanNumber(String.valueOf(convertToRomanNumeral(galaticNumerals[0])) + String.valueOf(convertToRomanNumeral(galaticNumerals[1]))).value()) *
                                        unknownUnits.get(galaticNumerals[2]));
                calculatedCredits.put(line + " is ", romanValue);
            }
            else{
                calculatedCredits.put("I have no idea what you are talking about", -1);
            }
        });

    }

    private void printCalulatedCredits() {
        calculatedCredits.forEach((galaticNumber, value) -> {
                System.out.println(galaticNumber + (value != -1 ? value + " Credits" : ""));
        });
    }

    public void solveProblem() {
        extractKnownUnits();
        extractUnknownUnits();
        calculateCredits();
        printCalulatedCredits();
    }
}
