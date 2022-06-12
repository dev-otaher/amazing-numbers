package numbers;

import java.util.*;

import static numbers.Property.*;

public class Number {
    private final long value;
    Set<NumberProperty> properties;

    public Number(long value) {
        this.value = value;
        this.properties = new LinkedHashSet<>();
        setNatural();
    }

    public Set<NumberProperty> getProperties() {
        if (properties.size() <= 1) {
            calculateProperties();
        }
        return properties;
    }

    private boolean isEndedWith7() {
        return value % 10 == 7;
    }

    private boolean isDivisibleBy7() {
        return value % 7 == 0;
    }

    private boolean isPositiveNumber() {
        return value > 0;
    }

    private boolean isNumberMoreThan3Digits() {
        return String.valueOf(value).length() >= 3;
    }

    private boolean isDivisibleByFirstAndLastDigits() {
        String string = String.valueOf(value);
        String firstDigit = String.valueOf(string.charAt(0));
        String lastDigit = String.valueOf(string.charAt(string.length() - 1));
        int firstAndLastDigits = Integer.parseInt(firstDigit + lastDigit);
        return value % firstAndLastDigits == 0;
    }

    private long calculateAllDigitsSum() {
        String n = String.valueOf(value);
        long sum = 0;
        for (String digit : n.split("")) {
            sum += Long.parseLong(digit);
        }
        return sum;
    }

    private long calculateAllDigitsProduct() {
        String n = String.valueOf(value);
        long product = 1;
        for (String digit : n.split("")) {
            product *= Long.parseLong(digit);
        }
        return product;
    }

    private void setNatural() {
        if (value > 0) {
            properties.add(new NumberProperty(NATURAL));
        }
    }

    private void setEven() {
        if (value % 2 == 0) {
            properties.add(new NumberProperty(EVEN));
        }
    }

    private void setOdd() {
        if (!isEven()) {
            properties.add(new NumberProperty(ODD));
        }
    }

    private void setBuzz() {
        if (isDivisibleBy7() || isEndedWith7()) {
            properties.add(new NumberProperty(BUZZ));
        }
    }

    private void setDuck() {
        String string = String.valueOf(value);
        if (isPositiveNumber() && string.contains("0") && string.charAt(0) != '0') {
            properties.add(new NumberProperty(DUCK));
        }
    }

    private void setPalindromic() {
        boolean palindromic = true;
        String string = String.valueOf(value);
        int stringLength = string.length();
        for (int i = 0; i < stringLength / 2; i++) {
            if (string.charAt(i) != string.charAt(stringLength - i - 1)) {
                palindromic = false;
                break;
            }
        }
        if (palindromic) {
            properties.add(new NumberProperty(PALINDROMIC));
        }
    }

    private void setGapful() {
        if (isNumberMoreThan3Digits() && isDivisibleByFirstAndLastDigits()) {
            properties.add(new NumberProperty(GAPFUL));
        }
    }

    private void setSpy() {
        if (calculateAllDigitsSum() == calculateAllDigitsProduct()) {
            properties.add(new NumberProperty(SPY));
        }
    }

    private void setSquare() {
        if (Math.floor(Math.sqrt(value)) == Math.sqrt(value)) {
            properties.add(new NumberProperty(SQUARE));
        }
    }

    private void setSunny() {
        if (Math.floor(Math.sqrt(value + 1)) == Math.sqrt(value + 1)) {
            properties.add(new NumberProperty(SUNNY));
        }
    }

    private void setJumping() {
        boolean jumping = true;
        String str = String.valueOf(this.value);
        for (int i = 0; i < str.length() - 1; i++) {
            int digit1 = Integer.parseInt(str.substring(i, i + 1));
            int digit2 = Integer.parseInt(str.substring(i + 1, i + 2));
            if (Math.abs(digit1 - digit2) != 1) {
                jumping = false;
                break;
            }
        }
        if (jumping) {
            properties.add(new NumberProperty(JUMPING));
        }
    }

    private void setHappy() {
        String number = String.valueOf(this.value);
        List<Integer> sumTrackList = new ArrayList<>();
        while (true) {
            int sum = 0;
            for (int i = 0; i < number.length(); i++) {
                sum += Math.pow(Integer.parseInt(String.valueOf(number.charAt(i))), 2);
            }
            if (sum == 1) {
                properties.add(new NumberProperty(HAPPY));
                break;
            }
            if (sum == value || sumTrackList.contains(sum)) {
                properties.add(new NumberProperty(SAD));
                break;
            } else {
                sumTrackList.add(sum);
            }
            number = String.valueOf(sum);
        }
    }

    private void setSad() {
//        if (!isHappy()) {
//            properties.add(new NumberProperty(SAD));
//        }
        return;
    }

    public long getValue() {
        return value;
    }

    public boolean isNatural() {
        for (NumberProperty prop : properties) {
            if (prop.getProperty().equals(NATURAL)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEven() {
        for (NumberProperty prop : properties){
            if (prop.getProperty().equals(EVEN)){
                return true;
            }
        }
        return false;
    }

    public boolean isOdd() {
        for (NumberProperty prop : properties){
            if (prop.getProperty().equals(ODD)){
                return true;
            }
        }
        return false;
    }

    public boolean isBuzz() {
        for (NumberProperty prop : properties){
            if (prop.getProperty().equals(BUZZ)){
                return true;
            }
        }
        return false;
    }

    public boolean isDuck() {
        for (NumberProperty prop : properties){
            if (prop.getProperty().equals(DUCK)){
                return true;
            }
        }
        return false;
    }

    public boolean isPalindromic() {
        for (NumberProperty prop : properties){
            if (prop.getProperty().equals(PALINDROMIC)){
                return true;
            }
        }
        return false;
    }

    public boolean isGapful() {
        for (NumberProperty prop : properties) {
            if (prop.getProperty().equals(GAPFUL)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpy() {
        for (NumberProperty prop : properties) {
            if (prop.getProperty().equals(SPY)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSquare() {
        for (NumberProperty prop : properties) {
            if (prop.getProperty().equals(SQUARE)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSunny() {
        for (NumberProperty prop : properties) {
            if (prop.getProperty().equals(SUNNY)) {
                return true;
            }
        }
        return false;
    }

    public boolean isJumping() {
        for (NumberProperty prop : properties) {
            if (prop.getProperty().equals(JUMPING)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHappy() {
        for (NumberProperty prop : properties) {
            if (prop.getProperty().equals(HAPPY)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSad() {
        return !isHappy();
    }

    private void calculateProperties() {
        if (isNatural()) {
            setEven();
            setOdd();
            setBuzz();
            setDuck();
            setPalindromic();
            setGapful();
            setSpy();
            setSquare();
            setSunny();
            setJumping();
            setHappy();
            setSad();
        }
    }

    public String getInlineProperties() {
        if (properties.size() < 2) {
            calculateProperties();
        }
        return value + " is " + properties.toString()
                .replace("NATURAL, ", "")
                .replace("[", "")
                .replace("]", "")
                .toLowerCase();
    }

    public String getMultilineProperties() {
        if (properties.size() < 2) {
            calculateProperties();
        }
        String string = "Properties of %d" +
                        "\neven: %b" +
                        "\nodd: %b" +
                        "\nbuzz: %b" +
                        "\nduck: %b" +
                        "\npalindromic: %b" +
                        "\ngapful: %b" +
                        "\nspy: %b" +
                        "\nsquare: %b" +
                        "\nsunny: %b" +
                        "\njumping: %b" +
                        "\nhappy: %b" +
                        "\nsad: %b";
        return String.format(string, this.value, isEven(), isOdd(), isBuzz(), isDuck(), isPalindromic(),
                isGapful(), isSpy(), isSquare(), isSunny(), isJumping(), isHappy(), isSad());
    }
}
