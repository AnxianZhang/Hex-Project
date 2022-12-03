package app;


import game.Stat;
import player.Identity;


import java.util.Scanner;
public class IHM {

    private final static int NB_TYPE_JOUEUR = Identity.values().length;

    //HashMap<Integer, String> Choix_type_joueur = new HashMap<>();

    private Scanner sc = new Scanner(System.in);
    public IHM(){

    }
    public Identity recuperer_type_de_joueur(){
        System.out.println("Choissisez le type du joueur par un numéro" );
        afficher_choix_possible();


        while (true) {

            if(!IsInt()){}

            else  {
                int choix = sc.nextInt() ;
                if (choix <NB_TYPE_JOUEUR && choix >0 ){
                    return Identity.values()[choix];
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
        System.out.println("fdsfjhcaca");
        return true;
    }
    private void afficher_choix_possible(){

        for (int i = 0; i < NB_TYPE_JOUEUR ;++i){
            System.out.println("choix " + i + " " + Stat.values()[i].toString());
        }
    }
    public void afficher_resultat(Stat gagnant , Stat perdant){
        System.out.println("Les " + gagnant.name() + " ont gagner ");
        System.out.println("Les " + perdant.name() + " ont perdu");
    }
}
