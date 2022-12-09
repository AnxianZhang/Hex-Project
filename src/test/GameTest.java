package test;

import IHM.IHM;
import app.Game;
import exeption.NotAdaptedFunction;

import org.junit.Test;
import player.Identity;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    /* le test entre hummain et ia n'est pas fesable */
    @Test
    public void testPlayerVsIA (){
        Game gIAIA = new Game(new Player(Identity.IA), new Player(Identity.IA), 3);

        // test des IA
        while (!gIAIA.isFull()){
            gIAIA.setPlayersChoice();
        }
    }

    @Test
    public void testPlayerVsPlayer(){
        Game gPlayerPlayer = new Game(new Player(Identity.HUMAN), new Player(Identity.HUMAN), 3);
        int [] human1Choice = {5, 3, 8, 0, 7};
        int [] human2Choice = {1, 2, 4, 6};
        int nbOfPawnPlayed1 = 0, nbOfPawnPlayed2 = 0;

        while (!gPlayerPlayer.isFull()){
            assertThrows(NotAdaptedFunction.class, gPlayerPlayer::setPlayersChoice);
            if (nbOfPawnPlayed1 < human1Choice.length){
                gPlayerPlayer.setPlayersChoice(human1Choice[nbOfPawnPlayed1]);
                ++nbOfPawnPlayed1;
            }

            if (nbOfPawnPlayed2 <human2Choice.length){
                gPlayerPlayer.setPlayersChoice(human2Choice[nbOfPawnPlayed2]);
                ++nbOfPawnPlayed2;
            }
        }

        assertEquals(
                """
                         A B C
                        1 W B B
                        2  W B W
                        3   B W W
                        """, gPlayerPlayer.toString());
    }
}