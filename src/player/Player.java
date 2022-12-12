package player;

import Provider.Coup_Provider;
import game.Plateau;
import game.Stat;

public abstract class Player{
    protected Coup_Provider provider = new Coup_Provider();
    private static boolean isFirstColor = true;
    protected final Stat pawnColor;

    public Player() {
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

    public abstract int getChoice(Plateau p) ;
}
