package game;

import exeption.Unplayable;

public interface Rule {
    void play(Plateau p ,int numCase, Stat s) throws Unplayable;
    Stat winner(Plateau p);
}
