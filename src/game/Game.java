package game;

import app.Fabrique;
import player.Identity;
import player.Player;

public class Game {
    private Player p1, p2;

    public Game(Identity p1, Identity p2){
        this.p1 = Fabrique.makePlayer(p1);
        this.p2 = Fabrique.makePlayer(p2);
    }

    public void play(Plateau p, Player player){

    }
}
