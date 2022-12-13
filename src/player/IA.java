package player;

import game.Plateau;

/**
 * Brief: Classe permettan de creer des joueurs IA
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 3
 * @since 13/12/2022
 */
public class IA extends Player{
    @Override
    public int getChoice(Plateau p) {
        return super.provider.provideIAMove(p);
    }
}
