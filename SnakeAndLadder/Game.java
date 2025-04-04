package Questions.SnakeAndLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

enum Color {RED,GREEN,BLUE}
enum GameStatus{STARTED,NOT_STARTED,ENDED}

class Snake {
    int start;
    int end;
    Snake(int start,int end){
        this.start=start;
        this.end=end;
    }

    int getStart(){
        return this.start;
    }

    int getEnd(){
        return this.end;
    }
}

class Ladder{
    int start;
    int end;
    Ladder(int start,int end){
        this.start=start;
        this.end=end;
    }

    int getStart(){
        return this.start;
    }

    int getEnd(){
        return this.end;
    }

}

class Piece{
    int pos;
    Color color;
    Piece(int pos,Color color){
        this.pos=pos;
        this.color=color;
    }

    int getPos(){
        return pos;
    }

    Color getColor(){
        return color;
    }

    public void setPos(int finalPos) {
        this.pos=finalPos;
    }
}

/**
 * If we need to display board then this is required

class Spot{
    int position;
    ArrayList<Piece>pieces;

    Spot(int position){
        this.position=position;
        this.pieces=new ArrayList<>();
    }

    void addPiece(Piece p){
        pieces.add(p);
    }

    void removePiece(Piece p){
        pieces.remove(p);
    }

    ArrayList<Piece> getPieces(){
        return pieces;
    }
}
 **/


class Board{
    int size;
    HashMap<Integer,Integer>board;
    HashMap<Integer,Snake>snakes;
    HashMap<Integer,Ladder>ladders;

    Board(int size){
        this.size=size;
        this.board = new HashMap<>();
        this.ladders=new HashMap<>();
        this.snakes=new HashMap<>();
        int count=1;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                board.put(count,count);
                count++;
            }
        }
    }

    void addLadder(int start,int end){
        Ladder ladder = new Ladder(start,end);
        ladders.put(start,ladder);
    }

    void addSnake(int start,int end){
        Snake snake=new Snake(start,end);
        snakes.put(start,snake);

    }

    boolean isSnake(int pos){
        return snakes.get(pos) != null;
    }
    boolean isLadder(int pos){
        return ladders.get(pos)!=null;
    }

    Ladder getLadder(int pos){
        return ladders.get(pos);
    }

    Snake getSnake(int pos){
        return snakes.get(pos);
    }

    /**
     * This code can be avoided because here there is no need to display board
    void addPieceAtPos(int pos,Piece p){
         board.get(pos).addPiece(p);
    }

    void removePieceAtPos(int pos,Piece p){
         board.get(pos).removePiece(p);
    }

    void displayBoard(){

        int count=1;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(isLadder(count)){
                    System.out.println("L");
                }
                else if(isSnake(count))
                {
                    System.out.println("S");
                }
                else {
                    int total=board.get(i).getPieces().size();
                    if(total>0) {
                        System.out.println(size + "P");
                    }else{
                        System.out.println(count);
                    }
                }
                count++;
            }
        }
    }**/
}

class Dice{
    int size=6;
    static Dice instance=null;

    private Dice(){};
    public static Dice getInstance(){
        if(instance==null)
        {
            instance=new Dice();
        }
        return instance;
    }

    int roll(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}

class Player{
    String name;
    Piece p;

    Player(String name, Piece p){
        this.p=p;
        this.name=name;
    }

    Piece getPiece(){
        return this.p;
    }
}

class SnakeAndLadderGame{
    Board board;
    Dice dice;
    ArrayList<Player>players;
    int destination;

    void initializeGame(){
        this.dice=Dice.getInstance();
        this.players=new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("What size board you want to add");
        int size=scanner.nextInt();
        this.board=new Board(size);
        this.destination = size*size;

        System.out.println("How many ladders you want to add");
        int totalLadders =scanner.nextInt();

        for(int i=0;i<totalLadders;i++)
        {
            System.out.println("Enter start position");
            int start=scanner.nextInt();
            System.out.println("Enter end position");
            int end=scanner.nextInt();

            board.addLadder(start,end);
        }

        System.out.println("How many snakes you want to add");
        int totalSnakes =scanner.nextInt();

        for(int i=0;i<totalSnakes;i++)
        {
            System.out.println("Enter start position");
            int start=scanner.nextInt();
            System.out.println("Enter end position");
            int end=scanner.nextInt();

            board.addSnake(start,end);
        }

    }

    void startGame(){
        System.out.println("How many player will play it should be less then 5");
        Scanner scanner = new Scanner(System.in);
        int totalPlayers = scanner.nextInt();
        Color[] colors=Color.values();
        for(int i=0;i<totalPlayers && i<colors.length;i++) {
            System.out.println("Enter player name");
            String name=scanner.next();
            players.add(new Player(name,new Piece(0,colors[i])));
        }

        GameStatus gameStatus=GameStatus.STARTED ;
        int player_chance=0;
        Player currPlayer = players.get(0);

        while(gameStatus!=GameStatus.ENDED){
            System.out.println(currPlayer.name+" "+"chance has come");
            int number= dice.roll();
            System.out.println(number);

            processMove(currPlayer,number);

            if(checkWin(currPlayer))
            {
                gameStatus = GameStatus.ENDED;
                System.out.println(currPlayer.name +" won the game");
            }

            player_chance++;
            player_chance = player_chance%totalPlayers;
            currPlayer = players.get(player_chance);

        }
    }

    void processMove(Player p,int number){
        int curr_pos=p.getPiece().getPos();
        int final_pos=curr_pos+number;

        if(board.isSnake(final_pos))
        {
            Snake snake=board.getSnake(final_pos);
            if(snake!=null){
                final_pos= snake.getEnd();
            }
        }
        else if(board.isLadder(final_pos))
        {
            Ladder ladder=board.getLadder(final_pos);
            if(ladder!=null){
                final_pos= ladder.getEnd();
            }
        }

        if(final_pos>destination)
        {
            final_pos=destination;
        }

        //board.removePieceAtPos(curr_pos,p.getPiece());
        //board.addPieceAtPos(final_pos,p.getPiece());
        p.getPiece().setPos(final_pos);
    }

    boolean checkWin(Player p){
        return p.getPiece().getPos() == destination;
    }

}


public class Game {
    public static void main(String[] args){
        SnakeAndLadderGame game=new SnakeAndLadderGame();
        game.initializeGame();
        game.startGame();
    }
}
