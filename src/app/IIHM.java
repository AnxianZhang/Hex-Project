package app;

import game.Plateau;
import player.Identity;
import player.Player;

public interface IIHM {
    Identity recuperer_type_de_joueur();

    void afficher_resultat(Player gagnant, Player perdant);

    void mettre_a_jour_plateau(Plateau p);

    void afficher_le_coup(Player joueur, int choix);

    int demander_coup_a_jouer(Plateau p, Player joueur);

    int demander_coup_a_jouer(Game g, Player joueur);
    void mettre_a_jour_plateau(Game g);

}
