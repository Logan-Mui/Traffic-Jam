package rushhour.view;


import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

import rushhour.model.Direction;
import rushhour.model.Move;
import rushhour.model.RushHour;
import rushhour.model.RushHourException;



public class RushHourCLI{

    //private static final String ROW_HEADER = "  0   1   2   3   4   5\n";

    private static String filename;
    private static RushHour game;
    public static void main(String[] args) throws IOException, RushHourException{
        try(Scanner scanner = new Scanner(System.in)){
            //boolean sentinel = true;
            
            System.out.println("Enter a filename: ");
            filename = scanner.nextLine();

            game = new RushHour(filename);

            while(!game.isGameOver()){
                System.out.println(game);
                System.out.println(">> ");
                String[] command = scanner.nextLine().split(" ");
                switch(command[0].toLowerCase()){
                    case "quit":
                        System.out.println("Goodbye!");
                        break;
                    case "reset":
                        System.out.println("Are you sure? y/n: ");
                        String res = scanner.nextLine();
                        if(res == "y"){
                            reset();
                            
                        } else {
                            continue;
                        }
                    case "hint":
                        Random random = new Random();
                        ArrayList<Move> hint = (ArrayList<Move>) game.getPossibleMoves();
                        

                        System.out.println("Hint: "+ hint.get(random.nextInt(0,hint.size()-1)).toString());
                        break;
                    case "help":
                        System.out.println("Avalible Commands: ");
                        System.out.println("quit");
                        System.out.println("reset");
                        System.out.println("hint");
                        System.out.println("help");
                        System.out.println("<symbol> <direction>");
                        break;
                    default:
                        if(command[0].length() == 1){
                            Direction dir;
                            char symbol = command[0].charAt(0);
                            switch (command[1].toUpperCase()) {
                                case "UP":
                                    dir = Direction.UP;
                                    break;
                                case "DOWN":
                                    dir = Direction.DOWN;
                                    break;
                                case "LEFT":
                                    dir = Direction.LEFT;
                                    break;
                                case "RIGHT":
                                    dir = Direction.RIGHT;
                                    break;
                                default:
                                    dir = null;
                                    break;

                            
                            }
                            Move move = new Move(symbol,dir);
                            game.moveVehicle(move);
                        }
                        
                    
                }
            }
            System.out.println("Game Over!");
        }
    }

    public static void reset() throws IOException{
        game = new RushHour(filename);
    }

}