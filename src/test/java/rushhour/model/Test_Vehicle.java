package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class Test_Vehicle {
    @Test
    public void testIsVerticleTrue() throws RushHourException
    {
        //setup
        Vehicle v = new Vehicle('V', new Position(2,3), new Position(2,5));
        //invoke
        boolean expected = v.isVertical();
        boolean actual = true;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testIsVerticleFalse() throws RushHourException
    {
        //setup
        Vehicle v = new Vehicle('V', new Position(2,3), new Position(4,3));
        //invoke
        boolean expected = v.isVertical();
        boolean actual = false;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testIsHorizontalTrue() throws RushHourException
    {
        //setup
        Vehicle v = new Vehicle('V', new Position(2,3), new Position(4,3));
        //invoke
        boolean expected = v.isHorizontal();
        boolean actual = true;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testIsHorizontalFalse() throws RushHourException
    {
        //setup
        Vehicle v = new Vehicle('V', new Position(2,3), new Position(2,5));
        //invoke
        boolean expected = v.isHorizontal();
        boolean actual = false;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testMoveVehicleVerti() throws RushHourException
    {
        //setup
        Vehicle v = new Vehicle('V', new Position(2,3), new Position(2,5));
        //invoke
        v.move(Direction.UP);
        Position expected = new Position(2,6);
        Position actual = v.getFront();
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testMoveVehicleHori() throws RushHourException
    {
        //setup
        Vehicle v = new Vehicle('V', new Position(2,3), new Position(3,3));
        //invoke
        v.move(Direction.RIGHT);
        Position expected = new Position(4,3);
        Position actual = v.getFront();
        //analyze
        assertEquals(expected, actual);
    }
    @Test
    public void testGetMoves()
    {
        //setup
        Vehicle v = new Vehicle('V', new Position(2,3), new Position(3,3));
        //invoke
        ArrayList<Move> expected = new ArrayList<>();
        expected.add(new Move('V',Direction.LEFT));
        expected.add(new Move('V',Direction.RIGHT));
        ArrayList<Move> actual = (ArrayList<Move>) v.getMoves();
        //analyze
        assertEquals(expected, actual);
    }
}
