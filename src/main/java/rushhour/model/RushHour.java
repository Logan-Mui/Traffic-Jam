package rushhour.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;


//import backtracker.Configuration;  For part 2

public class RushHour {
    public final int BOARD_DIM = 6;
    public final char RED_SYMBOL = 'R';
    public final char MT_SYMBOL = '-';
    public final Position EXIT_POS = new Position(2,5);
    public char[][] board;
    public Map<Character, Vehicle> vehicles;
    private int moveCount=0;
    private RushHourObserver observer;
    private String filename;

    public RushHour(String filename) throws IOException{
        this.filename=filename;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        vehicles = new HashMap<>();
        board = new char[6][6];

        String line = reader.readLine();


        for(int i=0; i<BOARD_DIM; i++){
            for(int k=0; k<BOARD_DIM; k++){   
                board[i][k] = MT_SYMBOL;      
            }
        }

        while(line != null){

            String[] vals = line.split(",");

            int backRow = Integer.parseInt(vals[1]);
            int backCol = Integer.parseInt(vals[2]);
            int frontRow = Integer.parseInt(vals[3]);
            int frontCol = Integer.parseInt(vals[4]);

            Position back = new Position(backRow, backCol);
            Position front = new Position(frontRow, frontCol);
            char symbol = vals[0].charAt(0);
            Vehicle veh = new Vehicle(symbol, back, front);
            vehicles.put(symbol, veh);

            board[frontRow][frontCol] = symbol;
            board[backRow][backCol] = symbol;

            if(veh.getMiddle() != null)
            {
                board[veh.getMiddle().getRow()][veh.getMiddle().getCol()] = symbol;
            }

            line = reader.readLine();
        }

        reader.close();
    }

    public RushHour (RushHour other)
    {
        this.board = new char[BOARD_DIM][BOARD_DIM];
        char [][] b = other.getBoard();
        for(int i=0; i<BOARD_DIM;i++){
            for(int j=0; j<BOARD_DIM;j++){
                board[i][j] = b[i][j];
            }
        }
        this.vehicles = new HashMap<>();
        for(char c : other.getVehicles().keySet()){
            Vehicle v =new Vehicle(c, other.getVehicles().get(c).getBack(), other.getVehicles().get(c).getFront());
            vehicles.put(c,v);
        }
        this.moveCount = other.getMoveCount();
    }



    public void registerObserver(RushHourObserver observer){
        this.observer = observer;
    }

    private void notifyObserver(Vehicle vehicle){
        observer.vehicleMoved(vehicle);
    }



    public Map<Character, Vehicle> getVehicles(){return vehicles;}


    public boolean canMove(Move move){

        char symbol = move.getSymbol();
        Direction dir = move.getDirection();
        Vehicle v = vehicles.get(symbol);
        
        switch(dir){

            case UP:   

                if(v.isHorizontal()){
                    return false;
                }else if(v.getFront().getRow()+dir.getMovement() < 0){
                    return false;
                }else if(v.getFront().getRow()+dir.getMovement() >= BOARD_DIM){
                    return false;
                }else if(board[v.getFront().getRow()+dir.getMovement()][v.getFront().getCol()] != MT_SYMBOL){
                    return false;
                }else{
                    return true;
                }

            case DOWN:
                
                if(v.isHorizontal()){
                    return false;
                }else if(v.getBack().getRow()+dir.getMovement() >= BOARD_DIM){
                    return false;
                }else if(v.getBack().getRow()+dir.getMovement() < 0){
                    return false;
                }else if(board[v.getBack().getRow()+dir.getMovement()][v.getBack().getCol()] != MT_SYMBOL){
                    return false;
                }else{
                    return true;
                }

            case RIGHT:

                if(v.isVertical()){
                    return false;
                }else if(v.getFront().getCol()+dir.getMovement() >= BOARD_DIM){
                    return false;
                }else if(v.getFront().getCol()+dir.getMovement() < 0){
                    return false;
                }else if(board[v.getFront().getRow()][v.getFront().getCol()+dir.getMovement()] != MT_SYMBOL){
                    return false;
                }else{
                    return true;
                }

            case LEFT:

                if(v.isVertical()){
                    return false;
                }else if(v.getBack().getCol()+dir.getMovement() < 0){
                    return false;
                }else if(v.getBack().getCol()+dir.getMovement() >= BOARD_DIM){
                    return false;
                }else if(board[v.getBack().getRow()][v.getBack().getCol()+dir.getMovement()] != MT_SYMBOL){
                    return false;
                }else{
                    return true;
                }
            
            default:
                return false;
        }
    }

    public void moveVehicle(Move move) throws RushHourException{

        char symbol = move.getSymbol();
        Direction dir = move.getDirection();
        Vehicle v = vehicles.get(symbol);
        
        if(v != null && canMove(move)){
            System.out.println("Moved: " + move);
            v.move(dir);
            moveCount++;
            
            //notifyObserver(v);
            registerObserver(new RushHourObserver() {

                @Override
                public void vehicleMoved(Vehicle vehicle) {
                    System.out.println("Observed");
                }
                
            });
            notifyObserver(v);
            updateBoard();
            moveCount++;
            System.out.println(toString());
        } else {
            throw new RushHourException("Invalid Move: " + move);
        }
    }

    public void updateBoard(){

        for(int i=0; i<BOARD_DIM; i++){
            for(int k=0; k<BOARD_DIM; k++){   
                board[i][k] = MT_SYMBOL;      
            }
        }

        for(char symbol: vehicles.keySet()){

            Vehicle v = vehicles.get(symbol);
            Position front = v.getFront();
            Position back = v.getBack();


            board[front.getRow()][front.getCol()] = symbol;
            board[back.getRow()][back.getCol()] = symbol;

            if(v.getMiddle() != null)
            {
                board[v.getMiddle().getRow()][v.getMiddle().getCol()] = symbol;
            }

        }
    }





    

    public boolean isGameOver(){
        Vehicle red = vehicles.get('R');
        Position exit = new Position(red.getBack().getRow(), BOARD_DIM-1);

        return red.getFront()==exit || red.getBack() == exit;
    }

    public Collection<Move> getPossibleMoves(){
        
        Collection<Move> possibleMoves = new ArrayList<>();
        Set<Character> keyset = vehicles.keySet();

        for(char symbol : keyset){
            Vehicle v = vehicles.get(symbol);
            Collection<Move> moves = v.getMoves();
            for(Move m : moves){
                if(canMove(m)){
                    possibleMoves.add(m);
                }
            }       
        
        }


        return possibleMoves;
    }

    public Move getHint(){
        Random random = new Random();
        ArrayList<Move> hintlist = (ArrayList<Move>) getPossibleMoves();
        Move move = hintlist.get(random.nextInt(0,hintlist.size()-1));
        return move;
    }

    public int getMoveCount(){
        return moveCount;
    }

    public String getFilename(){
        return filename;
    }

    public char[][] getBoard(){
        return board;
    }

    @Override
    public int hashCode(){
        return board.hashCode();
    }

    @Override
    public boolean equals(Object other){

        if(other instanceof RushHour){
            RushHour o = (RushHour)other;
            return board.equals(o.board);
        }else
        {
            return false;
        }
    }
    
    @Override
    public String toString()
    {
        String boardString = "";
        for(int i=0; i<BOARD_DIM; i++){
            for(int k=0; k<BOARD_DIM; k++){
                boardString += "[" + board[i][k] + "]";
            }
            boardString += "\n";
        }
        return boardString;
    }

    public static void main(String[] args) throws Exception {

        RushHour game = new RushHour("data/07_00.csv");
        System.out.println(game);
        
        Vehicle red = game.getVehicles().get('R');
        System.out.println(red);

        Move move = new Move('R', Direction.LEFT);
        game.moveVehicle(move);
        game.moveVehicle(move);
        
        System.out.println(game);
        System.out.println(red);

         //System.out.println(game);
        
    }
}