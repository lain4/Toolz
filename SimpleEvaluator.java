import java.util.Stack;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

abstract class SimpleEvaluator {

    private static final int evalMathExp(String s) {
        final Stack<Integer> stck = new Stack<Integer>();

        Arrays.stream(s.replaceAll("\\s", "")
                      .split("(?=\\D)"))
              .forEach(e -> {if (e.matches("\\d+"))
                                stck.push(Integer.parseInt(e));
                            else
                                eval1(stck, e);
                            });
        return stck.stream()
                  .mapToInt(Integer::intValue)
                  .sum();
    }

    //in progress - extend to multiple brackets
    static final int evaluate(String s) {
        Pattern p = Pattern.compile("(.*)(\\(.*\\))(.*)");
        Matcher m = p.matcher(s);

        return (m.matches())? evaluate(m.group(1) + evaluate(s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"))) + m.group(3)) :
                             evalMathExp(s);
    }

    private static void eval1(Stack<Integer> stck, String s) {
        switch(s.charAt(0)) {
            case '+':
                stck.push(Integer.parseInt(s.substring(1)));
                break;
            case '-':
                stck.push(-Integer.parseInt(s.substring(1)));
                break;
            case '/':
                stck.add(stck.pop() / Integer.parseInt(s.substring(1)));
                break;
            case '*':
                stck.add(stck.pop() * Integer.parseInt(s.substring(1)));
                break;
            case '%':
                stck.add(stck.pop() % Integer.parseInt(s.substring(1)));
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + s.charAt(0));
        }
    }

}
