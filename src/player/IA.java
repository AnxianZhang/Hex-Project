package player;

import game.Plateau;

import java.util.Random;

public class IA extends Player{
    public IA(){
        super(Identity.IA);
    }

    public void makeChoice(Plateau p){
        Random r = new Random();
        int randCase = r.nextInt(p.getNbOfUsableCase()) + 1;
        int ctp = 0;
        for (int i = 0; i < p.taille(); ++i)
            for (int j = 0; j < p.taille(); ++j){
                if (p.isEmpty(i, j)) ++ctp;
                if (ctp == randCase) {
                    super.setChoice(i * p.taille() + j);
                    return;
                }
            }
    }

}