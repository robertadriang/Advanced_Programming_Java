package Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Token> tokenList;
    List<Player> players;
    Board(){
        tokenList=new ArrayList<>();
        players=new ArrayList<>();
    }

    public void startGame(){
        for(var player:players){
            new Thread(player).start();
        }
    }

    public void addToken(Token token)
    {
        tokenList.add(token);
    }

    public void removeToken(int pos)
    {
        tokenList.remove(pos);
    }

    public Token getToken(int pos){
        return tokenList.get(pos);
    }

    public int getTokenNr(){
        return tokenList.size();
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public void createBoard(int n){
        for(int i=0;i<n;++i){
            for(int j=i+1;j<n;++j){
                addToken(new Token(i,j,(int)(Math.random()*20)));
                addToken(new Token(j,i,(int)(Math.random()*20)));
            }
        }
    }

    public void printPlayerTokens()
    {
        for (var player : players)
            player.printTokens();
    }

    public boolean isDone(){
        return (tokenList.size()==0);
    }

}
