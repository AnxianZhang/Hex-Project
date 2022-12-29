package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import game.Unplayable;
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
     * tests de la methode isWin sur un plateau qui contient que des
     * pions d'une seul couleur
     * /!\ --> ce cas est impossible en temps reel, mais il faut la
     *         tester afin d'assurer le bon fonctionement de la
     *         fonction
     * 
     * @see Plateau#play(int, Stat) 
     * @see Plateau#winner()
     */
    @Test
    public void testIsWinForOneColorOnAllCases(){
        Plateau p = new Plateau(4);
        p.play(0, Stat.WHITE);
        assertEquals(Stat.EMPTY, p.winner());
        p.play(1, Stat.WHITE);
        assertEquals(Stat.EMPTY, p.winner());
        p.play(2, Stat.WHITE);
        assertEquals(Stat.EMPTY, p.winner());
        p.play(3, Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner()); // les pions WHITE on gagne !
        p.play(4,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(5,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(6,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(7,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(8,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(9,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(10,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(11,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(12,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(13,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(14,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());
        p.play(15,Stat.WHITE);
        assertEquals(Stat.WHITE, p.winner());//WHITE ds tte le plateau
    }

    /**
     * Test de la fonction sur une couleur de pion
     *
     * @see Plateau#play(int, Stat)
     * @see Plateau#winner() ()
     */
    @Test
    public void testIsWinForBLACK(){
        Plateau pp = new Plateau(5);
        pp.play(0, Stat.BLACK);
        assertEquals(Stat.EMPTY, pp.winner());
        pp.play(5, Stat.BLACK);
        assertEquals(Stat.EMPTY, pp.winner());
        pp.play(10, Stat.BLACK);
        assertEquals(Stat.EMPTY, pp.winner());
        pp.play(15, Stat.BLACK);
        assertEquals(Stat.EMPTY, pp.winner());
        pp.play(20, Stat.BLACK);
        assertEquals(Stat.BLACK, pp.winner());// les pions BLACK on gagne !
    }

    /**
     * Test de isWin sur une partie
     *
     * @see Plateau#play(int, Stat)
     * @see Plateau#winner() ()
     */
    @Test
    public void testIsWinForOneGame(){
        Plateau pt = new Plateau(4);
        pt.play(0, Stat.BLACK);
        assertEquals(Stat.EMPTY, pt.winner());
        pt.play(1,Stat.WHITE);
        assertEquals(Stat.EMPTY, pt.winner());
        pt.play(4,Stat.BLACK);
        assertEquals(Stat.EMPTY, pt.winner());
        pt.play(2,Stat.WHITE);
        assertEquals(Stat.EMPTY, pt.winner());
        pt.play(8,Stat.BLACK);
        assertEquals(Stat.EMPTY, pt.winner());
        pt.play(3,Stat.WHITE);
        assertEquals(Stat.EMPTY, pt.winner());
        pt.play(12,Stat.BLACK);
        assertEquals(Stat.BLACK, pt.winner());// les pions BLACK on gagne !

        assertThrows(Unplayable.class, () -> pt.play(0,Stat.WHITE));

        // apres que BLACK ont gagne, WHITE play (cas impossible mais faut le tester !)
        pt.play(5,Stat.WHITE);
        assertEquals(Stat.BLACK, pt.winner());//tjr BLACK gagne !
    }
}