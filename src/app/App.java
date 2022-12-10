package app;

import IHM.IHM;
import game.Plateau;
import game.Stat;
import player.Human;
import player.IA;
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
        int choix_du_joueur_courant;
        IIHM ihm = new IHM();

        Player p1 = ihm.recuperer_type_de_joueur() == Identity.HUMAN? new Human(Identity.HUMAN): new IA(Identity.IA); // imparaire = WHITE
        Player p2 = ihm.recuperer_type_de_joueur() == Identity.HUMAN? new Human(Identity.HUMAN): new IA(Identity.IA);; // pair = BLACK
        joueurs.add(p1);
        joueurs.add(p2);
        ihm.mettre_a_jour_plateau(plateau);

        while (!plateau.isFull()) {
            joueur_courant = get_joueur();
            choix_du_joueur_courant = joueur_courant.getChoice(plateau);
            plateau.play(choix_du_joueur_courant, joueur_courant.getPawnColor());
            ihm.mettre_a_jour_plateau(plateau);
            ihm.afficher_le_coup(joueur_courant,choix_du_joueur_courant);
            if (plateau.isWin() == Stat.WHITE) {
                ihm.afficher_resultat(p1, p2);
                return;
            }
            else if (plateau.isWin() == Stat.BLACK) {
                ihm.afficher_resultat(p2, p1);
                return;
            }
        }


        //System.out.println(ihm.recuperer_type_de_joueur());
    }
    static private Player get_joueur(){

        ++compteur_joueur;
        compteur_joueur = compteur_joueur % joueurs.size();
        return joueurs.get(compteur_joueur);
    }
}
