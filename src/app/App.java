package app;

import IHM.IHM;
import game.Plateau;
import game.Stat;
import player.Identity;
import player.Player;

public class App {
    public static void main(String[] args) {
        IIHM ihm=new IHM();
        Player p1=new Player(ihm.recuperer_type_de_joueur());

        //System.out.println(ihm.recuperer_type_de_joueur());
    }
}
