package app;

import org.junit.jupiter.api.Test;
import player.Identity;

import static org.junit.jupiter.api.Assertions.*;

class IHMTest {

    @Test
    void recuperer_type_de_joueur() {

        new IHM().recuperer_type_de_joueur();
    }
}