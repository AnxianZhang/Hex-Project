package game;

public class Case {
    private Stat stat;

    public Case (){
        this.stat = Stat.EMPTY;
    }

    public Stat getStat() {
        return stat;
    }

    public void play(int numPlayer){
        if (numPlayer == 1) this.stat = Stat.WHITE;
        else this.stat = Stat.BLACK;
    }
}
