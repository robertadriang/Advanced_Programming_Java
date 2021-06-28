package Compulsory;

import java.util.Scanner;

/*
Concurrency
Write a program that simulates the following positional game between a given number of players (TSP Game).

At the beginning of the game the board contains a number of tokens, each token containing a distinct pair of numbers between 1 and n, and a specific positive value.
Each player extracts tokens successively from the board and must create with them a a closed sequence, that is t1=(i1,i2), t2=(i2,i3),...,tk=(ik,i1), where ti are tokens.
The value of a sequences may be evaluated in various modes: the sum of the token values, the number of tokens, etc. A special bonus may be given if the length of the sequence is n.
The game ends when all tokens have been removed from the board and each player receives a number of points equal to the the value of its largest sequence, or equal to the sum of its sequences, etc.
The players might take turns (or not...) and a time limit might be imposed (or not...).

The main specifications of the application are:

Compulsory (1p)

Create classes in order to model the problem. You may assume that all possible tokens are initially available, having random values.
Each player will have a name and they must perform in a concurrent manner, extracting repeatedly tokens from the board.
Simulate the game using a thread for each player.
Pay attention to the synchronization of the threads when extracting tokens from the board.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Nu stiu ce naiba sa fac");
        Scanner in=new Scanner(System.in);
        System.out.println("Number of players:");
        int nrPlayers=in.nextInt();

        var round=new Board();
        round.createBoard(nrPlayers);

        for(int i=1;i<=nrPlayers;++i){
            System.out.printf("Give player %d name: \n",i);
            String name=in.next();
            round.addPlayer(new Player(name,round));
        }

        round.startGame();
        while(!round.isDone()){
            Thread.sleep(100);
        }
        round.printPlayerTokens();
    }
}
