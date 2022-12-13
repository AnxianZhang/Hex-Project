package IHM;

import app.IIHM;
import game.Plateau;
import player.Identity;
import java.util.Scanner;

public class IHMConsole implements IIHM {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Affiche à l'utilisateur les choix
     * de type de joueur qui peut choisir. C'est une méthode d'affichage.
     *
     * @see Identity#values()
     * @see Identity#toString()
     */
    private void showAllPossibleChoices(){
        for (int i = 0; i < Identity.values().length; ++i){
            System.out.println("choix " + i + " pour l'" + Identity.values()[i].toString());
        }
    }

    /**
     * Recupere la valeur de la position où le joueur veut jouer
     *
     * @param borne_inferieure_inclue debut du plateau
     * @param borne_superieur fin du plateau
     *
     * @return retourne un entier qui correspond au choix du joueur
     */
    private int requestAnInteger(int borne_inferieure_inclue, int borne_superieur){
        while (true) {
            if(!sc.hasNextInt()){
                System.out.print("Un chiffre est requis, saisissez votre choix :");
                sc.nextLine();
            }
            else  {
                int choix = sc.nextInt() ;
                if (choix < borne_superieur && choix >= borne_inferieure_inclue){
                    return choix;
                }
                System.out.print("Un nombre valide est requis, saisissez votre choix : ");
                sc.nextLine();
            }
        }
    }

    @Override
    public Identity requestPlayerType(){
        System.out.println("Choissisez le type du joueur par un numéro" );
        showAllPossibleChoices();

        int choix = requestAnInteger(0,Identity.values().length);

        return Identity.values()[choix];
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
    public int requestAMove(Plateau p, String player){
        System.out.print("C'est au tour des " + player +" de jouer sur le plateau: ");
        return requestAnInteger(0,p.taille() * p.taille());
    }

    @Override
    public int requestPlateauSize() {
        System.out.println("Veuillez indiquer la taille de la largeur du plateau entre 2 et 100 inclus");
        return requestAnInteger(2,51);//taille minimale et maximale de la largeur du plateau
    }
}
