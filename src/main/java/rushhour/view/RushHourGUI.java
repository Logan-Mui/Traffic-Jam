package rushhour.view;

import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
import javafx.stage.Stage;
import rushhour.model.Direction;
import rushhour.model.Move;
import rushhour.model.RushHour;
import rushhour.model.RushHourException;
import rushhour.model.RushHourObserver;
import rushhour.model.RushHourSolver;
//import rushhour.model.RushHourSolver;
import rushhour.model.Vehicle;
import rushhour.model.Position;

public class RushHourGUI extends Application implements RushHourObserver{
    
    // private static final String IMAGE_PATH = "file:media/";
    // private static final Image BLANK = new Image(IMAGE_PATH+"blank.png");
    // private static final Image RED = new Image(IMAGE_PATH+"red.png");
    // private static final Image ORANGE = new Image(IMAGE_PATH+"orange.png");
    // private static final Image BLUE = new Image(IMAGE_PATH+"blue.png");
    // private static final Image BROWN = new Image(IMAGE_PATH+"brown.png");
    // private static final Image CRIMSON = new Image(IMAGE_PATH+"crimson.png");
    // private static final Image DARKGREEN = new Image(IMAGE_PATH+"darkgreen.png");
    // private static final Image GOLD = new Image(IMAGE_PATH+"gold.png");
    // private static final Image LIGHTBLUE = new Image(IMAGE_PATH+"lightblue.png");
    // private static final Image LIME = new Image(IMAGE_PATH+"lime.png");
    // private static final Image PINK = new Image(IMAGE_PATH+"pink.png");
    // private static final Image PURPLE = new Image(IMAGE_PATH+"purple.png");
    // private static final Image WHITE = new Image(IMAGE_PATH+"white.png");
    // private static final Image YELLOW = new Image(IMAGE_PATH+"yellow.png");

    //private static final Font COURIER_18 = new Font("Courier new",18);

    private RushHour game;

   // private RushHourCLI rushHourCLI;

    //private Button[][] buttons = new Button[BOARD_DIM][BOARD_DIM];

    //private Label status;

    private String filename;

    private Direction dir;

    private GridPane gameBoard;

    //private Direction dir;

    private static final int BOARD_DIM = 6;

    private Label moves;

    private Label status;

    public GridPane getGameBoard(){
        return gameBoard;
    }

    public Background chooseback(char x){
        Background bg = new Background(new BackgroundFill(Color.valueOf("gray"), new CornerRadii(0.3), new Insets(0)));
        if(x == 'R'){
            bg = new Background(new BackgroundFill(Color.valueOf("red"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'O'){
            bg = new Background(new BackgroundFill(Color.valueOf("orange"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'A'){
            bg = new Background(new BackgroundFill(Color.valueOf("blue"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'B'){
            bg = new Background(new BackgroundFill(Color.valueOf("brown"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'C'){
            bg = new Background(new BackgroundFill(Color.valueOf("crimson"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'D'){
            bg = new Background(new BackgroundFill(Color.valueOf("darkgreen"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'E'){
            bg = new Background(new BackgroundFill(Color.valueOf("lime"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'F'){
            bg = new Background(new BackgroundFill(Color.valueOf("lightblue"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'G'){
            bg = new Background(new BackgroundFill(Color.valueOf("gold"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'H'){
            bg = new Background(new BackgroundFill(Color.valueOf("pink"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'I'){
            bg = new Background(new BackgroundFill(Color.valueOf("white"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'P'){
            bg = new Background(new BackgroundFill(Color.valueOf("purple"), new CornerRadii(0.3), new Insets(0)));
            
        }
        if(x == 'Q'){
            bg = new Background(new BackgroundFill(Color.valueOf("yellow"), new CornerRadii(0.3), new Insets(0)));
            
        }
        return bg;

    }

    @Override
    public void vehicleMoved(Vehicle vehicle) {

        char vSymbol = vehicle.getSymbol();
        Move move = new Move(vSymbol, dir);

        if(game.canMove(move)){

            try {
                game.moveVehicle(move);
                updateSquares(game);
            } catch (RushHourException e) {
                System.out.println("Invalid move: " + move);
            }
        }
        System.out.println(game);
        
    }

    public Button makeButton(char color, int row, int  col){

        Button button = new Button("    ");
        Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN));
        Background bg;
        
        
        switch(color){
            case 'R':
                bg = new Background(new BackgroundFill(Color.valueOf("red"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'O':
                bg = new Background(new BackgroundFill(Color.valueOf("orange"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'A':
                bg = new Background(new BackgroundFill(Color.valueOf("blue"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'B':
                bg = new Background(new BackgroundFill(Color.valueOf("brown"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'C':
                bg = new Background(new BackgroundFill(Color.valueOf("crimson"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'D':
                bg = new Background(new BackgroundFill(Color.valueOf("darkgreen"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'E':
                bg = new Background(new BackgroundFill(Color.valueOf("lime"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'F':
                bg = new Background(new BackgroundFill(Color.valueOf("lightblue"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'G':
                bg = new Background(new BackgroundFill(Color.valueOf("gold"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'H':
                bg = new Background(new BackgroundFill(Color.valueOf("pink"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'I':
                bg = new Background(new BackgroundFill(Color.valueOf("white"), new CornerRadii(0.3), new Insets(0)));
                break;
            case 'P':
                bg = new Background(new BackgroundFill(Color.valueOf("purple"), new CornerRadii(0.3), new Insets(0)));
                break;    
            case 'Q':
                bg = new Background(new BackgroundFill(Color.valueOf("yellow"), new CornerRadii(0.3), new Insets(0)));
                break;
            default:
                bg = new Background(new BackgroundFill(Color.valueOf("gray"), new CornerRadii(0.3), new Insets(0)));
        }  

        button.setBorder(border);
        button.setBackground(bg);
        button.setOnAction(e -> {

            if(color == '-'){
                
            }
            else
            {
                Vehicle vehicle = game.getVehicles().get(color);
                Position front = vehicle.getFront();
                Position back = vehicle.getBack();
                Position curr = new Position(row, col);
                

                if(vehicle.isHorizontal())
                {
                    if(curr.equals(front))
                    {
                        dir = Direction.RIGHT;
                    }
                    else if(curr.equals(back))
                    {
                        dir = Direction.LEFT;
                    }
                    else
                    {
                        System.out.println("no dir horiz");
                        System.out.println(front + " " + back);
                        System.out.println(curr);
                    }   
                }
                else if(vehicle.isVertical())
                {
                    if(curr.equals(front))
                    {
                        dir = Direction.UP;
                    }
                    else if(curr.equals(back))
                    {
                        dir = Direction.DOWN;
                    }else
                    {
                        System.out.println("no dir vert");
                        System.out.println(front + " " + back);
                        System.out.println(curr);
                    }
                }
                else
                {
                    System.out.println("no dir");
                }

                vehicleMoved(vehicle);
            }
            System.out.println("Button at: " + row + ", " + col + " pressed");

        });

        return button;
    }

    private HBox makeControlPannel(){

        HBox controls = new HBox();

        status = new Label("New Game\t");
        moves = new Label("\tMoves: " + game.getMoveCount() + "\t");
        Button hint = new Button("Hint");
        Button reset = new Button("Reset");
        Button solve = new Button("Solve!");

        hint.setOnAction(e -> {
            Move m = game.getHint();
            status.setText("Hint: "+m.toString());
        });

        reset.setOnAction(e->
        {
            try {
                game = new RushHour(filename);
                updateSquares(game);
            } catch (IOException e1) {
                System.out.println("Reset failed!");
                e1.printStackTrace();
            }
            status.setText("Game Reset!");
        });

        solve.setOnAction(e -> 
        {
            RushHourSolver solution = RushHourSolver.solve(game);
            if(solution!=null)
            {
                this.game = solution.getRushHour();
                
            }
            else
            {
                status.setText("No Solution Found!");
            }
        });

        controls.getChildren().addAll(status, moves, hint, reset,solve);
        return controls;
    }

    public void updateSquares(RushHour game){
        char[][] board = game.getBoard();


        for(int i=0; i<BOARD_DIM; i++){
            for(int k=0; k<BOARD_DIM; k++){

                //Vehicle v = game.getVehicles().get(board[k][i]);
                //Position currPos = new Position(k, i);

                gameBoard.add(makeButton(board[k][i], k, i), i, k);
            }
        }
        
        moves.setText("Moves: "+game.getMoveCount()/2+"\t");
        if(game.getVehicles().get('R').getFront().getCol()==5){
            status.setText("Solved!");
        }
    }
    


    
    @Override
    public void start(Stage stage) throws Exception {

        String file = "data/07_00.csv";
        this.filename = file;

        game = new RushHour(file);
        char[][] board = game.getBoard();

        GridPane grid = new GridPane();
        HBox controls = makeControlPannel();
        
        for(int i=0; i<BOARD_DIM; i++){
            for(int k=0; k<BOARD_DIM; k++){

                //Vehicle v = game.getVehicles().get(board[k][i]);
                //Position currPos = new Position(k, i);

                //buttons[k][i] = makeButton(board[k][i], k, i);
                grid.add(makeButton(board[k][i], k, i), i, k);
            }
        }

        gameBoard = grid;
        
        System.out.println(game);
        
        grid.add(controls, 0, BOARD_DIM, 6, 1);

        Scene scene = new Scene(grid);
        stage.setTitle("Rush Hour");
        stage.setScene(scene);
        stage.show();

    }

        public static void main(String[] args) {
        launch(args);
    }
}