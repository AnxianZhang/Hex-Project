package game;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Player {
    private final static int MAX_PLAYERS = 2;
    private final int numPlayer;
    private static int nbPlayer = 0;

    public Player (){
        assert nbPlayer < MAX_PLAYERS;
        this.numPlayer = ++nbPlayer;
    }

    public void play(Plateau p, int numCase){
        assert numCase < Math.pow(p.taille(), 2) - 1 && numCase >= 0;
        if (p.isPlayable(numCase)){
            p.play(numCase, this.numPlayer);
        }
    }
}
