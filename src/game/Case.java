package game;

/**
 * Brief: Classe Case permettant de crer les
 * case d'un plateau
 * @author Anxian ZHANG, Nathan COLLOMBET,
 *         Xingtong LIN, Redouane OUASTI
 * @version 4
 * @since 13/12/2022
 */
public class Case {
    private Stat stat;
    private boolean isChecked = false;

    public Case (){
        this.stat = Stat.EMPTY;
    }

    /**
     * Renvoie le statut de la case (BLACK, WHITE, EMPTY)
     *
     * @return la stat
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * Renvoie un boolean qui indique si la case a deja ete
     * verifie
     *
     * @return son etat
     */
    public boolean isChecked() {
        return this.isChecked;
    }

    /**
     * Change le statut de la case
     *
     * @param isChecked le nouvel statut
     */
    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    /**
     * Change la contenance de la case (elle peut etre WHITE BLACK ou EMPTY)
     *
     * @param pawnColor nouveau contenue
     */
    public void play(Stat pawnColor) {
        this.stat = pawnColor;
    }
}
