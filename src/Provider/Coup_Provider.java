package Provider;

import IHM.IHM;
import game.Plateau;
import game.Stat;

import java.util.Random;

public class Coup_Provider {
    protected IHM console_joueur = new IHM();
    public int provide_coup_AI(Plateau plateau) {

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
    public int provide_coup_HUMAN(Stat couleur , Plateau p){
        return console_joueur.demander_coup_a_jouer(p,couleur);
    }

}
