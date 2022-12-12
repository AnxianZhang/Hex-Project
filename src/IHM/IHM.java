package IHM;

import app.IIHM;
import game.Plateau;
import player.Identity;
import java.util.Scanner;

public class IHM implements IIHM {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public Identity recuperer_type_de_joueur(){
        System.out.println("Choissisez le type du joueur par un numéro" );
        afficher_choix_possible();

        int choix = demander_un_integer(Identity.values().length);

        return Identity.values()[choix];
    }

    private void afficher_choix_possible(){

        for (int i = 0; i < Identity.values().length ;++i){
            System.out.println("choix " + i + " pour l'" + Identity.values()[i].toString());
        }
    }

    @Override
    public void afficher_resultat(String gagnant, String perdant){
        System.out.println("Les " + gagnant + " ont gagner ");
        System.out.println("Les " + perdant + " ont perdu");
        System.out.println("La partie est finie");
    }

    @Override
    public void mettre_a_jour_plateau(Plateau p){
        System.out.println(p);
    }


    @Override
    public void afficher_le_coup(String player_name, int choix){
        System.out.println( player_name + " ont jouées à la case " + choix);
    }

    @Override
    public int demander_coup_a_jouer(Plateau p, String joueur){
        System.out.print("C'est au tour des " + joueur +" de jouer sur le plateau: ");
        return demander_un_integer(p.taille() * p.taille());
    }

    private int demander_un_integer(int borne_superieur){
        while (true) {
            if(!sc.hasNextInt()){
                System.out.print("Un chiffre est requis, saisissez votre choix :");
                sc.nextLine();
            }
            else  {
                int choix = sc.nextInt() ;
                if (choix < borne_superieur && choix >= 0){
                    return choix;
                }
                System.out.print("Un nombre valide est requis, saisissez votre choix : ");
                sc.nextLine();
            }
        }
    }
}
