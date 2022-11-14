package game;

import exeption.Unplayable;

public class Plateau {
    private final int size;
    private final Case[][] tab;
    private static final int MAX_SIZE = 26;
    private static final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K", "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public Plateau(int size){
        assert size <= MAX_SIZE && size > 0;
        this.size = size;
        this.tab = new Case [this.size][this.size];

        initTab();
    }

    private void initTab(){
        for (int i = 0; i < this.size; ++i)
            for (int j = 0; j < this.size; ++j)
                this.tab[i][j] = new Case();
    }

    public int taille(){
        return this.tab[0].length;
    }

    public boolean isPlayable (int numCase) throws Unplayable{
        if (numCase < 0 || numCase >= MAX_SIZE)
            throw new Unplayable();
        int line = numCase / this.size;
        int column = numCase % this.size;

        return this.tab[line][column].getStat() == Stat.EMPTY;
    }

    public void play(int numCase, int numPlayer){
        int line = numCase / this.size;
        int column = numCase % this.size;
        this.tab[line][column].play(numPlayer);
    }

    private String makeSpace(int numLine){
        return " ".repeat(Math.max(0, numLine - 1));
    }

    private String stateOfAPosition (int line, int column){
        if (this.tab[line][column].getStat() == Stat.BLACK) return " X";
        if (this.tab[line][column].getStat() == Stat.WHITE) return " O";
        return " .";
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; ++i)
            sb.append(" ").append(ALPHABET[i]);
        sb.append("\n");
        for (int i = 0; i < this.size; ++i){
            sb.append(i+1).append(makeSpace(i+1));
            for (int j = 0; j < this.size; ++j)
                sb.append(stateOfAPosition(i, j));
            sb.append("\n");
        }

        return sb.toString();
    }
}
