import java.util.Stack;
import java.util.Arrays;

abstract class SimpleEvaluator {
    
    static final int evaluate(String str) {
        if(str.contains("(")) {
            //index of highest bracket
            final int bracketStart = str.lastIndexOf("(");
            final int bracketEnd = str.indexOf(")", bracketStart);

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
        final char op = str.charAt(0);
        final int num = Integer.parseInt(str.substring(1));
        
        switch(op) {
            case '+':
                stck.push(num);
                break;
            case '-':
                stck.push(-num);
                break;
            case '/':
                stck.push(stck.pop() / num);
                break;
            case '*':
                stck.push(stck.pop() * num);
                break;
            case '%':
                stck.push(stck.pop() % num);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

}
