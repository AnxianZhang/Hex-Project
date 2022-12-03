package game;

import exeption.Unplayable;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;

public class Plateau {
    private final static int MAX_PLAYERS = 2;
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

    public void play(int numCase, Stat s) throws Unplayable{
        int line = numCase / this.size;
        int column = numCase % this.size;
        if (numCase < 0 || numCase >= Math.pow(this.size, 2) || !isEmpty(line, column))
            throw new Unplayable();

        this.tab[line][column].play(s);
        --nbOfUsableCase;
    }

    private ArrayList<Integer> getColorPawns(Stat color){
        ArrayList<Integer> pawnsPosition = new ArrayList<>();

        for (int i = 0; i < this.size; ++i)
            pawnsPosition.add((color == Stat.BLACK) ? i + 1 : i * this.size);

        return pawnsPosition;
    }

    private boolean isInEndPosition(Stat color, int positionToCheck){
        ArrayList<Integer> pawsEndPosition = new ArrayList<>();

        for (int i =0; i < this.size; ++i)
            pawsEndPosition.add((color == Stat.BLACK) ? this.size * this.size  - i + 1 : i * this.size + this.size - 1);

        return pawsEndPosition.contains(positionToCheck);
    }

    private boolean checkItForOnePosition(Stat playerPawnColor, int pawnPosition, ArrayList<String> endPositions){
        int line = pawnPosition / this.size;
        int column = pawnPosition % this.size;

        if(isInEndPosition(playerPawnColor, pawnPosition)) return true;

        for (int i = -1; i < 2; ++i)
            for (int j = -1; j < 2; ++j){
                if (line + i < this.size && line + i >= 0 &&
                    column + j < this.size && column + j >= 0 &&
                    endPositions.contains(i+"+"+j)) // si le coupe i j se trouvent autour d'un point
                    if (this.tab[line + i][column + j].getStat() == playerPawnColor){
                        checkItForOnePosition(playerPawnColor,
                                ((line + i)  * this.size) + (column + j), endPositions);
                        this.tab[line + i][column + j].play(Stat.CHECKED);
                    }
            }
        return false;
    }

    public boolean isWin(Stat playerPawn){
        ArrayList<Integer> pawnsPosition = getColorPawns(playerPawn);
        ArrayList<String> endPositions = new ArrayList<>(
                Arrays.asList(
                        "-1+0",
                        "-1+1",
                        "0+1",
                        "1+0",
                        "1+-1",
                        "0+-1"));

        while (!pawnsPosition.isEmpty()){
            if (checkItForOnePosition(playerPawn, pawnsPosition.get(0), endPositions)) return true;
            pawnsPosition.remove(0);
        }
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

//    public boolean isWin(){
//        i=0
//    }

    public ArrayList<Integer> neighborCase(int numCase){
        ArrayList<Integer>nc = new ArrayList<>();
        int x = numCase / this.size;
        int y = numCase % this.size;

        nc.add(isEmpty(x-1,y)?null:toNumCase(x-1,y));
        nc.add(isEmpty(x-1,y+1)?null:toNumCase(x-1,y+1));
        nc.add(isEmpty(x,y+1)?null:toNumCase(x,y+1));
        nc.add(isEmpty(x+1,y)?null:toNumCase(x+1,y));
        nc.add(isEmpty(x+1,y-1)?null:toNumCase(x+1,y-1));
        nc.add(isEmpty(x,y-1)?null:toNumCase(x,y-1));

        return nc;
    }

    public int toNumCase(int x, int y){
        return x * this.taille() + y;
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
