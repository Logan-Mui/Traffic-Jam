package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Test_Move {
    //
    @Test
    public void testMove()
    {
        //Setup
            Move moveup= new Move('U',Direction.UP);
        //Invoke
            Direction expected = Direction.UP;
            Direction actual = moveup.getDirection();
        //Analyze
            assertEquals(expected,actual);
    }
    @Test
    public void testSymbols()
    {
        //Setup
            Move moveup= new Move('L',Direction.LEFT);
        //Invoke
            char expected = 'L';
            char actual = moveup.getSymbol();
        //Analyze
            assertEquals(expected,actual);
    }
    @Test
    public void testToString()
    {
        //Setup
            Move moveup= new Move('R',Direction.RIGHT);
        //Invoke
            String expected = "R, RIGHT";
            String actual = moveup.toString();
        //Analyze
            assertEquals(expected,actual);
    }
}
