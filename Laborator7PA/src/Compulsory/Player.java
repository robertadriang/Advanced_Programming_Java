package Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private String name;
    private List<Token> playerTokens;
    private Board boardGame;

    public Player(String name, Board boardGame)
    {
        this.name = name;
        this.boardGame = boardGame;
        playerTokens = new ArrayList<>();
    }

    public void addToken(Token token)
    {
        playerTokens.add(token);
    }

    public List<Token> getPlayerTokens()
    {
        return playerTokens;
    }

    public String getName()
    {
        return name;
    }

    public void printTokens(){
        System.out.println("Player: "+name+": ");;
        for(var token : playerTokens){
            System.out.println(token);
        }
        System.out.println("---------------");
    }

    public void extractToken(){
        int pos=(int)(Math.random()* boardGame.getTokenNr());
        var token = boardGame.getToken(pos);
        this.addToken(token);
        boardGame.removeToken(pos);
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep((int) (Math.random() * 100));
            }catch(Exception e){
                System.out.println("Ops...");
            }
            synchronized (boardGame)
            {
                if (boardGame.isDone())
                    break;
                extractToken();
            }
        }
    }


}
