package game;

import exeption.Unplayable;

import java.util.ArrayList;
import java.util.Arrays;

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

    private void initTab() {
        for (int i = 0; i < this.size; ++i)
            for (int j = 0; j < this.size; ++j)
                this.tab[i][j] = new Case();
    }

    public boolean isEmpty(int line, int column) {
        return this.tab[line][column].getStat() == Stat.EMPTY;
    }

    public boolean isFull() {
        return this.nbOfUsableCase == 0;
    }

    public int taille() {
        return this.size;
    }

    /**
     * @param numCase cela indique sur quelle case le joueur va jouer
     * @param s indique quel joueur joue en indiquant sa couleur, WHITE OU BLACK.
     * @throws Unplayable le joueur ne peut pas jouer si le numéro de case n'existe pas : s'il est inférieur à 0, s'il dépasse le plateau de jeu ou si la case n'est pas disponible
     * la méthode play dans la méthode, change si toutes les conditions le veulent, la case de la couleur du joueur qui a joué et l'on retire une case du nombre des cases à jouées
     */
    public void play(int numCase, Stat s) throws Unplayable{
        int line = numCase / this.size;
        int column = numCase % this.size;
        if (numCase < 0 || numCase >= Math.pow(this.size, 2) || !isEmpty(line, column))
            throw new Unplayable();

        this.tab[line][column].play(s);
        --nbOfUsableCase;
    }

    private ArrayList<Integer> getPawnsPositions(Stat color){
        ArrayList<Integer> pawnsPosition = new ArrayList<>();
        for (int i = 0; i < this.size; ++i){
            if (color == Stat.BLACK) {
                if (tab[i/this.size][i%this.size].getStat() == Stat.BLACK)
                    pawnsPosition.add(i);
            }
            else
                if (tab[(i * this.size)/this.size][(i * this.size)%this.size].getStat() == Stat.WHITE)
                    pawnsPosition.add(i * this.size);
        }
        return pawnsPosition;
    }

    // cette fonction peux être juste appeler une fois dans isWin, puis le passer en param de la fonction de récurence
    private boolean isInEndPosition(Stat color, int positionToCheck){
        ArrayList<Integer> pawsEndPosition = new ArrayList<>();
        for (int i = 0; i < this.size; ++i){
            if (color == Stat.BLACK){
                if (tab[(this.size * this.size - i - 1)/this.size][(this.size * this.size - i - 1)%this.size].getStat() == Stat.BLACK)
                    pawsEndPosition.add(this.size * this.size - i - 1);
            }
            else
                if (tab[(i * this.size + this.size - 1)/this.size][(i * this.size + this.size - 1)%this.size].getStat() == Stat.WHITE)
                    pawsEndPosition.add(i * this.size + this.size - 1);
        }
        return pawsEndPosition.contains(positionToCheck);
    }

    private boolean checkItForOnePosition(Stat playerPawnColor, int pawnPosition, ArrayList<String> endPositions){
        int line = pawnPosition / this.size;
        int column = pawnPosition % this.size;

        if(isInEndPosition(playerPawnColor, pawnPosition)) return true;
        if (this.tab[line][column].getStat() != playerPawnColor) return false;

        for (int i = -1; i < 2; ++i)
            for (int j = -1; j < 2; ++j) {
                if (line + i < this.size && line + i >= 0 &&
                        column + j < this.size && column + j >= 0 &&
                        endPositions.contains(i + "+" + j)) // si le couple i j se trouve bien au tour d'une position existante
                    if (this.tab[line + i][column + j].getStat() == playerPawnColor &&
                            !this.tab[line + i][column + j].isChecked()) {
                        this.tab[line + i][column + j].setChecked(true);
                        return checkItForOnePosition(playerPawnColor, ((line + i) * this.size) + (column + j), endPositions);
                    }
            }
        return false;
    }

    private void remettreToutFalse(){
        for (int i = 0; i < this.size; ++i)
            for (int j = 0; j < this.size; ++j)
                this.tab[i][j].setChecked(false);
    }

    public boolean isWin(Stat playerPawn){
        ArrayList<Integer> pawnsPosition = getPawnsPositions(playerPawn);
        ArrayList<String> sidePositions = new ArrayList<>(
                Arrays.asList(
                        "-1+0",
                        "-1+1",
                        "0+1",
                        "1+0",
                        "1+-1",
                        "0+-1"));

        while (!pawnsPosition.isEmpty()){
            if (checkItForOnePosition(playerPawn, pawnsPosition.get(0), sidePositions)) return true;
            pawnsPosition.remove(0);
        }
        remettreToutFalse();
        return false;
    }

    public int getNbOfUsableCase(){
        return this.nbOfUsableCase;
    }

    private String makeSpace(int numLine) {
        return " ".repeat(Math.max(0, numLine - 1));
    }

    private String stateOfAPosition(int line, int column) {
        if (this.tab[line][column].getStat() == Stat.BLACK) return " B";
        if (this.tab[line][column].getStat() == Stat.WHITE) return " W";
        return " .";
    }

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
