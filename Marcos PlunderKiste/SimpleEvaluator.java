import java.util.Stack;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

abstract class SimpleEvaluator {

    static final boolean isValid(String str) {
        int index = 0;
        int brCounter = 0;
        boolean opFlag = false;

        for(char c : str.toCharArray()) {
            index++;
            switch (c) {
                case '(':
                    brCounter++;
                    break;
                case ')':
                    if (brCounter > 0){
                        brCounter--;
                        break;
                    } else
                        return false;
                case '+':
                case '-':
                case '*':
                case '/':
                case '%':
                    if(!opFlag) {
                        opFlag = true;
                        break;
                    } else
                        return false;
                default:
                    if(Character.isDigit(c)) {
                        opFlag = false;
                        break;
                    } else
                        return false;
            }
        }
        return ((brCounter == 0) && !opFlag);
    }

    static final int evaluate(String str) {
        if(isValid(str.replaceAll("\\s", ""))) {
            if(str.contains("(")) {
                final int bracketStart = str.lastIndexOf("(");
                final int bracketEnd = str.indexOf(")", str.lastIndexOf("("));

                return evaluate(str.substring(0, bracketStart) +
                                evalMathExp(str.substring(bracketStart + 1, bracketEnd)) +
                                str.substring(bracketEnd+1));
            } else
                return evalMathExp(str);
        } else
            throw new IllegalArgumentException("Invalid expression!");
    }

    private static final int evalMathExp(String str) {
        final Stack<Integer> stck = new Stack<Integer>();

        Arrays.stream(str.replaceAll("\\s", "")
                        .split("(?=\\D)"))
              .forEach(e -> {if (e.matches("(\\+|\\-)?\\d+"))
                                stck.push(Integer.parseInt(e));
                            else
                                evalOperator(stck, e);
                            });

        return stck.stream()
                  .mapToInt(Integer::intValue)
                  .sum();
    }

    private static void evalOperator(Stack<Integer> stck, String str) {
        final char op = str.charAt(0);
        final int num = Integer.parseInt(str.substring(1));

        switch(op) {
            case '/':
                if (num != 0)
                    stck.push(stck.pop() / num);
                else
                    throw new ArithmeticException("Division by zero");
                break;
            case '*':
                stck.push(stck.pop() * num);
                break;
            case '%':
                stck.push(stck.pop() % num);
                break;
            default:
                throw new UnsupportedOperationException("Unknown operator: " + op);
        }
    }
}
