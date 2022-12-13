package app;

import game.Plateau;
import game.Stat;

/**
 * Brief: Interfacte IPlayer permettant d'utiliser
 * plus facilment les Joueurs
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 1
 * @since 13/12/2022
 */
public interface IPlayer {
    /**
     * Indique la couleur du pion du joueur
     *
     * @return la couleur du pion (WHITE,BLACK)
     */

    Stat getPawnColor();

    /**
     * Retourn le choix qu'aura effectuer le joueur, la
     * méthede sera spécifier dans les sous classes
     *
     * @return retourne son choix
     */
    int getChoice(Plateau p);
}