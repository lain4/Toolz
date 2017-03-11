import java.util.Stack;
import java.util.Arrays;

abstract class SimpleEvaluator {
    
    static final int evaluate(String str) {
        if(str.contains("(")) {
            //index of highest bracket
            final int bracketStart = str.lastIndexOf("(");
            final int bracketEnd = str.indexOf(")", str.lastIndexOf("("));

            return evaluate(str.substring(0, bracketStart) +
                        evalMathExp(str.substring(bracketStart + 1, bracketEnd)) +
                        str.substring(bracketEnd + 1));
        } else
            return evalMathExp(str);
    }

    private static final int evalMathExp(String str) {
        final Stack<Integer> stck = new Stack<>();

        Arrays.stream(str.replaceAll("\\s", "")
                      .split("(?=\\D)"))
              .forEach(e -> {if (e.matches("\\d+"))
                                stck.push(Integer.parseInt(e));
                            else
                                evalOperator(stck, e);
                            });
        
        return stck.stream()
                  .mapToInt(Integer::intValue)
                  .sum();
    }

    private static void evalOperator(Stack<Integer> stck, String str) {
        switch(str.charAt(0)) {
            case '+':
                stck.push(Integer.parseInt(str.substring(1)));
                break;
            case '-':
                stck.push(-Integer.parseInt(str.substring(1)));
                break;
            case '/':
                stck.push(stck.pop() / Integer.parseInt(str.substring(1)));
                break;
            case '*':
                stck.push(stck.pop() * Integer.parseInt(str.substring(1)));
                break;
            case '%':
                stck.push(stck.pop() % Integer.parseInt(str.substring(1)));
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + str.charAt(0));
        }
    }

}
