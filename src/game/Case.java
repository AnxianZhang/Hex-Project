package game;

public class Case {
    private Stat stat;

    public Case (){
        this.stat = Stat.EMPTY;
    }

    public Stat getStat() {
        return stat;
    }

    public void play(Stat pawnColor){
        if (pawnColor == Stat.WHITE) this.stat = Stat.WHITE;
        else this.stat = Stat.BLACK;
    }
}
