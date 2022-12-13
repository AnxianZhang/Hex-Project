package player;

import game.Stat;

public class Player{
    private static boolean isFirstColor = true;
    private int choice;
    private final boolean isIA;
    private final Stat pawnColor;

    /**
     *
     * @param type
     */
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

    public int getChoice() {
        return this.choice;
    }

    public void setChoice(int inChoice){
        this.choice = inChoice;
    }

    public boolean getIsIA(){
        return this.isIA;
    }
}
