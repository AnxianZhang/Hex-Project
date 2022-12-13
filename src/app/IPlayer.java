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
     * Indique le choix du joueur elle sera differente
     * selon le type de joueur choisi(IA, HUMAN ...)
     *
     * @return retourne son choix
     */
    int getChoice(Plateau p);
}
