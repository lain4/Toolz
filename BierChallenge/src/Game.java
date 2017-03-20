package beerchallenge;

import java.util.LinkedList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;

abstract class Game {
            private boolean playable, turn;
            private final IntegerProperty score1 = new SimpleIntegerProperty();
            private final IntegerProperty score2 = new SimpleIntegerProperty();
            private LinkedList<Player> team1 = new LinkedList<>();
            private LinkedList<Player> team2 = new LinkedList<>();
            private Team t1, t2;
            Type type = null;

            enum Type {
                PONG,
                STEMMEN,
                QUIZ,
                KLIMPERN,
                STAFFEL,
                EXEN;
                final static Type getType(int index) {
                    Type type = null;
                    for(Type t : Type.values()) {
                        if(t.ordinal() == index)
                            type = t;
                    }
                    return type;
                }
            }

            abstract Parent showContent();
            abstract void run();

            Game(Team first, Team sec) {
                this.t1 = first;
                this.t2 = sec;
                this.playable = true;
                this.turn = false;
                team1 = first.getMates();
                team2 = sec.getMates();
            }

            public final Team getT1() {
                return t1;
            }

            public final Team getT2() {
                return t2;
            }

            public final LinkedList<Player> getTeam1() {
                return team1;
            }

            public final LinkedList<Player> getTeam2() {
                return team2;
            }

            public final void setTurn(boolean turn) {
                this.turn = turn;
            }

            public final void setScore1(int x) {
                score1.set(score1.getValue() + x);
            } 

            public final void setScore2(int x) {
                score2.set(score2.getValue() + x);
            } 

            public final IntegerProperty getScore1() {
                return score1;
            }

            public final IntegerProperty getScore2() {
                return score2;
            }

            public final boolean isPlayable() {
                return playable;
            }

            public final boolean getTurn() {
                return turn;
            }

            public final void endTurn() {
                this.turn = !turn;
            }
 }
