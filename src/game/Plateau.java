package game;

public class Plateau {
    private final int size;
    private final Case[][] tab;
    private static final int MAX_SIZE = 26;
    private static final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K", "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private void initTab(){
        for (int i = 0; i < this.size; ++i)
            for (int j = 0; j < this.size; ++j)
                this.tab[i][j] = new Case();
    }
    public Plateau(int size){
        assert size <= MAX_SIZE && size > 0;
        this.size = size;
        this.tab = new Case [this.size][this.size];

        initTab();
    }

    public int taille(){
        return this.tab[0].length;
    }



    public boolean isPlayable (int numCase){
        int line = numCase / this.size;
        int column = numCase % this.size;

        return this.tab[line][column].getStat() == Stat.EMPTY;
    }

    public void play(int numCase, int numPlayer){
        int line = numCase / this.size;
        int column = numCase % this.size;
//        this.tab[line][column]./
    }

    private String makeSpace(int numLine){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < numLine; ++i){
            sb.append(" ");
        }

        return sb.toString();
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
