package app;

import IHM.IHMConsole;
import game.Plateau;
import game.Stat;
import player.Human;
import player.IA;
import player.Identity;

import java.util.ArrayList;

/**
 * Brief: Application qui permet de jouer au jeu
 * HEX
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 10
 * @since 13/12/2022
 */
public class App {
    static private final ArrayList<IPlayer> joueurs = new ArrayList<>();
    static private int numPlayer = 0; // numeros du joueur qui doit jouer

    /**
     * Permet de récupérer le joueur courant
     *
     * @return le joueur courrant
     */
    static private IPlayer get_joueur(){
        if (numPlayer >= 2){
            numPlayer = 0;
        }
        int numPlayer = App.numPlayer % joueurs.size();
        ++App.numPlayer;
        return joueurs.get(numPlayer);
    }

    public static void main(String[] args) {
        int currentPlayerChoice;
        IIHM ihm = new IHMConsole();
        Plateau plateau = new Plateau(ihm.requestPlateauSize());
        IPlayer p1 = ihm.requestPlayerType() == Identity.HUMAN ? new Human() : new IA(); // impaire = WHITE
        IPlayer p2 = ihm.requestPlayerType() == Identity.HUMAN ? new Human() : new IA(); // pair = BLACK

        joueurs.add(p1);
        joueurs.add(p2);

        ihm.refreshPlateau(plateau);

        while (!plateau.isFull()) {
            IPlayer currentPlayer = get_joueur();

            currentPlayerChoice = currentPlayer.getChoice(plateau);
            while (!plateau.isEmpty(currentPlayerChoice / plateau.taille(),
                    currentPlayerChoice % plateau.taille())) {
                ihm.caseAlreadyUsed(plateau);
                currentPlayerChoice = currentPlayer.getChoice(plateau);
            }

            plateau.play(currentPlayerChoice, currentPlayer.getPawnColor());
            ihm.showPlayedPosition(currentPlayer.getPawnColor().name(), currentPlayerChoice);
            ihm.refreshPlateau(plateau);
            if (plateau.winner() == Stat.WHITE) {
                ihm.showResult(p1.getPawnColor().name(), p2.getPawnColor().name());
                return;
            } else if (plateau.winner() == Stat.BLACK) {
                ihm.showResult(p2.getPawnColor().name(), p1.getPawnColor().name());
                return;
            }
        }
    }
}
