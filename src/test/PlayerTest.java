package test;

import app.IPlayer;
import game.Stat;
import org.junit.jupiter.api.Test;
import player.Human;
import player.IA;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void test(){
        IPlayer p1 = new IA();
        IPlayer p2 = new Human();

        assertEquals(Stat.WHITE, p1.getPawnColor());
        assertEquals(Stat.BLACK, p2.getPawnColor());
    }
}