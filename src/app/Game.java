package app;

import app.Fabrique;
import game.Plateau;
import player.Identity;
import player.Player;

public class Game {
    private final IPlayer p1, p2;
    private final Plateau plateau;

    public Game(Identity p1, Identity p2, int size){
        this.p1 = Fabrique.makePlayer(p1);
        this.p2 = Fabrique.makePlayer(p2);

        this.plateau = new Plateau(size);
    }

    public void playersChoice(int p1Choice, int p2Choice){
        this.p1.setChoice(p1Choice);
        this.p2.setChoice(p2Choice);
    }

    public boolean stillPlayable(){
        return this.plateau.isFull();
    }

    public void play() {
        this.plateau.play(this.p1.getChoice(), this.p1.getPawnColor());
        this.plateau.play(this.p2.getChoice(), this.p2.getPawnColor());
    }
}
