package sample;

/**
 * Created by Leisterbäcker on 03.05.2017.
 */
public interface IBoard {

    void makeMove(int pos);
    void undoMove();
    boolean threeInARow();
}
