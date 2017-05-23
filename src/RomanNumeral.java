/**
 * Created by Shridhar on 5/21/2017.
 */
public enum RomanNumeral {

    I('I', 1),V('V',5),X('X', 10),L('L', 50),C('C', 100),D('D', 500),M('M',1000);

    char numeral;
    int value;
    RomanNumeral(char numeral, int value) {
        this.numeral=numeral;
        this.value=value;
    }

    public static RomanNumeral get (char numeral) {
        for (RomanNumeral instance : RomanNumeral.values()) {
            if(instance.numeral == numeral) {
                return instance;
            }
        }
        return null;
    }
}
