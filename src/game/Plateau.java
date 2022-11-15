package game;

import exeption.Unplayable;

public class Plateau {
    private final static int MAX_PLAYERS = 2;
    private final int size;
    private final Case[][] tab;
    private static final int MAX_SIZE = 26;

    public Plateau(int size) {
        assert size <= MAX_SIZE && size > 0;
        this.size = size;
        this.tab = new Case[this.size][this.size];

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

    /**
     * A competer / revoir
     * @return
     */
    public boolean ifFull() {
        boolean p = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isEmpty(i, j)) {
                    p = true;
                }
            }
        }
        return p;
    }

    public int taille() {
        return this.size;
    }

    public void play(int numCase, Stat pawnColor) throws Unplayable{
        if (numCase < 0 || numCase >= Math.pow(this.size, 2))
            throw new Unplayable();

        int line = numCase / this.size;
        int column = numCase % this.size;
        if (isEmpty(line, column))
            this.tab[line][column].play(pawnColor);
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
