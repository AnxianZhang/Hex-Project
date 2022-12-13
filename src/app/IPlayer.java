package app;

import game.Plateau;
import game.Stat;

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
