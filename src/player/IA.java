package player;

import game.Plateau;

import java.util.Random;

public class IA extends Player{
    public IA(){
        super(Identity.IA);
    }

//    private int choiceRandCase(Plateau p){
//        Random r = new Random();
//        int nbOfEmptyCases = 0;
//        for(int i = 0; i < p.taille(); i++){
//            for(int j = 0; j < p.taille(); j++){
//                if(p.isEmpty(i,j))
//                    ++nbOfEmptyCases;
//            }
//        }
//        return r.nextInt(nbOfEmptyCases);
//    }
//
//    public int setIAChoice(Plateau p){
//        int nbOfEmptyCases = 0;
//        int c = choiceRandCase(p);
//        for(int i=0;i<p.taille();i++){
//            for(int j=0 ; j<p.taille() ; j++){
//                if(p.isEmpty(i,j))
//                    ++nbOfEmptyCases;
//                if(nbOfEmptyCases == c){
//                    super.setPlayerChoice(i * p.taille() + j);
//                    return i * p.taille() + j;
//                }
//            }
//        }
//        return -1;
//    }

    public void makeChoice(Plateau p){
        Random r = new Random();
        System.out.println("Case dispo " + p.getNbOfUsableCase());
        int randCase = r.nextInt(p.getNbOfUsableCase()) + 1; // ok
        System.out.print("randCase: " + randCase + " ");
        int ctp = 0;
        for (int i = 0; i < p.taille(); ++i)
            for (int j = 0; j < p.taille(); ++j){
                if (p.isEmpty(i, j)) ++ctp;
                if (ctp == randCase) {
                    super.setChoice(i * p.taille() + j);
                    System.out.println(i * p.taille() + j);
                    return;
                }
            }
    }
}