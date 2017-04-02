import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Caesar {

  public static void main(String[] args) {
      try (Scanner sc = new Scanner(System.in))
      {
          System.out.println("Enter a text: ");
          System.out.println(scramble(sc.nextLine(), askOffset()));
      }
  }

  private final static int askOffset() {
      int offSet = 0;
      do
      {
          try (Scanner sc = new Scanner(System.in))
          {
              System.out.println("Enter a offset: ");
              offSet = sc.nextInt();
          } catch (InputMismatchException ime) {
              System.err.println("Undefined input!");
              offSet = askOffset();
          }
      } while(offSet == 0);
      return offSet;
  }

  static final String scramble(String msg, int offSet) {
      return msg.toLowerCase()
               .chars()
               .map(e -> setOff((char)e, offSet))
               .collect(StringBuilder::new,
                   StringBuilder::appendCodePoint, StringBuilder::append)
               .toString()
               .toUpperCase();
  }

  private final static char setOff(char c, int offSet) {
      if(c > 96 && c+offSet < 123 ) {
          return (char)(c + offSet);
      } else if(c + offSet > 122) {
          return (char)(((c + offSet) % 122) + 96);
      } else
          return c;
  }

}
