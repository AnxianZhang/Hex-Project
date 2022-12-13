package player;

import Provider.MoveProvider;
import app.IPlayer;
import game.Stat;

public abstract class Player implements IPlayer {
    protected MoveProvider provider = new MoveProvider();
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
}
