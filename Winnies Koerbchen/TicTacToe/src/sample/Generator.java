package sample;

/**
 * Created by Leisterbäcker on 11.05.2017.
 */
public class Generator {

    public static int positions, wins;

    public static void generateMoves(Board board){
        for(Integer move: board.listMoves()){
            board.makeMove(move);
            positions+=1;
            if(board.threeInARow()){
                wins+=1;
                board.undoMove();
                continue;
            }
            generateMoves(board);
            board.undoMove();
        }
    }
}
