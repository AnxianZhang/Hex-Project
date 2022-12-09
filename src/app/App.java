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
        while (!g.isFull()){
            if(p1.getIsIA() && p2.getIsIA()){
                ihm.mettre_a_jour_plateau(g);
                g.setPlayersChoice();
            }
            else if((p1.getIsIA() && !p2.getIsIA())){
                g.setPlayersChoice();
                ihm.mettre_a_jour_plateau(g);
                g.setPlayersChoice(ihm.demander_coup_a_jouer(g, p2));
                ihm.mettre_a_jour_plateau(g);
            }
            else if((!p1.getIsIA() && p2.getIsIA())){
                g.setPlayersChoice(ihm.demander_coup_a_jouer(g, p1));
                ihm.mettre_a_jour_plateau(g);
                g.setPlayersChoice();
                ihm.mettre_a_jour_plateau(g);
            }
            else {
                g.setPlayersChoice(ihm.demander_coup_a_jouer(g, p1));
                ihm.mettre_a_jour_plateau(g);
                g.setPlayersChoice(ihm.demander_coup_a_jouer(g, p2));
                ihm.mettre_a_jour_plateau(g);
            }

        }

        //System.out.println(ihm.recuperer_type_de_joueur());
    }
}
