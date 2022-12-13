package IHM;

import app.IIHM;
import game.Plateau;
import player.Identity;
import java.util.Scanner;

public class IHM implements IIHM {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public Identity requestPlayerTypr(){
        System.out.println("Choissisez le type du joueur par un numéro" );
        afficher_choix_possible();

        int choix = demander_un_integer(Identity.values().length);

        return Identity.values()[choix];
    }

    /**
     * Cette méthode permet simplement d'afficher à l'utilisateur les choix
     * de type de joueur qui peut choisir. C'est une méthode d'affichage.
     *
     * @see Identity#values()
     * @see Identity#toString()
     */
    private void afficher_choix_possible(){

        for (int i = 0; i < Identity.values().length ;++i){
            System.out.println("choix " + i + " pour l'" + Identity.values()[i].toString());
        }
    }

    @Override
    public void showResult(String winner, String looser){
        System.out.println("Les " + winner + " ont gagner ");
        System.out.println("Les " + looser + " ont perdu");
        System.out.println("La partie est finie");
    }

    @Override
    public void refreshPlateau(Plateau p){
        System.out.println(p);
    }

    @Override
    public void showPlayedPosition(String playerPawnColor, int choice){
        System.out.println(playerPawnColor + " ont jouées à la case " + choice);
    }

    @Override
    public int resquestAMove(Plateau p, String player){
        System.out.print("C'est au tour des " + player +" de jouer sur le plateau: ");
        return demander_un_integer(p.taille() * p.taille());
    }

    /**
     * Cette méthode va récupérer la valeur de la position où le joueur veut jouer
     *
     * @param borne_superieur fin du plateau
     *
     * @return retourne un entier qui correspond au choix du joueur
     */
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
