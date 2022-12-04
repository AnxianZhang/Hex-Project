package app;


import game.Plateau;
import game.Stat;
import player.Identity;
import player.Player;


import java.util.Scanner;
public class IHM {



    //HashMap<Integer, String> Choix_type_joueur = new HashMap<>();

    private Scanner sc = new Scanner(System.in);
    public IHM(){

    }
    public Identity recuperer_type_de_joueur(){
        final int  PREMIER_ELEMENT = 0;

        System.out.println("Choissisez le type du joueur par un numéro" );
        afficher_choix_possible();

        int choix = demander_un_integer( PREMIER_ELEMENT, Identity.values().length);

        return Identity.values()[choix];
    }


    private void afficher_choix_possible(){

        for (int i = 0; i < Identity.values().length ;++i){
            System.out.println("choix " + i + " " + Stat.values()[i].toString());
        }
    }
    public void afficher_resultat(Player gagnant , Player perdant){
        System.out.println("Les " + gagnant.getPawnColor().name() + " ont gagner ");
        System.out.println("Les " + perdant.getPawnColor().name() + " ont perdu");
    }

    public void afficher_le_coup(Player joueur , int choix){
        System.out.println( joueur.getPawnColor() + "ont jouées à la case " + choix);

    }

    public int demander_coup_a_jouer(Plateau p){
        final int  PREMIERE_CASE_DU_PLATEAU = 0;
        System.out.println("Choississez une case valide a jouer sur le plateau");
        return demander_un_integer(PREMIERE_CASE_DU_PLATEAU,p.taille());
    }


    private int demander_un_integer(int borne_inferieur_incluse, int borne_superieur){
        while (true) {

            if(!IsInt()){}

            else  {
                int choix = sc.nextInt() ;
                if (choix < borne_superieur && choix >=borne_inferieur_incluse ){
                    return choix;
                }
                System.out.print("Un nombre valide est requis, saisissez votre choix : ");
                sc.nextLine();

            }
        }
    }

    private boolean IsInt(){
        if (!sc.hasNextInt()) {

            System.out.print("Un chiffre est requis, saisissez votre choix :");
            sc.nextLine();
            return false;

        }

        return true;
    }
}
