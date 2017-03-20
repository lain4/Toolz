package beerchallenge;

import java.util.LinkedList;
import javafx.scene.Parent;


public class GameState {
            private LinkedList<Team> teams = new LinkedList<>();
            private Team team1, team2;
                       
            private Game game;
            
            GameState() {
                this.team1 = null;
                this.team2 = null;
                this.game = null;
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
                game = getGame(index);
                game.setTurn(turn);
                game.setScore1(sc1);
                game.setScore2(sc2);  
            }
            
            final boolean teamsChosen() {
                return (team1 != null && team2 != null);
            }
            
            final LinkedList<Team> getTeams() {
                return teams;
            }
            
            final Game currentGame() {
                return game;
            }
            
            private Game getGame(int index) {            
                switch(index) {
                    case 0:
                        game = new Pong(team1, team2);
                        return game;
                    default:
                        return new Pong(team1, team2);
                }
            }
            
            final Parent showGame(int index) {
                return getGame(index).showContent();
            }
            
            final void setTeam1(int index) {
                this.team1  = teams.get(index);
            }
            
            final void setTeam2(int index) {
                this.team2 = teams.get(index);
            }
            
            final Team getTeam1() {
                return team1;
            }
            
            final void addTeam(Team team) {
                teams.add(team);
            }
            
            final Team getTeam2() {
                return team2;
            }

}
