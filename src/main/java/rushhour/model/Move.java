package rushhour.model;

public class Move{
    private char symbol;
    private Direction dir;

    public Move(char symbol, Direction dir){
        this.symbol = symbol;
        this.dir = dir;
    }

    public char getSymbol(){return symbol;}

    public Direction getDirection(){return dir;}

    public boolean isOpposing(Move other)
    {
        if(this.symbol == other.getSymbol())
        {
            if(this.dir == Direction.LEFT && other.getDirection() == Direction.RIGHT)
            {
                return true;
            }
            else if(this.dir == Direction.RIGHT && other.getDirection() == Direction.LEFT)
            {
                return true;
            }
            else if(this.dir == Direction.UP && other.getDirection() == Direction.DOWN)
            {
                return true;
            }
            else if(this.dir == Direction.DOWN && other.getDirection() == Direction.UP)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    @Override
    public String toString(){return symbol+ ", "+dir;}
}