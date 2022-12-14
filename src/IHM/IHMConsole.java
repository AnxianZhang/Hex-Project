package IHM;

import app.IIHM;
import game.Plateau;
import player.Identity;
import java.util.Scanner;

/**
 * Brief: Classe IHMConsole, permet tout interaction entre
 * le(s) joueur(s) et le jeu
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 8
 * @since 13/12/2022
 */
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
     * @param maxVal debut du plateau
     * @param minVal fin du plateau
     *
     * @return retourne un entier qui correspond au choix du joueur
     */
    private int requestAnInteger(int maxVal, int minVal){
        while (true) {
            if(!sc.hasNextInt()){
                System.out.print("Un chiffre est requis, saisissez votre choix :");
                sc.nextLine();
            }
            else  {
                int choice = sc.nextInt() ;
                if (choice < minVal && choice >= maxVal){
                    return choice;
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

        int choice = requestAnInteger(0,Identity.values().length);

        return Identity.values()[choice];
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
        System.out.print("Veuillez indiquer la taille de la largeur du plateau entre 2 et 100 inclus: ");
        return requestAnInteger(2,51);//taille minimale et maximale de la largeur du plateau
    }

    @Override
    public void caseAlreadyUsed(Plateau p) {
        System.out.println("La case que vous avez donnée contient deja un pion !");
        System.out.println("Choisisez une case valide.");
    }
}