package game;

public class Case {
    private Stat stat;
    private boolean isChecked = false;

    public Case (){
        this.stat = Stat.EMPTY;
    }

    /**
     * Méthode qui renvoie le statut de la case (BLACK, WHITE, VIDE)
     *
     * @return retourne un objet de la classe enum Stat
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * Méthode qui renvoie si la case a été vérifié ou non
     * de base à false
     *
     * @return retourne un booléen : true si case vérifiée sinon false
     */
    public boolean isChecked() {
        return this.isChecked;
    }

    /**
     * Méthode qui permet de changer le statut de la case si on la vérifiée
     *
     * @param isChecked dire si on mets true ou false à la case
     */
    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    /**
     * Méthode qui permet de changer le statut de la case en posant un pion
     * de la couleur indiquée en paramètre
     *
     * @param pawnColor couleur du pion qu'on veut poser
     */
    public void play(Stat pawnColor) {
        this.stat = pawnColor;
    }
}
