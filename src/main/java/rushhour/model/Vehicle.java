package rushhour.model;

import java.util.ArrayList;
import java.util.Collection;

public class Vehicle {
    private char symbol;
    private Position back;
    private Position front;
    private int size;

    public Vehicle(char symbol, Position back, Position front)
    {
        this.symbol = symbol;
        this.back = back;
        this.front = front;
        size = Math.abs((back.getCol()-front.getCol())+(back.getRow()-front.getRow()))+1;
    }
    public  Vehicle(Vehicle other){
        this.symbol = other.symbol;
        this.back = other.back;
        this.front = other.front;
        this.size = other.getSize();
    }

    public char getSymbol() {
        return symbol;
    }
    
    public Position getBack() {
        return back;
    }
    
    public Position getFront() {
        return front;
    }

    public Position getMiddle(){
        Position middle;

        if(size == 3){
            if(isHorizontal()){

                int mid = (front.getCol()+back.getCol())/2;
                middle = new Position(front.getRow(), mid);

            }else if(isVertical()){

                int mid = (front.getRow()+back.getRow())/2;
                middle = new Position(mid, front.getCol());
            }else{
                middle = null;
            }
        }else{
            middle = null;
        }


        return middle;
    }

    public int getSize(){
        return size;
    }

    public boolean isHorizontal(){
        return front.getRow() == back.getRow();
    }

    public boolean isVertical(){
        return front.getCol() == back.getCol();
    }

    public void move(Direction d) throws RushHourException
    {
        if(d == Direction.UP || d == Direction.DOWN)
        {
            back.moveVert(d);
            front.moveVert(d);
        } else {
            back.moveHori(d);
            front.moveHori(d);
        }

    }

    public Collection<Move> getMoves(){
        Collection<Move> possible = new ArrayList<>();

        if(isHorizontal()){
            possible.add(new Move(symbol,Direction.LEFT));
            possible.add(new Move(symbol,Direction.RIGHT));
        } else if(isVertical()){
            possible.add(new Move(symbol,Direction.UP));
            possible.add(new Move(symbol,Direction.DOWN));
        }
        return possible;
    }

    @Override
    public int hashCode(){
        return Character.toString(symbol).hashCode();
    }

    @Override 
    public String toString(){
        return Character.toString(symbol) + " size: " + size + "\n\tFront: " + front + "\n\tBack: " + back;
    }
}

