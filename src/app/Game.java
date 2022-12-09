package app;

import exeption.NotAdaptedFunction;
import game.Plateau;
import player.Player;

import java.util.Random;

public class Game {
    private final Player p1, p2;
    private final Plateau plateau;

    private boolean isPlayerOneTurn = true;
//    private final IIHM ihm;
//
//    public Game( int size , IIHM human_interface){
//        this.p1 = new Player(human_interface.recuperer_type_de_joueur()) ;
//        this.p2 = new Player(human_interface.recuperer_type_de_joueur());
//        this.ihm = human_interface;
//        this.plateau = new Plateau(size);
//    }

   //pour simplifier nos test
    public Game(Player p1, Player p2, int size){
        this.p1 = p1 ;
        this.p2 = p2;
        this.plateau = new Plateau(size);
    }

    public void setPlayersChoice(int choice){
        if (this.isPlayerOneTurn){
            p1.setChoice(p1.getIsIA() ? gameChoice() : choice);
            this.plateau.play(p1.getChoice(), p1.getPawnColor());
            this.isPlayerOneTurn = false;
        }else{
            p2.setChoice(p2.getIsIA() ? gameChoice() : choice);
            this.plateau.play(p2.getChoice(), p2.getPawnColor());
            this.isPlayerOneTurn = true;
        }
    }
    public void setPlayersChoice(){
        if (!this.p1.getIsIA() && !this.p2.getIsIA()) throw new NotAdaptedFunction();
        setPlayersChoice(0);
    }

    public int nbCases(){
        return this.plateau.taille() * this.plateau.taille();
    }

    public boolean isFull(){
        return this.plateau.isFull();
    }

    private int gameChoice(){
        Random r = new Random();
        int randCase = r.nextInt(this.plateau.getNbOfUsableCase()) + 1;
        int ctp = 0;
        for (int i = 0; i < this.plateau.taille(); ++i)
            for (int j = 0; j < this.plateau.taille(); ++j){
                if (this.plateau.isEmpty(i, j)) ++ctp;
                if (ctp == randCase)
                    return (i * this.plateau.taille() + j);
            }
        return -1;
    }

    @Override
    public String toString(){
        return this.plateau.toString();
    }
}
