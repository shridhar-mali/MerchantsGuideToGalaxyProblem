import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shridhar on 5/21/2017.
 */
public class RomanNumber {

    String romanNumber;

    static String [] INVALID_ROMAN_NUMERAL_COMBINATIONS = new String[] {"IIII", "XXXX", "CCCC", "MMMM", "DD", "LL"
    , "VV", "IL", "IC", "ID", "IM", "XD", "XM", "VX", "VL", "VC", "VD", "VM", "LC", "LD", "LM", "DM"};

    public RomanNumber(String romanNumber) {
        this.romanNumber = romanNumber;
    }

    int value() {
        int result = 0;
        for (int index=0; index < romanNumber.length(); index++) {
            int digit1 = RomanNumeral.get(romanNumber.charAt(index)).value;
            if(hasNextDigit(index +1)) {
                int digit2 = RomanNumeral.get(romanNumber.charAt(index + 1)).value;
                if(digit1 < digit2) {
                    result += digit2;
                    digit1 = -digit1;
                    index++;
                }
            }
                result += digit1;
        }
        return  result;
    }

    private boolean hasNextDigit(int index) {
        return index != romanNumber.length();
    }

    boolean isValid() {
        return Arrays.stream(INVALID_ROMAN_NUMERAL_COMBINATIONS).anyMatch(romanNumber::contains);
    }

}
