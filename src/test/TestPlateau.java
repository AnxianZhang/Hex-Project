package test;

import app.Fabrique;
import exeption.Unplayable;
import game.Plateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import player.Identity;
import player.Player;

class TestPlateau {
    // test de tout
    @Test
    void test() {
        final int taille = 4;
        Plateau p = new Plateau(taille);

        Player p1 = Fabrique.makePlayer(Identity.HUMAIN);
        Player p2 = Fabrique.makePlayer(Identity.IA);

        assertEquals(taille, p.taille());

        assertEquals(
                """
                         A B C D
                        1 . . . .
                        2  . . . .
                        3   . . . .
                        4    . . . .
                        """, p.toString());

        p.play(0, p1.getPawnColor());
        p.play(6, p2.getPawnColor());

        assertEquals(
                """
                         A B C D
                        1 W . . .
                        2  . . B .
                        3   . . . .
                        4    . . . .
                        """, p.toString());

        assertThrows(Unplayable.class, () -> {
            p.play(60, p1.getPawnColor());
            p.play(-99, p2.getPawnColor());
            p.play(16, p1.getPawnColor());
        });

        assertTrue(p.isEmpty(10 / p.taille(),  10 % p.taille()));
        assertFalse(p.isEmpty(6 / p.taille(), 6 % p.taille()));
    }
}