package rushhour.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import backtracker.Backtracker;
import backtracker.Configuration;

public class RushHourSolver implements Configuration<RushHourSolver>{

    private RushHour game;
    private Move[] moves;
    private static Set<RushHour> seenConfigs = new HashSet<>();

    public RushHourSolver(RushHour game, Move[] move)
    {
        this.game = game;
        this.moves = moves;
    }

    public RushHourSolver(RushHour game){
        this.game = new RushHour(game);
    }
    
    public RushHour getRushHour()
    {
        return game;
    }

    @Override
    public Collection<RushHourSolver> getSuccessors() {

        Collection<RushHourSolver> successors = new ArrayList<>();
        Collection<Move> moves = game.getPossibleMoves();

        for(Move move: moves){

            RushHour gameCopy = new RushHour(game);
            

            try {
                gameCopy.moveVehicle(move);
                if(!seenConfigs.contains(gameCopy)){
                    seenConfigs.add(gameCopy);
                }
            } catch (RushHourException e) {
                System.out.println("Invalid solver move");
            }

            successors.add(new RushHourSolver(gameCopy, move));

        }
        
        

        return successors;
        
    }

    @Override
    public boolean isValid(){
        //return true;

        return !seenConfigs.contains(game);
        
    }

    @Override
    public boolean isGoal(){

        return isValid() && game.isGameOver();

    }
    @Override
    public String toString()
    {
        return game.toString();
    }
    
    public static RushHourSolver solve(RushHour rushHour)
    {
        RushHourSolver solver = new RushHourSolver(rushHour);
        Backtracker<RushHourSolver> backtracker = new Backtracker<>(true);
        RushHourSolver solution = backtracker.solve(solver);
        return solution;
    }
}
