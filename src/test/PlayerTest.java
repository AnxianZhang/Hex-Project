package test;

import app.Fabrique;
import game.Plateau;
import game.Stat;
import org.junit.Test;
import player.IA;
import player.Identity;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void test(){
        Player ia = Fabrique.makePlayer(Identity.IA);
        Player human = Fabrique.makePlayer(Identity.HUMAIN);
        Plateau p = new Plateau(3);

        assertEquals(Identity.IA, ia.getType());
        assertEquals(Identity.HUMAIN, human.getType());
        assertEquals(Stat.WHITE, ia.getPawnColor());
        assertEquals(Stat.BLACK, human.getPawnColor());


        p.play(0, human.getPawnColor());
        p.play(1, human.getPawnColor());
        p.play(2, human.getPawnColor());
        p.play(3, human.getPawnColor());
        p.play(7, human.getPawnColor());

        for (int i = 0; i <= p.taille(); ++i){
            ((IA)ia).makeChoice(p);
            p.play(ia.getChoice(), ia.getPawnColor());
        }

        assertEquals("""
                         A B C
                        1 B B B
                        2  B W W
                        3   W B W
                        """, p.toString());
}
}