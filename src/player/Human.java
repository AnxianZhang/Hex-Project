package player;

import game.Plateau;

/**
 * Brief: Classe permettan de creer des joueurs HUMAN
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 7
 * @since 13/12/2022
 */
public class Human extends Player{
    @Override
    public int getChoice(Plateau p) {
        return super.provider.provideHumanMove(super.pawnColor,p);
    }
}