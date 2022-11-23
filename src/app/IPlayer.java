package app;

import game.Stat;
import player.Identity;

public interface IPlayer {
    void setChoice(int inChoice);

    int getChoice();

    Identity getType();
    Stat getPawnColor();
}
