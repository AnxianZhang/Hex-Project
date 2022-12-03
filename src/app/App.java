package app;

import game.Plateau;
import game.Stat;

public class App {
    public static void main(String[] args) {
        Plateau p = new Plateau(4);
        p.play(0, Stat.WHITE);
        p.play(1, Stat.BLACK);
        p.play(2, Stat.BLACK);
        p.play(3, Stat.BLACK);
        p.play(4, Stat.BLACK);
        p.play(5, Stat.WHITE);
        p.play(6, Stat.WHITE);
        p.play(7, Stat.BLACK);
        p.play(8, Stat.WHITE);
        p.play(9, Stat.BLACK);
        p.play(10, Stat.WHITE);
        p.play(11, Stat.BLACK);
        p.play(12, Stat.WHITE);
        p.play(13, Stat.BLACK);
        p.play(14, Stat.WHITE);
        p.play(15, Stat.WHITE);
        System.out.println(p.isWin(Stat.WHITE));

        System.out.println(p);
    }
}
