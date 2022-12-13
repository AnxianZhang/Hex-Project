package player;

import game.Plateau;

public class IA extends Player{
    @Override
    public int getChoice(Plateau p) {
        return super.provider.provideIAMove(p);
    }
}
