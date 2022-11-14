package game;

public class Player {
    private int numPlayer;
    private static int nbPlayer = 0;

    public Player (){
        assert nbPlayer < 2;
        this.numPlayer = ++nbPlayer;
    }

    public void play(Plateau p, int numCase){
        assert numCase < p.taille() - 1 && numCase >= 0;
        if (p.isPlayable(numCase)){
            p.play(numCase, this.numPlayer);
        }
    }
}
