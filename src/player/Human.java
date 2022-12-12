package player;

import game.Plateau;

public class Human extends Player{
    @Override
    public int getChoice(Plateau p) {
        return super.provider.provide_coup_HUMAN(super.pawnColor,p);
    }
}