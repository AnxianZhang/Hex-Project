package player;

import game.Stat;

public class Player{
    private static boolean isFirstColor = true;
    private int choice;
    private final boolean isIA;
    private final Stat pawnColor;

    /**
     * Ce constructeur permet d'initialiser tous les paramètres de la classe
     * en fonction du type du joueur
     * isIA est initialisé à true si la condition : le type du paramètre est égal à IA
     * est vraie.
     * isFirstColor permet de savoir qui va commencer
     *
     * @param type objet human ou ia de l'enum Identity
     */
    public Player(Identity type) {
        this.isIA = type == Identity.IA;

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
    public int getChoice() {
        return this.choice;
    }

    /**
     * Méthode qui permet de faire le choix de la case et de le mettre dans le paramètre choix
     *
     * @param inChoice position dans le plateau
     */
    public void setChoice(int inChoice){
        this.choice = inChoice;
    }

    /**
     * Méthode qui indique si le joueur est une IA ou non
     *
     * @return retourne un booléen : true si c'est une IA sinon false
     */
    public boolean getIsIA(){
        return this.isIA;
    }
}
