package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static boolean firstplayer = true;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Provisorische Spielengine:

        Board b = new Board();
        Generator.generateMoves(b);
        System.out.println(Generator.positions);
        System.out.println(Generator.wins);

        /*
        Random r = new Random();

        Scanner sc = new Scanner(System.in);
        System.out.println("\nHerzlich Willkommen! Du bist an der Reihe, wähle ein Feld ( 0 - 8) \n");
        while(true) {

            System.out.println("Spiel beginnt!");

            while (!b.gameEnd()) {
                again:
                if (firstplayer) {
                    System.out.println("Wähle:");
                    int choice = sc.nextInt();
                    if (Board.moves.contains(choice)) {
                        System.out.println("Ungültig, nochmal!\n");
                        break again;
                    }
                    b.makeMove(choice);
                    firstplayer = false;
                    b.show();
                } else {
                    System.out.println("Computer zieht:");
                    while (Board.moves.size() % 2 != 0) {
                        int tmp = r.nextInt(9);
                        if (!Board.moves.contains(tmp)) b.makeMove(tmp);
                    }
                    firstplayer = true;
                    b.show();
                }
            }

            System.out.println(b.threeInARow() ? (Integer.bitCount(b.board) % 2 == 0 ? "Spieler 2 hat gewonnen" : "Spieler 1 hat gewonnen!") : "Draw!");
            System.out.println("Nochmal? Y/N");
            if (sc.next().equals("Y")) {
                b.board = 0b0;
                Board.moves = new LinkedList<>();
                continue;
            }
            else{
                System.out.println("Tschö!");
                break;
            }
        }
        */
    }








    public static void main(String[] args) {
        launch(args);
    }
}
