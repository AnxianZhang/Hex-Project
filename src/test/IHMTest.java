package test;

import app.IIHM;
import IHM.IHM;
import org.junit.jupiter.api.Test;
import player.Human;
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
        screen.afficher_resultat(Identity.HUMAN.name(), Identity.HUMAN.name());
    }
}