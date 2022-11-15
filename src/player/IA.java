package player;

import game.Plateau;

import java.util.Random;

public class IA extends Player{
    public IA(){
        super(Identity.IA);
    }

    /**
     * A modifier mettre une variable static pour le nombre de case utiliser
     * @param p
     * @return
     */
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

    /**
     * a modifier aussi
     * @param p
     * @return
     */
    public int setIAChoice(Plateau p){
        int nbOfEmptyCases = 0;
        int c = choiceRandCase(p);
        for(int i=0;i<p.taille();i++){
            for(int j=0 ; j<p.taille() ; j++){
                if(p.isEmpty(i,j))
                    ++nbOfEmptyCases;
                if(nbOfEmptyCases == c){
                    super.setPlayerChoice(i * p.taille() + j);
                    return i * p.taille() + j;
                }
            }
        }
        return -1;
    }
}