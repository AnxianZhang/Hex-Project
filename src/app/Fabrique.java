package app;

import player.Human;
import player.IA;
import player.Identity;
import player.Player;

public class Fabrique {
    public static Player makePlayer(Identity id){
        return id == Identity.HUMAIN ? new Human() : new IA();
    }
}
