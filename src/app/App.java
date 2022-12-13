package app;

import IHM.IHMConsole;
import game.Plateau;
import game.Stat;
import player.Human;
import player.IA;
import player.Identity;

import java.util.ArrayList;

public class App {
    static final int TAILLE_JEU = 4;
    static Plateau plateau = new Plateau(TAILLE_JEU);
    static ArrayList<IPlayer> joueurs = new ArrayList<>();
    static IPlayer joueur_courant;
    static private int compteur_joueur = 0;
    public static void main(String[] args) {
        int choix_du_joueur_courant;
        IIHM ihm = new IHMConsole();

        IPlayer p1 = ihm.requestPlayerType() == Identity.HUMAN? new Human(): new IA(); // impaire = WHITE
        IPlayer p2 = ihm.requestPlayerType() == Identity.HUMAN? new Human(): new IA(); // pair = BLACK
        joueurs.add(p1);
        joueurs.add(p2);
        ihm.refreshPlateau(plateau);

        while (!plateau.isFull()) {
            joueur_courant = get_joueur();
            choix_du_joueur_courant = joueur_courant.getChoice(plateau);
            plateau.play(choix_du_joueur_courant, joueur_courant.getPawnColor());
            ihm.showPlayedPosition(joueur_courant.getPawnColor().name(),choix_du_joueur_courant);
            ihm.refreshPlateau(plateau);
            if (plateau.isWin() == Stat.WHITE) {
                ihm.showResult(p1.getPawnColor().name(), p2.getPawnColor().name());
                return;
            }
            else if (plateau.isWin() == Stat.BLACK) {
                ihm.showResult(p2.getPawnColor().name(), p1.getPawnColor().name());
                return;
            }
        }
    }

    static private IPlayer get_joueur(){
        if (compteur_joueur >= 2){
            compteur_joueur = 0;
        }
        int numPlayer = compteur_joueur % joueurs.size();
        ++compteur_joueur;
        return joueurs.get(numPlayer);
    }
}
