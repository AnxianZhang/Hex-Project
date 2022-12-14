package game;

import exeption.Unplayable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Brief: Classe Plateau permettan de creer une
 * plateau de jeu et d'y jouer
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 20
 * @since 13/12/2022
 */
public class Plateau {
    private int nbOfUsableCase;
    private final int size;
    private final Case[][] tab;
    private static final int MAX_SIZE = 26;

    public Plateau(int size) {
        assert size <= MAX_SIZE && size > 0;
        this.size = size;
        this.tab = new Case[this.size][this.size];
        this.nbOfUsableCase = this.size * this.size;

        initTab();
    }

    /**
     * Remplit le tableau de nouvelle case
     *
     */
    private void initTab() {
        for (int i = 0; i < this.size; ++i)
            for (int j = 0; j < this.size; ++j)
                this.tab[i][j] = new Case();
    }

    /**
     * Indique si une case est vide ou non
     *
     * @param line ligne ou l'on veut regarder
     * @param column colonne ou l'on veut regarder
     *
     * @return vrais si elle vide sinon false
     *
     * @see Case#getStat()
     */
    public boolean isEmpty(int line, int column) {
        return this.tab[line][column].getStat() == Stat.EMPTY;
    }

    /**
     * Indique si le plateau est plein
     *
     * @return true si le plateau est plein, false sinon
     */
    public boolean isFull() {
        return this.nbOfUsableCase == 0;
    }

    /**
     * Donne la taille du plateau
     *
     * @return le taille
     */
    public int taille() {
        return this.size;
    }

    /**
     * Permet de jouer sur une case en fonction du joueur, elle renvoie
     * argument exception si la case choisie n'est pas jouable.
     *
     * @param numCase cela indique sur quelle case le joueur va jouer
     * @param s indique quel joueur joue avec la couleur de son pion WHITE OU BLACK.
     *
     * @throws Unplayable lorsque le joueur joue sur une case non jouable
     *
     * @see Case#play(Stat)
     * @see #isEmpty(int, int)
     */
    public void play(int numCase, Stat s) throws Unplayable{
        int line = numCase / this.size;
        int column = numCase % this.size;
        if (numCase < 0 || numCase >= Math.pow(this.size, 2) || !isEmpty(line, column))
            throw new Unplayable();

        this.tab[line][column].play(s);
        --nbOfUsableCase;
    }

    /**
     * Remplis une liste avec les cases de la première
     * ligne ou colonne du plateau en fonction des couleurs des cases.
     * En bref, pour connaitre les positions des pions.
     *
     * @param color la couleur du pion du joueur
     *
     * @return la liste le la premiere colonne ou ligne
     *
     * @see Case#getStat()
     */
    private ArrayList<Integer> getPawnsStartPositions(Stat color){
        ArrayList<Integer> pawnsPosition = new ArrayList<>();
        for (int i = 0; i < this.size; ++i){
            if (color == Stat.BLACK) {
                if (this.tab[i / this.size][i % this.size].getStat() == Stat.BLACK)
                    pawnsPosition.add(i);
            }
            else
                if (this.tab[(i * this.size) / this.size][(i * this.size) % this.size].getStat() == Stat.WHITE)
                    pawnsPosition.add(i * this.size);
        }
        return pawnsPosition;
    }

    /**
     * Remplit une liste avec les cases de la dernière
     * ligne ou colonne du plateau en fonction des couleurs des cases.
     *
     * @param color couleur de la case à vérifier
     *
     * @return la liste de poistion de fin
     *
     * @see Case#getStat()
     */
    private ArrayList<Integer> getPawnsEndPositions(Stat color){
        ArrayList<Integer> pawsEndPosition = new ArrayList<>();
        for (int i = 0; i < this.size; ++i){
            if (color == Stat.BLACK){
                if (this.tab[(this.size * this.size - i - 1)/this.size][(this.size * this.size - i - 1)%this.size].getStat() == Stat.BLACK)
                    pawsEndPosition.add(this.size * this.size - i - 1);
            }
            else
                if (this.tab[(i * this.size + this.size - 1)/this.size][(i * this.size + this.size - 1)%this.size].getStat() == Stat.WHITE)
                    pawsEndPosition.add(i * this.size + this.size - 1);
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
     *
     * @return vrais si le pions a gagner sinon false
     *
     * @see Case#getStat()
     * @see Case#isChecked() 
     * @see Case#setChecked(boolean) 
     * @see #checkItForOnePosition(Stat, int, ArrayList, Stack, ArrayList)
     */
    private boolean checkItForOnePosition(
            Stat playerPawnColor, int pawnPosition, ArrayList<String> sitePos,
            Stack<Integer> s, ArrayList<Integer> pawnsEndPos){
        int line = pawnPosition / this.size;
        int column = pawnPosition % this.size;

        this.tab[line][column].setChecked(true);

        if(pawnsEndPos.contains(pawnPosition)) return true;

        if (this.tab[line][column].getStat() != playerPawnColor) return false;

        for (int i = -1; i < 2; ++i)
            for (int j = -1; j < 2; ++j) {
                if (line + i < this.size && line + i >= 0 && column + j < this.size && column + j >= 0 &&
                        sitePos.contains(i + "+" + j)) // si le couple i j se trouve bien au tour d'une position existante
                    if (this.tab[line + i][column + j].getStat() == playerPawnColor &&
                            !this.tab[line + i][column + j].isChecked()) {
                        s.push(pawnPosition);
                        return checkItForOnePosition(playerPawnColor,
                                ((line + i) * this.size) + (column + j), sitePos, s, pawnsEndPos);
                    }
            }

        if(s.isEmpty()) return false; // rentre dans la condition s'il n'y a plus de case visitable

        return checkItForOnePosition(playerPawnColor, s.pop(), sitePos, s, pawnsEndPos);
    }

    /**
     * Remet toutes les cases du plateau a checked false
     *
     * @see Case#setChecked(boolean)
     */
    private void remettreToutFalse(){
        for (int i = 0; i < this.size; ++i)
            for (int j = 0; j < this.size; ++j)
                this.tab[i][j].setChecked(false);
    }

    /**
     * Indique si un joueur et gagnant ou pas pour chaque case de debut
     *
     * @return retourne true s'il y a un chemin jusqu'à la dernière case
     * 
     * @see #getPawnsStartPositions(Stat)
     * @see #isEmpty(int, int) 
     * @see #checkItForOnePosition(Stat, int, ArrayList, Stack, ArrayList)
     * @see #remettreToutFalse() 
     */
    public Stat isWin(){
        for (int i = 0; i < Stat.values().length - 1; i++) {
            Stack<Integer> caseStillCheckable = new Stack<>();
            ArrayList<Integer> pawnsStartPosition = getPawnsStartPositions(Stat.values()[i]);
            ArrayList<Integer> pawnsEndPosition = getPawnsEndPositions(Stat.values()[i]);
            ArrayList<String> sidePositions = new ArrayList<>(
                    Arrays.asList(
                            "-1+0",
                            "-1+1",
                            "0+1",
                            "1+0",
                            "1+-1",
                            "0+-1"));
            while (!pawnsStartPosition.isEmpty()) {
                remettreToutFalse();
                if (checkItForOnePosition(Stat.values()[i], pawnsStartPosition.get(0),
                        sidePositions, caseStillCheckable, pawnsEndPosition)){
                    return Stat.values()[i];
                }
                pawnsStartPosition.remove(0);
            }
        }

        return Stat.EMPTY;
    }

    /**
     * Retourne le nombre de case jouable sur le plateau
     *
     * @return le nombre de case
     */
    public int getNbOfUsableCase(){
        return this.nbOfUsableCase;
    }

    /**
     * Permet de creer les espace necessaire en contion du numeros
     * de ligne pour l'affichage en console
     *
     * @param numLine numeros de ligne
     *
     * @return retourne des espaces dans une chaine de caractere
     */
    private String makeSpace(int numLine) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numLine - 1; ++i)
            sb.append(" ");
        return sb.toString();
    }

    /**
     * Retourne la lettre correspondante selon la
     * stat du pion dans le tableau
     *
     * @param line ligne tu tableau
     * @param column colonne du tableau
     *
     * @return retourne B pour le pion BLACK
     *         retourne W pour le pion WHITE
     *         retourne un point si la case est vide
     *
     * @see Case#getStat()
     *
     */
    private String stateOfAPosition(int line, int column) {
        if (this.tab[line][column].getStat() == Stat.BLACK) return " B";
        if (this.tab[line][column].getStat() == Stat.WHITE) return " W";

        return " .";
    }

    /**
     * Permet l'affichage en console du tableau
     *
     * @return retourne une chaine de caractère presentant le tableau avec
     * tous les pions et les cases vides
     *
     * @see #makeSpace(int)
     * @see #stateOfAPosition(int, int)
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char c = 'A'; c < 'A' + this.size; ++c)
            sb.append(" ").append(c);
        sb.append("\n");
        for (int i = 0; i < this.size; ++i) {
            sb.append(i + 1).append(makeSpace(i + 1));
            for (int j = 0; j < this.size; ++j)
                sb.append(stateOfAPosition(i, j));
            sb.append("\n");
        }

        return sb.toString();
    }
}