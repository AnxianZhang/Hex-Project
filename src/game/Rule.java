package game;

/**
 * Brief: Interfacte Rule permettant de crer des
 * des regles differentes
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 1
 * @since 26/12/2022
 */
public interface Rule {
    /**
     * Permet de jouer sur une case en fonction du joueur, elle renvoie
     * argument exception si la case choisie n'est pas jouable.
     *
     * @param p le plateau
     * @param numCase cela indique sur quelle case le joueur va jouer
     * @param s indique quel joueur joue avec la couleur de son pion WHITE OU BLACK.
     *
     * @throws Unplayable lorsque le joueur joue sur une case non jouable
     */

    void play(Plateau p ,int numCase, Stat s) throws Unplayable;

    /**
     * Indique si un joueur et gagnant ou pas pour chaque case de debut
     *
     * @param p plateau dans le quel recherche s'il y a un gagnant
     *
     * @return retourne le joueur gagnant, si aucun ne l'est return EMPTY
     */
    Stat winner(Plateau p);
}
