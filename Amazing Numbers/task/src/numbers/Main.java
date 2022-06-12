package numbers;

import java.util.*;

import static numbers.Property.*;

public class Main {
    static Map<NumberProperty, NumberProperty> mutuallyExclusiveProps;

    public static void initFields() {
        mutuallyExclusiveProps = new HashMap<>();
        mutuallyExclusiveProps.put(new NumberProperty(EVEN), new NumberProperty(ODD));
        mutuallyExclusiveProps.put(new NumberProperty(DUCK), new NumberProperty(SPY));
        mutuallyExclusiveProps.put(new NumberProperty(SQUARE), new NumberProperty(SUNNY));
        mutuallyExclusiveProps.put(new NumberProperty(SAD), new NumberProperty(HAPPY));
    }

    public static void printMenu() {
        String menu = "Supported requests:\n" +
                      "- enter a natural number to know its properties;\n" +
                      "- enter two natural numbers to obtain the properties of the list:\n" +
                      "  * the first parameter represents a starting number;\n" +
                      "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                      "- two natural numbers and a properties to search for;\n" +
                      "- a property preceded by minus must not be present in numbers;\n" +
                      "- separate the parameters with one space;\n" +
                      "- enter 0 to exit";
        System.out.println(menu);
    }

    public static boolean isNatural(long n) {
        return n > 0;
    }

    public static boolean isRequestHasNInputs(String[] inputs, int n) {
        return inputs.length == n;
    }

    public static boolean isInputZero(String[] inputs) {
        return inputs[0].equals("0");
    }

    public static List<NumberProperty> convertPropsToEnumList(List<String> props) {
        List<NumberProperty> numberProperties = new ArrayList<>();
        for (String prop : props) {
            try {
                Property property = Property.valueOf(prop.replace("-", "").toUpperCase());
                NumberProperty numberProperty;
                if (prop.contains("-")) {
                    numberProperty = new NumberProperty(property, Sign.NEGATIVE);
                } else {
                    numberProperty = new NumberProperty(property);
                }
                numberProperties.add(numberProperty);
            } catch (IllegalArgumentException ignored) {
                numberProperties.add(null);
            }
        }
        return numberProperties;
    }

    public static List<Integer> findUnsupportedProperty(List<NumberProperty> properties) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i) == null) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static boolean isMutuallyExclusiveProperties(List<NumberProperty> properties) {
        if (properties.size() <= 1) {
            return false;
        }
        for (NumberProperty prop : properties) {
//            getAntiProp();
            if (prop.getSign().equals(Sign.POSITIVE)) {
                NumberProperty antiProp = null;
                if (mutuallyExclusiveProps.containsKey(prop)) {
                    antiProp = mutuallyExclusiveProps.getOrDefault(prop, null);
                } else if (mutuallyExclusiveProps.containsValue(prop)) {
                    for (Map.Entry<NumberProperty, NumberProperty> entry : mutuallyExclusiveProps.entrySet()) {
                        if (entry.getValue().equals(prop)) {
                            antiProp = entry.getKey();
                        }
                    }
                }
                if (antiProp == null) {
                    return false;
                }
                if (properties.contains(antiProp)) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", prop, antiProp);
                    System.out.println("There are no numbers with these properties.");
                    return true;
                }
                antiProp = new NumberProperty(prop.getProperty(), Sign.NEGATIVE);
                if (properties.contains(antiProp)) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", prop, antiProp);
                    System.out.println("There are no numbers with these properties.");
                    return true;
                }
            } else {
                NumberProperty propPositiveClone = new NumberProperty(prop.getProperty());
                NumberProperty antiProp = null;
                if (mutuallyExclusiveProps.containsKey(propPositiveClone)) {
                    antiProp = mutuallyExclusiveProps.getOrDefault(propPositiveClone, null);
                } else if (mutuallyExclusiveProps.containsValue(propPositiveClone)) {
                    for (Map.Entry<NumberProperty, NumberProperty> entry : mutuallyExclusiveProps.entrySet()) {
                        if (entry.getValue().equals(propPositiveClone)) {
                            antiProp = entry.getKey();
                        }
                    }
                }
                if (antiProp == null) {
                    return false;
                }
                antiProp = new NumberProperty(antiProp.getProperty(), Sign.NEGATIVE);
                if (properties.contains(antiProp)) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", prop, antiProp);
                    System.out.println("There are no numbers with these properties.");
                    return true;
                }
                antiProp = new NumberProperty(prop.getProperty());
                if (properties.contains(antiProp)) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", prop, antiProp);
                    System.out.println("There are no numbers with these properties.");
                    return true;
                }
            }
        }
        return false;
    }

    public static List<NumberProperty> getPositiveProps(List<NumberProperty> properties) {
        List<NumberProperty> positiveProps = new ArrayList<>();
        for (NumberProperty prop : properties) {
            if (prop.getSign().equals(Sign.POSITIVE)) {
                positiveProps.add(prop);
            }
        }
        return positiveProps;
    }

    public static List<NumberProperty> getNegativeProps(List<NumberProperty> properties) {
        List<NumberProperty> negativeProps = new ArrayList<>();
        for (NumberProperty prop : properties) {
            if (prop.getSign().equals(Sign.NEGATIVE)) {
                negativeProps.add(prop);
            }
        }
        return negativeProps;
    }

    public static List<NumberProperty> switchSign(List<NumberProperty> properties) {
        List<NumberProperty> newList = new ArrayList<>();
        for (NumberProperty numberProperty : properties) {
            Sign sign = numberProperty.getSign().equals(Sign.POSITIVE) ? Sign.NEGATIVE : Sign.POSITIVE;
            NumberProperty property = new NumberProperty(numberProperty.getProperty(), sign);
            newList.add(property);
        }
        return newList;
    }

    public static void main(String[] args) {
        initFields();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        printMenu();
        while (true) {
            System.out.println("\nEnter a request:");
            String[] inputs = scanner.nextLine().split(" ");
            if (isRequestHasNInputs(inputs, 0)) {
                printMenu();
            } else if (isRequestHasNInputs(inputs, 1)) {
                if (isInputZero(inputs)) {
                    break;
                }
                Number n = new Number(Long.parseLong(inputs[0]));
                if (n.isNatural()) {
                    System.out.println(n.getMultilineProperties());
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            } else {
                Number startingNumber = new Number(Long.parseLong(inputs[0]));
                Number length = new Number(Long.parseLong(inputs[1]));
                if (!startingNumber.isNatural()) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                if (!length.isNatural()) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                if (isRequestHasNInputs(inputs, 2)) {
                    for (long i = startingNumber.getValue(); i < startingNumber.getValue() + length.getValue(); i++) {
                        System.out.println(new Number(i).getInlineProperties());
                    }
                } else {
                    List<NumberProperty> properties = convertPropsToEnumList(Arrays.asList(inputs).subList(2, inputs.length));
                    List<Integer> indexes = findUnsupportedProperty(properties);
                    if (indexes.size() > 0) {
                        if (indexes.size() == 1) {
                            System.out.printf("The property [%s] is wrong.\n", inputs[indexes.get(0) + 2]);
                        } else {
                            StringBuilder unsupportedProps = new StringBuilder();
                            for (int index : indexes) {
                                unsupportedProps.append(inputs[indexes.get(index) + 2]).append(", ");
                            }
                            unsupportedProps.deleteCharAt(unsupportedProps.length() - 1);
                            System.out.printf("The properties [%s] are wrong.\n", unsupportedProps);
                        }
                        System.out.println("Available properties: " + Arrays.toString(Property.values()));
                        continue;
                    }
                    if (!isMutuallyExclusiveProperties(properties)) {
                        List<NumberProperty> negativeProps = getNegativeProps(properties);
                        properties.removeAll(negativeProps);
                        List<NumberProperty> exclusionList = switchSign(negativeProps);
                        int foundNumbers = 0;
                        for (long i = startingNumber.getValue(); foundNumbers < length.getValue(); i++) {
                            Number n = new Number(i);
                            Set<NumberProperty> inlineProperties = n.getProperties();
                            if (exclusionList.size() == 0 && inlineProperties.containsAll(properties)
                                || inlineProperties.containsAll(properties) && !inlineProperties.containsAll(exclusionList)) {
                                System.out.println(n.getInlineProperties());
                                foundNumbers++;
                            }
                        }
                    }
                }
            }
        }
    }
}







