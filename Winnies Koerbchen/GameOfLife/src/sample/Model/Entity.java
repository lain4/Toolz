package sample.Model;

/**
 * Created by Leisterbäcker on 18.05.2017.
 */
public interface Entity {

    void setState(boolean state);
    boolean getState();
    void changeState();
    long countNeighbours();
    boolean evolve();
}
