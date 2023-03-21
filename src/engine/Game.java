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
    public static Cell[][] map;

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

    public static void main(String[] args) throws Exception {
        loadHeroes("Heros.csv");
        for (int i = 0; i < availableHeroes.size(); i++) {
            System.out.println(availableHeroes.get(i).getName());
        }

        for (int i = 0; i < zombies.size(); i++) {
            System.out.println(zombies.get(i).getName());
        }
    }
}
