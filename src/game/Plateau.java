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

    /**
     * Cette méthode parcours le tableau et le rempli avec des cases vides
     *
     * @see Case#Case() appele du constructeur de case, initialisé à vide
     */
    private void initTab() {
        for (int i = 0; i < this.size; ++i)
            for (int j = 0; j < this.size; ++j)
                this.tab[i][j] = new Case();
    }

    /**
     * Cette méthode indique si une case est vide ou non
     *
     * @param line ligne où l'on veut regarder
     * @param column colonne où l'on veut regarder
     *
     * @return retourne un booléen : true si la case est vide et false si
     * elle ne l'est pas
     *
     * @see Case#getStat() permet de retourner le statut de la case (empty, black ou white)
     */
    public boolean isEmpty(int line, int column) {
        return this.tab[line][column].getStat() == Stat.EMPTY;
    }

    /**
     * Cette méthode indique si le plateau est plein, c'est à dire qu'aucune
     * case n'est jouable
     *
     * @return retourne true si le plateau est plein, false sinon
     */
    public boolean isFull() {
        return this.nbOfUsableCase == 0;
    }

    /**
     * Cette méthode donne la taille du plateau
     *
     * @return retourne la taille de la ligne ou de la colonne
     */
    public int taille() {
        return this.size;
    }

    /**
     * Cette méthode permet de jouer sur une case en fonction du joueur, elle renvoie
     * argument exception si la case
     * choisie nc'est pas jouable.
     *
     * @param numCase cela indique sur quelle case le joueur va jouer
     * @param s indique quel joueur joue en indiquant sa couleur, WHITE OU BLACK.
     *
     * @throws Unplayable le joueur ne peut pas jouer si le numéro de case n'existe
     * pas : s'il est inférieur à 0,
     * s'il dépasse le plateau de jeu ou si la case n'est pas disponible.
     *
     * @see Case#play(Stat) : la méthode play de la classe Case, change si toutes
     * les conditions le veulent, la case de la couleur du joueur qui a joué et
     * l'on retire une case du nombre des cases à jouées.
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
     *Cette méthode permet de remplir une liste avec les cases de la première
     * ligne ou colonne du plateau en fonction des couleurs des cases.
     * En bref, pour connaitre les positions des pions.
     *
     * @param color la couleur du joueur à vérifier
     *
     * @return retourne une ArrayList d'entier qui retranscrit la première ligne
     * ou colonne
     * du plateau en différenciant les couleurs de cases
     *
     * @see Case#getStat() permet de retourner le statut de la case (empty, black ou white)
     */
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

    /**
     * Cette méthode permet de remplir une liste avec les cases de la dernière
     * ligne ou colonne du plateau en fonction des couleurs des cases. Ensuite,
     * la méthode regarde si une case en particulier est dans cette liste.
     *
     * @param color couleur de la case à vérifier
     * @param positionToCheck position sur la plateau à vérifier
     *
     * @return return un booléen : true si la liste contient la positition spécifique,
     * false sinon
     *
     * @see Case#getStat() permet de retourner le statut de la case (empty, black ou white)
     */
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

    /**
     * Cette méthode est la méthode principale du plateau, c'est celle qui relie
     * la méthode pour vérifier les premières lignes et colonnes avec la méthode
     * pour les dernières lignes et colonnes. Cette méthode sera essentiel, elle
     * va vérifier si un pions a gagné ou pas.
     *
     * @param playerPawnColor la couleur du pion du joueur
     * @param pawnPosition la position du pion
     * @param endPositions la dernière colonne du plateau
     *
     * @return retourne un booléen si oui ou non le pion passé en paramètre a gagné
     *         si true le joueur a gagné sinon false
     *
     * @see #isInEndPosition(Stat, int) 
     * @see Case#getStat() permet de retourner le statut de la case (empty, black ou white)
     * @see Case#isChecked() 
     * @see Case#setChecked(boolean) 
     * @see #checkItForOnePosition(Stat, int, ArrayList) 
     */
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

    /**
     * Cette méthode remet toutes les cases du plateau à checked false,
     * c'est à dire que l'état des cases ne deviennent plus verifier,
     * il faudra re-check tout le plateau
     *
     * @see Case#setChecked(boolean)
     */
    private void remettreToutFalse(){
        for (int i = 0; i < this.size; ++i)
            for (int j = 0; j < this.size; ++j)
                this.tab[i][j].setChecked(false);
    }

    /**
     * Cette méthode nous indique s'il y a un chemin qui mène à la dernière case
     * en regdardant autour de la case s'il y a des cases remplis.
     *
     * @param playerPawn couleur du pion du joueur
     *
     * @return retourne true s'il y a un chemin jusqu'à la dernière case
     * 
     * @see #getPawnsPositions(Stat) 
     * @see #isEmpty(int, int) 
     * @see #checkItForOnePosition(Stat, int, ArrayList) 
     * @see #remettreToutFalse() 
     */
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

    /**
     * Cette méthode nous indique le nombre de case jouable sur le plateau
     *
     * @return retourne un entier du nombre de cases jouables
     */
    public int getNbOfUsableCase(){
        return this.nbOfUsableCase;
    }

    /**
     * Cette méthode permet de faire des espaces le nombre de fois que l'on souhaite.
     * Elle sera utile pour le toString de la classe.
     *
     * @param numLine le nombre de ligne
     *
     * @return retourne des espaces dans une chaine de caractère
     */
    private String makeSpace(int numLine) {
        return " ".repeat(Math.max(0, numLine - 1));
    }

    /**
     * Cette méthode permet de remplir le toString avec des lettres
     * en fonction de la couleur des pions pour chaque position sur le plateau
     *
     * @param line ligne où l'on veut regarder
     * @param column colonne où l'on veut regarder
     *
     * @return retourne B pour le pion BLACK
     *         retourne W pour le pion WHITE
     *         retourne un point si la case est vide
     *
     * @see Case#getStat() permet de retourner le statut de la case (empty, black ou white)
     *
     */
    private String stateOfAPosition(int line, int column) {
        if (this.tab[line][column].getStat() == Stat.BLACK) return " B";
        if (this.tab[line][column].getStat() == Stat.WHITE) return " W";
        return " .";
    }

    /**
     * Méthode toString qui permet un affichage lisible du plateau grâce
     * aux méthodes ci-dessus et un stringbuilder pour de la facilité
     *
     * @return retourne une chaine de caractère présentant le tableau avec
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
