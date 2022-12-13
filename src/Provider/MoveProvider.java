package Provider;

import IHM.IHMConsole;
import game.Plateau;
import game.Stat;

import java.util.Random;

public class MoveProvider {
    protected IHMConsole playerConsole = new IHMConsole();

    /**
     * Generation du coup de l'IA un fonction des coses
     * disponible sur le plateau
     * @param plateau le plateau
     *
     * @return le coup jouer par l'IA
     */
    public int provideIAMove(Plateau plateau) {
        Random r = new Random();
        int randCase = r.nextInt(plateau.getNbOfUsableCase()) + 1;
        int ctp = 0;
        for (int i = 0; i < plateau.taille(); ++i)
            for (int j = 0; j < plateau.taille(); ++j) {
                if (plateau.isEmpty(i, j)) ++ctp;
                if (ctp == randCase)
                    return (i * plateau.taille() + j);
            }

        return -1;
    }

    /**
     * Demande en console la position sur le quel le joueur
     * HUMAN veut jouer, le plateau passe en parametre permet
     * de verifier la validite du coup jouer
     *
     * @param color la couleur du joueur
     * @param p le plateau
     *
     * @return le coup choisis par le joueur
     */
    public int provideHumanMove(Stat color , Plateau p){
        return playerConsole.requestAMove(p,color.name());
    }
}
