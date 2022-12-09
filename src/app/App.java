package app;

import IHM.IHM;
import game.Plateau;
import game.Stat;
import player.Identity;
import player.Player;

public class App {
    public static void main(String[] args) {
        IIHM ihm=new IHM();
        final int TAILLE_JEU = 4;
        Player p1 = new Player(ihm.recuperer_type_de_joueur()); // imparaire = WHITE
        Player p2 = new Player(ihm.recuperer_type_de_joueur()); // pair = BLACK
        Game g= new Game(p1, p2 ,TAILLE_JEU);
        ihm.mettre_a_jour_plateau(g);
        while (true){
            if(p1.getIsIA() && p2.getIsIA()){
                g.setPlayersChoice();
                ihm.mettre_a_jour_plateau(g);
                if(g.isWin(p1.getPawnColor())) {
                    ihm.afficher_resultat(p1,p2);
                    return;
                }
                if(g.isWin(p2.getPawnColor())){
                    ihm.afficher_resultat(p2,p1);
                    return;
                }
            }
            else if((p1.getIsIA() && !p2.getIsIA())){
                g.setPlayersChoice();
                ihm.mettre_a_jour_plateau(g);
                if(g.isWin(p1.getPawnColor())) {
                    ihm.afficher_resultat(p1,p2);
                    return;
                }
                g.setPlayersChoice(ihm.demander_coup_a_jouer(g, p2));
                ihm.mettre_a_jour_plateau(g);
                if(g.isWin(p2.getPawnColor())){
                    ihm.afficher_resultat(p2,p1);
                    return;
                }
            }
            else if((!p1.getIsIA() && p2.getIsIA())){
                g.setPlayersChoice(ihm.demander_coup_a_jouer(g, p1));
                ihm.mettre_a_jour_plateau(g);
                if(g.isWin(p1.getPawnColor())) {
                    ihm.afficher_resultat(p1,p2);
                    return;
                }
                g.setPlayersChoice();
                ihm.mettre_a_jour_plateau(g);
                if(g.isWin(p2.getPawnColor())){
                    ihm.afficher_resultat(p2,p1);
                    return;
                }
            }
            else {
                g.setPlayersChoice(ihm.demander_coup_a_jouer(g, p1));
                ihm.mettre_a_jour_plateau(g);
                if(g.isWin(p1.getPawnColor())) {
                    ihm.afficher_resultat(p1,p2);
                    return;
                }
                g.setPlayersChoice(ihm.demander_coup_a_jouer(g, p2));
                ihm.mettre_a_jour_plateau(g);
                if(g.isWin(p2.getPawnColor())){
                    ihm.afficher_resultat(p2,p1);
                    return;
                }
            }

        }

        //System.out.println(ihm.recuperer_type_de_joueur());
    }
}
