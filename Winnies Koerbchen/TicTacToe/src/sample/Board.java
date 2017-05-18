package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;


/**
 * Created by Leisterbäcker on 03.05.2017.
 */
public class Board implements IBoard{

    public static LinkedList<Integer> moves;
    public int board, turn;

    public Board(){
        board = 0b0;
        moves = new LinkedList<>();
        turn = 1;
    }


    public void show(){
        for(int i = 0, j = 1; i <= 17;j++, i++){
            int mask = 1 << i;
            if((mask&board) !=0) System.out.print("1");
            else System.out.print("0");

            if(j%2==0) System.out.print(" ");
            if(j%6==0) System.out.println("");

        }
        System.out.println("------------------------------------BOARD-");
    }

    public Integer[] listMoves(){
        return new Integer[]{0,1,2,3,4,5,6,7,8};
    }

    @Override
    public void makeMove(int idx){
        board |= 1 << (turn < 1 ? idx*2+1 : idx*2);
        moves.add(idx);
        turn = -turn;
    }

    @Override
    public void undoMove(){
        board &= ~(11 << moves.getLast()*2);
        moves.remove(moves.getLast());
        turn = -turn;
    }

    @Override
    public boolean threeInARow(){
        if(Integer.bitCount(board) < 5) return false;
        if(Integer.bitCount(board) % 2 != 0)
            return IntStream.of(21,1344,86016,4161,16644,66576,65793,4368).anyMatch(e -> (board & e) == e );
        else
            return IntStream.of(42,2688,172032,8322,33288,133152,131586,8736).anyMatch(e -> (board & e) == e);
    }

    public boolean gameEnd(){return Integer.bitCount(board) == 9 || threeInARow();}

}


