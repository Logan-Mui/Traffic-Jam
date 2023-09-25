package rushhour.model;



public class Position {
    private static final int MAX_VALUE = 5;
    private int row;
    private int col;

    public Position(int row, int col) 
    {
        try {
            if(row<0 || row>MAX_VALUE){
                throw new RushHourException("Row out of bounds");
            }
            if(col<0 || col>MAX_VALUE){
                throw new RushHourException("Col out of bounds");
            }
            this.row = row;
            this.col = col;
        } catch (Exception rushHourException) {
            System.out.println(rushHourException);
        }
    }
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
    public void moveHori(Direction dir) 
    {
        this.col += dir.getMovement();
    }
    public void moveVert(Direction dir) 
    {
        this.row += dir.getMovement();
    }

    @Override
    public int hashCode()
    {
        return this.row+this.col * this.row+this.col;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Position)
        {
            Position other = (Position) o;
            return this.row == other.getRow() && this.col == other.getCol();
        }
        return false;
    }
    @Override
    public String toString()
    {
        return "Row: "+row+" Column: "+col;
    }
      
    
}
