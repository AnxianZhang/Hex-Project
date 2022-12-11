package game;

public class Case {
    private Stat stat;
    private boolean isChecked = false;

    public Case (){
        this.stat = Stat.EMPTY;
    }

    public Stat getStat() {
        return stat;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    public void play(Stat pawnColor) {
        this.stat = pawnColor;
    }
}
