package app;

import game.Plateau;
import game.Stat;
import player.Identity;
import player.Player;

public interface IIHM {
    Identity recuperer_type_de_joueur();

    void afficher_resultat(String gagnant, String perdant);

    void mettre_a_jour_plateau(Plateau p);

    void afficher_le_coup(String nom_joueur, int choix);

    int demander_coup_a_jouer(Plateau p, String joueur);



}
