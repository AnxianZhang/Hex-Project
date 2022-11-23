package test;

import app.Game;
import org.junit.jupiter.api.Test;
import player.Identity;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void test (){
        Game g = new Game(Identity.HUMAIN, Identity.IA, 3);
        int [] hummanChoice = {5, 3, 8, 0};
        int nbOfPawnPlayed = 0;
        while (!g.stillPlayable()){
            //g.playersChoice();
            g.play();
        }
    }
}