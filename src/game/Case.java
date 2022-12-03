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
        if (pawnColor == Stat.CHECKED) this.stat = Stat.CHECKED;
        this.stat = (pawnColor == Stat.WHITE) ? Stat.WHITE : Stat.BLACK;
    }
}
