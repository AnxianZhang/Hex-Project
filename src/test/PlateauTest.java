package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import exeption.Unplayable;
import game.Plateau;
import game.Stat;

public class PlateauTest {
    private final int taille = 4;

    /**
     * Tests de tout les methode (sauf isWin)
     *
     * @see Plateau#taille()
     * @see Plateau#getNbOfUsableCase()
     * @see Plateau#isFull()
     * @see Plateau#play(int, Stat)
     * @see Plateau#toString()
     */
    @Test
    public void toStringTest() {
        final int nbCases = taille * taille;
        Plateau p = new Plateau(taille);

        assertEquals(taille, p.taille());

        assertEquals(nbCases, p.getNbOfUsableCase());

        assertFalse(p.isFull());

        assertEquals(" A B C D\n" +
                     "1 . . . .\n" +
                     "2  . . . .\n" +
                     "3   . . . .\n" +
                     "4    . . . .\n", p.toString());

        p.play(0, Stat.WHITE);
        p.play(6, Stat.BLACK);

        assertEquals(nbCases - 2, p.getNbOfUsableCase());

        assertEquals(" A B C D\n" +
                "1 W . . .\n" +
                "2  . . B .\n" +
                "3   . . . .\n" +
                "4    . . . .\n", p.toString());

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

        assertEquals(" A B C D\n" +
                    "1 W W B W\n" +
                    "2  B W B B\n" +
                    "3   W B W B\n" +
                    "4    W B W B\n", p.toString());
    }

    /**
     * test de l'exception Unplayable
     *
     * @see Plateau#play(int, Stat)
     */
    @Test
    public void exceptionTest(){
        Plateau p = new Plateau(taille);
        assertThrows(Unplayable.class, () -> {
            p.play(60, Stat.WHITE);
            p.play(-99, Stat.BLACK);
            p.play(16, Stat.WHITE);
            p.play(0, Stat.BLACK);
        });
    }

    /**
     * tests de la methode isWin couvrant la victoire des
     * pions WHITE et BLACK
     * 
     * @see Plateau#play(int, Stat) 
     * @see Plateau#isWin() 
     */
    @Test
    public void testIsWin(){
        Plateau p = new Plateau(4);
        p.play(0, Stat.WHITE);
        assertEquals(Stat.EMPTY, p.isWin());
        p.play(1, Stat.WHITE);
        assertEquals(Stat.EMPTY, p.isWin());
        p.play(2, Stat.WHITE);
        assertEquals(Stat.EMPTY, p.isWin());
        p.play(3, Stat.WHITE);
        assertEquals(Stat.WHITE, p.isWin()); // les pions WHITE on gagne !

        Plateau pp=new Plateau(5);
        pp.play(0, Stat.BLACK);
        assertEquals(Stat.EMPTY, pp.isWin());
        pp.play(5, Stat.BLACK);
        assertEquals(Stat.EMPTY, pp.isWin());
        pp.play(10, Stat.BLACK);
        assertEquals(Stat.EMPTY, pp.isWin());
        pp.play(15, Stat.BLACK);
        assertEquals(Stat.EMPTY, pp.isWin());
        pp.play(20, Stat.BLACK);
        assertEquals(Stat.BLACK, pp.isWin());// les pions BLACK on gagne !

        Plateau pt=new Plateau(4);
        pt.play(0, Stat.BLACK);
        assertEquals(Stat.EMPTY, pt.isWin());
        pt.play(1,Stat.WHITE);
        assertEquals(Stat.EMPTY, pt.isWin());
        pt.play(4,Stat.BLACK);
        assertEquals(Stat.EMPTY, pt.isWin());
        pt.play(2,Stat.WHITE);
        assertEquals(Stat.EMPTY, pt.isWin());
        pt.play(8,Stat.BLACK);
        assertEquals(Stat.EMPTY, pt.isWin());
        pt.play(3,Stat.WHITE);
        assertEquals(Stat.EMPTY, pt.isWin());
        pt.play(12,Stat.BLACK);
        assertEquals(Stat.BLACK, pt.isWin());
        assertThrows(Unplayable.class, () -> {
            pt.play(0,Stat.WHITE);
        });
        assertEquals(Stat.BLACK, pt.isWin());

    }
}