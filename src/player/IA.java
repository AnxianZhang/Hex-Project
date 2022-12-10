package player;

import game.Plateau;

public class IA extends Player{

    public IA(Identity type) {
        super(type);
    }

    @Override
    public int getChoice(Plateau p) {
        return super.provider.provide_coup_AI(p);
    }
}
