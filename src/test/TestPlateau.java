package test;

import app.Fabrique;
import exeption.Unplayable;
import game.Plateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import player.IA;
import player.Identity;
import player.Player;

class TestPlateau {
    @Test
    void test() {
        final int taille = 4, nbCases = taille * taille;
        Plateau p = new Plateau(taille);

        Player p1 = Fabrique.makePlayer(Identity.HUMAIN);
        Player p2 = Fabrique.makePlayer(Identity.IA);

        assertEquals(taille, p.taille());

        assertEquals(nbCases, p.getNbOfUsableCase());

        assertFalse(p.isFull());

        assertEquals(
                """
                         A B C D
                        1 . . . .
                        2  . . . .
                        3   . . . .
                        4    . . . .
                        """, p.toString());

        p.play(0, p1);
        p.play(6, p2);

        assertEquals(nbCases - 2, p.getNbOfUsableCase());

        assertEquals(
                """
                         A B C D
                        1 W . . .
                        2  . . B .
                        3   . . . .
                        4    . . . .
                        """, p.toString());

        assertThrows(Unplayable.class, () -> {
            p.play(60, p1);
            p.play(-99, p2);
            p.play(16, p1);
            p.play(0, p2);
        });

        // pour les cases 10 et 6
        assertTrue(p.isEmpty(10 / p.taille(),  10 % p.taille()));
        assertFalse(p.isEmpty(6 / p.taille(), 6 % p.taille()));

        p.play(1, p1);
        p.play(2, p2);
        p.play(3, p1);
        p.play(4, p2);
        p.play(5, p1);
        p.play(7, p2);
        p.play(8, p1);
        p.play(9, p2);
        p.play(10, p1);
        p.play(11, p2);
        p.play(12, p1);
        p.play(13, p2);
        p.play(14, p1);
        p.play(15, p2);

        assertTrue(p.isFull());
    }
}