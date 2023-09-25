package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class Test_Position {
    @Test
    public void testGetters()
    {
        //setup
        Position p = new Position(2,3);
        //invoke
        int expected = p.getCol();
        int actual = 3;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testMove()
    {
        //setup
        Position p = new Position(2,3);
        //invoke
        p.moveHori(Direction.RIGHT);
        int expected = p.getCol();
        int actual = 4;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testHash()
    {
        //setup
        Position p = new Position(2,3);
        //invoke
        int expected = 11;
        int actual = p.hashCode();
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testEqualsTrue()
    {
        //setup
        Position p = new Position(2,3);
        Position other = new Position(2,3);
        //invoke
        boolean expected = p.equals(other);
        boolean actual = true;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testEqualsFalse()
    {
        //setup
        Position p = new Position(2,3);
        Position other = new Position(3,3);
        //invoke
        boolean expected = p.equals(other);
        boolean actual = false;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testToString()
    {
        //setup
        Position p = new Position(2,3);
        //invoke
        String expected = p.toString();
        String actual = "Row: 2 Column: 3";
        //analyze
        assertEquals(expected,actual);
    }
}
