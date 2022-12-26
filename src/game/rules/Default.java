package game.rules;

import exeption.Unplayable;
import game.Case;
import game.Plateau;
import game.Rule;
import game.Stat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Default implements Rule {
    /**
     * Permet de jouer sur une case en fonction du joueur, elle renvoie
     * argument exception si la case choisie n'est pas jouable.
     *
     * @param p le plateau
     * @param numCase cela indique sur quelle case le joueur va jouer
     * @param s indique quel joueur joue avec la couleur de son pion WHITE OU BLACK.
     *
     * @throws Unplayable lorsque le joueur joue sur une case non jouable
     *
     * @see Case#play(Stat)
     * @see Plateau#isEmpty(int, int)
     */
    public void play(Plateau p, int numCase ,Stat s) throws Unplayable{
        int line = numCase / p.taille();
        int column = numCase % p.taille();
        if (numCase < 0 || numCase >= Math.pow( p.taille(), 2) || !p.isEmpty(line, column))
            throw new Unplayable();

        p.playOnCase(line ,column, s);
    }

    /**
     * Remplis une liste avec les cases de la première
     * ligne ou colonne du plateau en fonction des couleurs des cases.
     * En bref, pour connaitre les positions des pions.
     *
     * @param p le plateau dans le quel les positions seront calculer
     * @param color la couleur du pion du joueur
     *
     * @return la liste le la premiere colonne ou ligne
     *
     * @see Case#getStat()
     */
    private ArrayList<Integer> getPawnsStartPositions(Plateau p, Stat color){
        ArrayList<Integer> pawnsPosition = new ArrayList<>();
        for (int i = 0; i < p.taille(); ++i){
            if (color == Stat.BLACK) {
                if (p.isColorPawn(i / p.taille(), i % p.taille(), Stat.BLACK))
                    pawnsPosition.add(i);
            }
            else
                if (p.isColorPawn((i * p.taille()) / p.taille(), (i * p.taille()) % p.taille(), Stat.WHITE))
                    pawnsPosition.add(i * p.taille());
        }
        return pawnsPosition;
    }

    /**
     * Remplit une liste avec les cases de la dernière
     * ligne ou colonne du plateau en fonction des couleurs des cases.
     *
     * @param p le plateau dans le quel les positions seront calculer
     * @param color couleur de la case à vérifier
     *
     * @return la liste de poistion de fin
     *
     * @see Case#getStat()
     */
    private ArrayList<Integer> getPawnsEndPositions(Plateau p, Stat color){
        ArrayList<Integer> pawsEndPosition = new ArrayList<>();
        for (int i = 0; i < p.taille(); ++i){
            if (color == Stat.BLACK){
                if (p.isColorPawn((p.taille() * p.taille() - i - 1)/p.taille(), (p.taille() * p.taille() - i - 1)%p.taille(),  Stat.BLACK))
                    pawsEndPosition.add(p.taille() * p.taille() - i - 1);
            }
            else
            if (p.isColorPawn((i * p.taille() + p.taille() - 1)/p.taille(), (i * p.taille() + p.taille() - 1)%p.taille(),  Stat.WHITE))
                pawsEndPosition.add(i * p.taille() + p.taille() - 1);
        }
        return pawsEndPosition;
    }

    /**
     * Verifit recursivement si un la couleur d'un pions est gagnant ou pas
     *
     * @param playerPawnColor la couleur du pion du joueur
     * @param pawnPosition la position du pion
     * @param sitePos position des case voisin
     * @param s pile de toutes les cases encore visitable
     * @param pawnsEndPos les positions de la derniere colonne ou ligne
     * @param p le plateau a parcopurir
     *
     * @return vrais si le pions a gagner sinon false
     *
     * @see Case#getStat()
     * @see Case#isChecked()
     * @see Case#setChecked(boolean)
     * @see #checkItForOnePosition(Stat, int, ArrayList, Stack, ArrayList, Plateau)
     */
    private boolean checkItForOnePosition(
            Stat playerPawnColor, int pawnPosition, ArrayList<String> sitePos,
            Stack<Integer> s, ArrayList<Integer> pawnsEndPos, Plateau p){
        int line = pawnPosition / p.taille();
        int column = pawnPosition % p.taille();
//        System.out.println(pawnPosition);
        p.setCheckedAt(line, column, true);

        if(pawnsEndPos.contains(pawnPosition)) return true;

        if (!p.isColorPawn(line, column, playerPawnColor)) return false;

        for (int i = -1; i < 2; ++i)
            for (int j = -1; j < 2; ++j) {
                if (line + i < p.taille() && line + i >= 0 && column + j < p.taille() && column + j >= 0 &&
                        sitePos.contains(i + "+" + j)) {// si le couple i j se trouve bien au tour d'une position existante
                    if (p.isColorPawn(line + i, column + j, playerPawnColor) &&
                            !p.isChecked(line + i, column + j)) {
                        s.push(pawnPosition);
                        return checkItForOnePosition(playerPawnColor,
                                ((line + i) * p.taille()) + (column + j), sitePos, s, pawnsEndPos, p);
                    }
                }
            }

        if(s.isEmpty()) return false; // rentre dans la condition s'il n'y a plus de case visitable

        return checkItForOnePosition(playerPawnColor, s.pop(), sitePos, s, pawnsEndPos, p);
    }

    /**
     * Remet toutes les cases du plateau a checked false
     *
     * @param p plateau a remmetre a false
     *
     * @see Case#setChecked(boolean)
     */
    private void remettreToutFalse(Plateau p){
        for (int i = 0; i < p.taille(); ++i)
            for (int j = 0; j < p.taille(); ++j)
                p.setCheckedAt(i, j, false);
    }

    /**
     * Indique si un joueur et gagnant ou pas pour chaque case de debut
     *
     * @param p plateau dans le quel recherche s'il y a un gagnant
     *
     * @return retourne le joueur gagnant, si aucun ne l'est return EMPTY
     *
     * @see #getPawnsStartPositions(Plateau, Stat)
     * @see Plateau#isEmpty(int, int)
     * @see #checkItForOnePosition(Stat, int, ArrayList, Stack, ArrayList, Plateau)
     * @see #remettreToutFalse(Plateau)
     */
    public Stat winner(Plateau p){
        for (int i = 0; i < Stat.values().length - 1; i++) {
            Stack<Integer> caseStillCheckable = new Stack<>();
            ArrayList<Integer> pawnsStartPosition = getPawnsStartPositions(p, Stat.values()[i]);
            ArrayList<Integer> pawnsEndPosition = getPawnsEndPositions(p, Stat.values()[i]);
            ArrayList<String> sidePositions = new ArrayList<>(
                    Arrays.asList(
                            "-1+0",
                            "-1+1",
                            "0+1",
                            "1+0",
                            "1+-1",
                            "0+-1"));
            while (!pawnsStartPosition.isEmpty()) {
                remettreToutFalse(p);
                if (checkItForOnePosition(Stat.values()[i], pawnsStartPosition.get(0),
                        sidePositions, caseStillCheckable, pawnsEndPosition, p)){
                    return Stat.values()[i];
                }
                pawnsStartPosition.remove(0);
            }
        }

        return Stat.EMPTY;
    }
}
