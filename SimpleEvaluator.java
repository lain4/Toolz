import java.util.Stack;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

abstract class SimpleEvaluator {
    
//in progress - missing support for nested brackets
    static final int evaluate(String s) {
        Pattern p = Pattern.compile("(.*)(\\(.*\\))(.*)");
        Matcher m = p.matcher(s);

        return m.matches()? evaluate(m.group(1) +
                                     evaluate(s.substring(s.lastIndexOf("(") + 1, s.indexOf(")", s.lastIndexOf("(")))) +
                                     m.group(3)) :
                            evalMathExp(s);
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
                stck.add(stck.pop() / Integer.parseInt(str.substring(1)));
                break;
            case '*':
                stck.add(stck.pop() * Integer.parseInt(str.substring(1)));
                break;
            case '%':
                stck.add(stck.pop() % Integer.parseInt(str.substring(1)));
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + str.charAt(0));
        }
    }

}
