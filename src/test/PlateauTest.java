package test;

import exeption.Unplayable;
import game.Plateau;

import static org.junit.jupiter.api.Assertions.*;

import game.Stat;
import org.junit.jupiter.api.Test;

class PlateauTest {
    @Test
    void test() {
        final int taille = 4, nbCases = taille * taille;
        Plateau p = new Plateau(taille);

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

        p.play(0, Stat.WHITE);
        p.play(6, Stat.BLACK);

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
            p.play(60, Stat.WHITE);
            p.play(-99, Stat.BLACK);
            p.play(16, Stat.WHITE);
            p.play(0, Stat.BLACK);
        });

        p.play(1, Stat.WHITE);
        p.play(2, Stat.BLACK);
        p.play(3, Stat.WHITE);
        p.play(4, Stat.BLACK);
        p.play(5, Stat.WHITE);
        p.play(7, Stat.BLACK);
        p.play(8, Stat.WHITE);
        p.play(9, Stat.BLACK);
        p.play(10, Stat.WHITE);
        p.play(11, Stat.BLACK);
        p.play(12, Stat.WHITE);
        p.play(13, Stat.BLACK);
        p.play(14, Stat.WHITE);
        p.play(15, Stat.BLACK);

        assertTrue(p.isFull());

        assertEquals(
                """
                         A B C D
                        1 W W B W
                        2  B W B B
                        3   W B W B
                        4    W B W B
                        """, p.toString());
    }
}