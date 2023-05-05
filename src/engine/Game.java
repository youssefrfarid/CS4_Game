package engine;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.Cell;

public class Game {
    public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
    public static ArrayList<Hero> heroes = new ArrayList<Hero>();
    public static ArrayList<Zombie> zombies = new ArrayList<Zombie>(10);
    public static Cell[][] map = new Cell[15][15];

    public static void loadHeroes(String filePath) throws Exception {
        Scanner sc = new Scanner(new File(filePath));

        while (sc.hasNext()) {
            String[] hero = sc.nextLine().split(",");
            String heroName = hero[0];
            String heroType = hero[1];
            int heroMaxHp = Integer.parseInt(hero[2]);
            int heroMaxActions = Integer.parseInt(hero[3]);
            int heroAttackDmg = Integer.parseInt(hero[4]);

            switch (heroType) {
                case "MED":
                    availableHeroes.add(new Medic(
                            heroName,
                            heroMaxHp,
                            heroAttackDmg,
                            heroMaxActions));
                    break;
                case "FIGH":
                    availableHeroes.add(new Fighter(
                            heroName,
                            heroMaxHp,
                            heroAttackDmg,
                            heroMaxActions));
                    break;
                case "EXP":
                    availableHeroes.add(new Explorer(
                            heroName,
                            heroMaxHp,
                            heroAttackDmg,
                            heroMaxActions));
                    break;
                default:
                    break;
            }
        }
    }

    public static void startGame(Hero h) {
        // spawn 5 vaccine 5 supply w 5 traps w 10 zombies randomly but not at (0,0)
        // load heroes then get a hero from the available heros add it to the heros
        // array and set its location to (0,0) remove it from available heros

    }

    public static boolean checkWin() {
        // Has 5 or more heros
        // collected and used all vaccines 5
        return true;
    }

    public static boolean checkGameOver() {
        // Has less than 5 heros
        // collected and used all vaccines 5
        // OR
        // All heros dead by zombies
        return true;
    }

    public static void endTurn() {
        // All zombies check adjacent cells for heros law la2a ye attack bas yeattack
        // wahed bas
        // Reset kol heros actions w special w target
        // nemsek kol hero w nekhaly el adjacent cells tb2a visible
        // randomly spawn a zombie f makan fadi
    }
}
