package player;

import Provider.Coup_Provider;
import game.Plateau;
import game.Stat;

public abstract class Player{
    protected Coup_Provider provider = new Coup_Provider();
    private static boolean isFirstColor = true;
    private int choice;
    private final boolean isIA;
    protected final Stat pawnColor;

    public Player(Identity type) {
        this.isIA = type == Identity.IA;

        if(isFirstColor){
            this.pawnColor = Stat.WHITE;
            isFirstColor = false;
        }
        else{
            this.pawnColor = Stat.BLACK;
            isFirstColor = true;
        }
    }

    public Stat getPawnColor(){
        return this.pawnColor;
    }

    abstract public int getChoice(Plateau p) ;

    public void setChoice(int inChoice){
        this.choice = inChoice;
    }

    public boolean getIsIA(){
        return this.isIA;
    }
}
