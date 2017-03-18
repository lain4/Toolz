package beerchallenge;

import java.util.LinkedList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class GameState {
            private final static Game[] GAMES = new Game[7];
            private LinkedList<Team> teams;
            private Team team1, team2;
                       
            static Game game;
            
            GameState() {
                this.teams = null;
            }

            GameState(LinkedList<Team> teams, int t1, int t2) {
                this.teams = teams;
                this.team1 = teams.get(t1);
                this.team2 = teams.get(t2);
                game = new Pong(team1, team2);
            }
            
            GameState(LinkedList<Team> teams, int t1, int t2, boolean turn, int index, int sc1, int sc2) {
                this.teams = teams;
                this.team1 = teams.get(t1);
                this.team2 = teams.get(t2);
                game = GAMES[index];
                game.setTurn(turn);
                game.setScore1(sc1);
                game.setScore2(sc2);  
            }
            
            public final Team getTeam1() {
                return team1;
            }
            
            public final Team getTeam2() {
                return team2;
            }

}
