package player;

import Provider.MoveProvider;
import game.Plateau;
import game.Stat;

public abstract class Player{
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

    /**
     * Indique la couleur du pion
     *
     * @return la couleur du pion (WHITE,BLACK)
     */
    public Stat getPawnColor(){
        return this.pawnColor;
    }

    /**
     * Indique le choix du joueur
     *
     * @return retourne son choix
     */
    public abstract int getChoice(Plateau p) ;
}
