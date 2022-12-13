package player;

import Provider.MoveProvider;
import app.IPlayer;
import game.Stat;

/**
 * Brief: Classe Player permettan de creer des joueurs
 * diferent type de joueur
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 9
 * @since 13/12/2022
 */
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
