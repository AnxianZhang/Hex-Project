package app;

import IHM.IIHM;
import exeption.NotAdaptedFunction;
import game.Plateau;
import game.Stat;
import player.Player;

import java.util.Random;

public class Game {
    private final Player p1, p2;
    private final Plateau plateau;

    private boolean isPlayerOneTurn = true;
    private final IIHM ihm;

    public Game( int size , IIHM human_interface){
        this.p1 = new Player(human_interface.recuperer_type_de_joueur()) ;
        this.p2 = new Player(human_interface.recuperer_type_de_joueur());
        this.ihm = human_interface;
        this.plateau = new Plateau(size);
    }

   //pour simplifier nos test
    public Game(Player p1, Player p2, int size , IIHM human_interface){
        this.p1 = p1 ;
        this.p2 = p2;
        this.ihm = human_interface;
        this.plateau = new Plateau(size);
    }

    /**
     * Cette méthode permet au joueur ou à l'ia de jouer, faire un choix
     * sur quelles cases le joueur va poser son pion
     *
     * @param choice la case sur lequelle le joueur va jouer
     *
     * @see Player#setChoice(int)
     * @see Player#getIsIA()
     * @see #gameChoice()
     * @see Plateau#play(int, Stat)
     * @see Player#getChoice()
     * @see Player#getPawnColor()
     */
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

    /**
     * Cette méthode est utile quand les deux joueurs sont des IA
     *
     * @throws NotAdaptedFunction retourne une erreur si un des joueurs est un
     * humain
     *
     * @see Player#getIsIA()
     * @see #setPlayersChoice(int)
     */
    public void setPlayersChoice(){
        if (!this.p1.getIsIA() && !this.p2.getIsIA()) throw new NotAdaptedFunction();
        setPlayersChoice(0);
    }

    /**
     * Cette méthode permet d'indiquer si il reste des cases jouables sur la plateau
     *
     * @return retourne un booléen : true si le plateau est plein sinon fdlse
     *
     * @see Plateau#isFull()
     */
    public boolean isFull(){
        return this.plateau.isFull();
    }

    /**
     * Cette méthode permet de choisir aléatoirement une case à jouer pour les IA
     *
     * @return retourne un entier qui va correspondre à la position de la case
     *
     * @see Plateau#getNbOfUsableCase()
     * @see Plateau#taille()
     * @see Plateau#isEmpty(int, int)
     */
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

    /**
     * Méthode toString d'affichage du plateau
     *
     * @return retourne une chaine de caractère qui affiche le plateau
     *
     * @see Plateau#toString()
     */
    @Override
    public String toString(){
        return this.plateau.toString();
    }
}
