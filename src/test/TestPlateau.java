package test;

import exeption.Unplayable;
import game.Plateau;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestPlateau {
    @Test
    void test() {
        final int taille = 4;
        Plateau p = new Plateau(taille);
        assertEquals(taille, p.taille());

        assertEquals(
                """
                         A B C D
                        1 . . . .
                        2  . . . .
                        3   . . . .
                        4    . . . .
                        """, p.toString());

        assertTrue(p.isPlayable(6));
        assertTrue(p.isPlayable(0));
        assertTrue(p.isPlayable(p.taille() - 1));

        assertThrows(Unplayable.class, () -> {
            p.isPlayable(60);
            p.isPlayable(-99);
        });
    }
}