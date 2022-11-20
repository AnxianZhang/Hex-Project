package player;

import app.IPlayer;
import game.Stat;

public abstract class Player{
    private static boolean isFirstColor = true;
    protected int choice;
    private Identity type;
    private Stat pawnColor;

    public Player(Identity type) {
        this.type = type;

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

    public int getChoice() {
        return this.choice;
    }

    public void setChoice(int choice){
        this.choice = choice;
    }

    public Identity getType(){
        return this.type;
    }
}
