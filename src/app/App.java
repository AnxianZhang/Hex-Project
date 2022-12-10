package app;

import IHM.IHM;
import game.Plateau;
import game.Stat;
import player.Identity;
import player.Player;

import java.util.ArrayList;

public class App {
    static final int TAILLE_JEU = 4;
    static Plateau plateau = new Plateau(TAILLE_JEU);
    static ArrayList<Player> joueurs = new ArrayList<>();
    static Player joueur_courant;
    static private int compteur_joueur = 0;
    public static void main(String[] args) {
        IIHM ihm = new IHM();

        Player p1 = new Player(ihm.recuperer_type_de_joueur()); // imparaire = WHITE
        Player p2 = new Player(ihm.recuperer_type_de_joueur()); // pair = BLACK
        ihm.mettre_a_jour_plateau(plateau);
        while (!plateau.isFull()) {
            joueur_courant = get_joueur();
            plateau.play(joueur_courant.getChoice(), joueur_courant.getPawnColor());
            ihm.afficher_le_coup(joueur_courant,joueur_courant.getChoice());
            ihm.mettre_a_jour_plateau(plateau);
            if (plateau.isWin() == Stat.WHITE) {
                ihm.afficher_resultat(p1, p2);
                return;
            }
            if (plateau.isWin() == Stat.BLACK) {
                ihm.afficher_resultat(p2, p1);
                return;
            }
        }


        //System.out.println(ihm.recuperer_type_de_joueur());
    }
    static private Player get_joueur(){
        ++compteur_joueur;
        compteur_joueur = compteur_joueur%2;
        return joueurs.get(compteur_joueur);
    }
}
