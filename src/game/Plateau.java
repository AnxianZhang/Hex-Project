package game;

import game.rules.Default;

/**
 * Brief: Classe Plateau permettan de creer une
 * plateau de jeu et d'y jouer
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 20
 * @since 13/12/2022
 */
public class Plateau {
    private final Rule rule;
    private int nbOfUsableCase;
    private final int size;
    private final Case[][] tab;
    private static final int MAX_SIZE = 26;

    public Plateau(int size) {
        assert size <= MAX_SIZE && size > 0;
        this.rule = new Default();
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

    public void playOnCase(int line, int column, Stat s){
        this.tab[line][column].play(s);
        --nbOfUsableCase;
    }

    public void play(int numCase, Stat s) throws Unplayable{
        this.rule.play(this, numCase, s);
    }

    public boolean isColorPawn(int line, int column, Stat color){
        return this.tab[line][column].getStat() == color;
    }

    public void setCheckedAt(int line, int column, boolean b){
        this.tab[line][column].setChecked(b);
    }

    public boolean isChecked(int line, int column){
        return this.tab[line][column].isChecked();
    }

    public Stat winner(){
        return this.rule.winner(this);
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