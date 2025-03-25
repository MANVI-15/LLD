package Questions.TicTacToe;

import java.util.ArrayList;
import java.util.Scanner;

enum Piece {X,O};
enum GameStatus{NOT_STARTED,STARTED,WIN,DRAW}

class Board
{
    private int size;
    ArrayList<ArrayList<Piece>>grid=new ArrayList<>();
    Board(int size)
    {
        this.size=size;
        for(int i=0;i<size;i++)
        {
            ArrayList<Piece> row=new ArrayList<>(size);

            for(int j=0;j<size;j++)
            {
                row.add(null);
            }
            grid.add(row);
        }

    }

    void addPiece(Piece type,int i,int j)
    {
        grid.get(i).set(j,type);
    }

    void display()
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(grid.get(i).get(j)==null)
                {
                    System.out.print("null");
                }
                else {
                    System.out.print(grid.get(i).get(j) == Piece.O ? 'O' : 'X');
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    boolean isEmpty()
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(grid.get(i).get(j)==null)
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isValid(int row,int col)
    {
        if(row>=0 && row<size && col>=0 && col<size && grid.get(row).get(col)==null)
        {
            return true;
        }
        return false;
    }

}
class Player{
    String name;
    Piece piece;

    Player(String name,Piece p)
    {
        this.name=name;
        this.piece=p;
    }

    String getName()
    {
        return this.name;
    }

    Piece getPieceType(){
        return this.piece;
    }


}
class Game{
    Board board = new Board(3);
    static Game game=null;
    Player p1;
    Player p2;
    GameStatus status=GameStatus.NOT_STARTED;

    private Game(Player p1,Player p2)
    {
        this.p1=p1;
        this.p2=p2;
    }
    public static Game getGameInstance(Player p1,Player p2)
    {
        if(game==null)
        {
            game=new Game(p1,p2);
        }
        return game;
    }

    void startGame(){
        status = GameStatus.STARTED;
        System.out.println("Game started");
        Player curr=p1;
        Scanner scanner=new Scanner(System.in);
        while(board.isEmpty() && status!= GameStatus.WIN)
        {
            System.out.println("Chance of player"+curr.getName());
            System.out.println("Enter row and col");
            int row=scanner.nextInt();
            int col=scanner.nextInt();

            if(board.isValid(row,col))
            {
                board.addPiece(curr.getPieceType(),row,col);
                if(checkIfWin())
                {
                   System.out.println(curr.getName()+ "Won the game");
                   board.display();
                   status=GameStatus.WIN;
                }

            }
            else {
                System.out.println("Please enter the valid entry");
                continue;
            }

            curr=curr.equals(p1)?p2:p1;
        }

        if(status!=GameStatus.WIN)
        {
            System.out.println("Game is draw");
            status=GameStatus.DRAW;
        }
    }

    private boolean checkRowWise(Piece type)
    {
        boolean find=true;
        for(int i=0;i<board.grid.size();i++){
            find=true;
            for(int j=0;j<board.grid.get(i).size();j++)
            {
                if(board.grid.get(i).get(j)!=type)
                {
                    find=false;
                   break;
                }
            }
            if(find)
            {
                return true;
            }
        }

        return false;

    }

    private boolean checkColWise(Piece type)
    {
        boolean find=true;
        for(int i=0;i<board.grid.size();i++){
            find=true;
            for(int j=0;j<board.grid.get(i).size();j++)
            {
                if(board.grid.get(j).get(i)!=type)
                {
                    find=false;
                    break;
                }
            }
            if(find)
            {
                return true;
            }
        }

        return false;

    }


    boolean checkIfWin(){

        // add logic for  diagonal as well
        if(checkRowWise(Piece.O) || checkColWise(Piece.O) || checkRowWise(Piece.X) || checkColWise(Piece.X))
        {
            return true;
        }
        return false;
    }

}


public class TicTacToe {

    public static void main(String[] args)
    {
        Player p1=new Player("Manvi",Piece.O);
        Player p2=new Player("Janvi",Piece.X);
        Game game= Game.getGameInstance(p1,p2);

        game.startGame();
    }
}
