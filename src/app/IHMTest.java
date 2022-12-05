package app;

import IHM.IIHM;
import IHM.IHM;
import org.junit.jupiter.api.Test;
import player.Identity;
import player.Player;

class IHMTest {
    private IIHM screen = new IHM();
    @Test
    void recuperer_type_de_joueur() {

        screen.recuperer_type_de_joueur();
    }

    @Test
    void afficher_resultat() {
        screen.afficher_resultat(new Player(Identity.HUMAN), new Player(Identity.HUMAN));
    }
}