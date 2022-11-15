package player;

import game.Plateau;

import java.util.Random;

public class IA extends Player{
    public IA(){
        super(Identity.IA);
    }

    private int choiceRandCase(Plateau p){
        Random r = new Random();
        int nbOfEmptyCases = 0;
        for(int i = 0; i < p.taille(); i++){
            for(int j = 0; j < p.taille(); j++){
                if(p.isEmpty(i,j))
                    ++nbOfEmptyCases;
            }
        }
        return r.nextInt(nbOfEmptyCases);
    }

    public void setPlayerChoice(Plateau p){
        int nbOfEmptyCases = 0;
        int c = choiceRandCase(p);
        for(int i=0;i<p.taille();i++){
            for(int j=0 ; j<p.taille() ; j++){
                if(p.isEmpty(i,j))
                    ++nbOfEmptyCases;
                if(nbOfEmptyCases == c)
                    super.setPlayerChoice(i * p.taille() + j);

            }
        }
    }
}