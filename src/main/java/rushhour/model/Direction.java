package rushhour.model;

public enum Direction {
    
    UP(1),
    RIGHT(1),
    DOWN(-1),
    LEFT(-1);

    private int movement;
    private Direction(int movement){
        this.movement = movement;
    }

    public int getMovement(){return movement;}

    @Override
    public String toString()
    {
        return name();
    }
}
