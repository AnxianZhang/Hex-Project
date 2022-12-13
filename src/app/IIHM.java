package app;

import game.Plateau;
import player.Identity;

/**
 * Brief: Interfacte IIHM permettant d'utiliser plus
 * plus facilment les IHM
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 4
 * @since 13/12/2022
 */
public interface IIHM {
    /**
     * Proposer a l'utilisateur de choisir un typesde joueurs (IA ou humain)
     *
     * @return le type choisit par l'utilisateur
     *
     */
    Identity requestPlayerType();

    /**
     * Afficher les résultats de la partie.
     *
     * @param winner le gagant
     * @param looser le pardant
     */
    void showResult(String winner, String looser);

    /**
     * Afficher le plateau
     *
     * @param p le plateau
     */
    void refreshPlateau(Plateau p);

    /**
     * Afficher le coup d'un joueur, où est-ce qu'il a joué ?
     *
     * @param playerPawnColor la couleur du joueur
     * @param choice la case où le joueur à joué
     *
     */
    void showPlayedPosition(String playerPawnColor, int choice);

    /**
     * Demande aux joueurs où est-ce qu'ils veulent poser leur pion
     *
     * @param p le plateau sur lequelle les joueurs jouent
     * @param player le joueur a qui on demande où il va jouer
     *
     * @return la position joue par le joueur
     *
     * @see Plateau#taille()
     */
    int requestAMove(Plateau p, String player);

    /**
     * Demande aux joueur la taille du tableau souhaite
     * @return la taille
     */
    int requestPlateauSize();

    /**
     * Affiche un message prevenant que la case jouer
     * presente deja un pion
     */
    void caseAlreadyUsed(Plateau p);
}
