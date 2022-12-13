package player;

import game.Plateau;

public class Human extends Player{
    @Override
    public int getChoice(Plateau p) {
        return super.provider.provideHumanMove(super.pawnColor,p);
    }
}