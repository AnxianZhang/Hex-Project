package app;

import game.Stat;
import org.junit.jupiter.api.Test;
import player.Identity;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class IHMTest {
    private IHM screen = new IHM();
    @Test
    void recuperer_type_de_joueur() {

        screen.recuperer_type_de_joueur();
    }

    @Test
    void afficher_resultat() {
        screen.afficher_resultat(new Player(Identity.HUMAN), new Player(Identity.HUMAN));
    }
}