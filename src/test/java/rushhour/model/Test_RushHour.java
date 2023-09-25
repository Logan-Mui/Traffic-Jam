package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;



public class Test_RushHour {
    @Test
    public void testGetVehicles() throws IOException
    {
        //setup
        RushHour r = new RushHour("data/03_00.csv");
        //invoke
        Map<Character,Vehicle> a = r.getVehicles();
        Map<Character,Vehicle> e = new HashMap<Character,Vehicle>();
        e.put('O',new Vehicle('O',new Position(0,2),new Position(2,2)));
        e.put('A',new Vehicle('A',new Position(1,3),new Position(2,3)));
        e.put('R',new Vehicle('R',new Position(2,0),new Position(2,1)));
        String expected = e.toString();
        String actual = a.toString();
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testCanMoveTrue() throws IOException
    {
        //setup
        RushHour r = new RushHour("data/03_01.csv");
        //invoke
        boolean expected = r.canMove(new Move('A',Direction.RIGHT));
        boolean actual = true;
        //analyze
        assertEquals(expected,actual);
        
        
    }
    @Test
    public void testCanMoveFalseDirection() throws IOException
    {
        //setup
        RushHour r = new RushHour("data/03_00.csv");
        //invoke
        boolean expected = true;
        boolean actual = r.canMove(new Move('A',Direction.UP));
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testCanMoveFalseObstruction() throws IOException
    {
        //setup
        RushHour r = new RushHour("data/03_01.csv");
        //invoke
        boolean expected = r.canMove(new Move('O',Direction.RIGHT));
        boolean actual = false;
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testMoveVehicleHori() throws IOException, RushHourException
    {
        //setup
        RushHour r = new RushHour("data/03_00.csv");
        //invoke
        r.moveVehicle(new Move('A',Direction.RIGHT));
        Position expected = new Position(3,3);
        Position actual = r.getVehicles().get('A').getFront();
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testMoveVehicleVerti() throws IOException, RushHourException
    {
        //setup
        RushHour r = new RushHour("data/03_00.csv");
        //invoke
        r.moveVehicle(new Move('R',Direction.UP));
        Position actual = r.getVehicles().get('R').getFront();
        Position expected = new Position(2,2);
        //analyze
        assertEquals(expected,actual);
    }
    @Test
    public void testToString() throws IOException
    {
        //setup
        RushHour r = new RushHour("data/03_00.csv");
        //invoke
        String actual = r.toString();
        String expected = "[-][-][-][-][-][-]\n[-][-][-][-][-][-]\n[-][-][-][-][-][-]\n[R][R][O][A][-][-]\n[-][-][-][A][-][-]\n[-][-][O][-][-][-]\n";
        //assert
        assertEquals(expected,actual);
    }
}
