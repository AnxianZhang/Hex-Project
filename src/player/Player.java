package player;

import Provider.Coup_Provider;
import game.Plateau;
import game.Stat;

public abstract class Player{
    protected Coup_Provider provider = new Coup_Provider();
    private static boolean isFirstColor = true;
    protected final Stat pawnColor;

    /**
     * Ce constructeur permet d'initialiser tous les paramètres de la classe
     * en fonction du type du joueur
     * isIA est initialisé à true si la condition : le type du paramètre est égal à IA
     * est vraie.
     * isFirstColor permet de savoir qui va commencer
     */
    public Player() {
        if(isFirstColor){
            this.pawnColor = Stat.WHITE;
            isFirstColor = false;
        }
        else{
            this.pawnColor = Stat.BLACK;
            isFirstColor = true;
        }
    }

    /**
     * Méthode qui indique la couleur du pion
     *
     * @return retourne un objet de la classe Stat (WHITE,BLACK,VIDE)
     */
    public Stat getPawnColor(){
        return this.pawnColor;
    }

    /**
     * Méthode qui indique le choix du joueur pour la position de son pion
     *
     * @return retourne un entier en référence à la position sur le plateau
     */
    public abstract int getChoice(Plateau p) ;
}
