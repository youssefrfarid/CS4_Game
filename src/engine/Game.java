package engine;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

import java.util.Random;
import java.awt.Point;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {
    public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
    public static ArrayList<Hero> heroes = new ArrayList<Hero>();
    public static ArrayList<Zombie> zombies = new ArrayList<Zombie>(10);
    public static ArrayList<Point> points = new ArrayList<Point>(225);
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

    public static int getVaccinesMapCount() {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] instanceof CollectibleCell) {
                    CollectibleCell c = (CollectibleCell) map[i][j];
                    if (c.getCollectible() instanceof Vaccine)
                        count++;
                }
            }
        }
        return count;
    }

    public static int getVaccineInventoryCount() {
        int count = 0;
        for (Hero hero : heroes) {
            count += hero.getVaccineInventory().size();
        }
        return count;
    }

    public static int generateRandomNumber(int Number) {
        Random random = new Random();
        return random.nextInt(Number);
    }

    public static void fillPoints() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                points.add(new Point(i, j));
            }
        }
        points.remove(0);
    }

    public static Point getEmptyRandomCell() {
        int x, y;

        while (true) {
            x = generateRandomNumber(15);
            y = generateRandomNumber(15);
            if ((x != 0 && y != 0) && Game.map[x][y] == null)
                break;
        }
        return new Point(x, y);
    }

    public static void startGame(Hero h) {
        fillPoints();
        // spawn 5 vaccine 5 supply w 5 traps w 10 zombies randomly but not at (0,0)
        for (int i = 0; i < 5; i++) {
            Point p1 = points.get(generateRandomNumber(points.size()));
            points.remove(p1);
            Game.map[p1.x][p1.y] = new CollectibleCell(new Vaccine());
            Point p2 = points.get(generateRandomNumber(points.size()));
            points.remove(p2);
            Game.map[p2.x][p2.y] = new CollectibleCell(new Supply());
            Point p3 = points.get(generateRandomNumber(points.size()));
            points.remove(p3);
            Game.map[p3.x][p3.y] = new TrapCell();
        }
        for (int i = 0; i < 10; i++) {
            Zombie z = new Zombie();
            zombies.add(z);
            Point p = points.get(generateRandomNumber(points.size()));
            points.remove(p);
            z.setLocation(p);
            Game.map[p.x][p.y] = new CharacterCell(z);
        }
        // load heroes then get a hero from the available heros add it to the heros
        availableHeroes.remove(h);
        heroes.add(h);
        // array and set its location to (0,0) remove it from available heros
        h.setLocation(new Point(0, 0));
        Game.map[0][0] = new CharacterCell(h);
        Game.map[0][0].setVisible(true);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (Game.map[i][j] == null)
                    Game.map[i][j] = new CharacterCell(null);
            }
        }
        Game.map[0][1].setVisible(true);
        Game.map[1][1].setVisible(true);
        Game.map[1][0].setVisible(true);
    }

    public static boolean checkWin() {
        // Has 5 or more heros
        // collected and used all vaccines 5
        if (heroes.size() >= 5 && getVaccinesMapCount() == 0 && getVaccineInventoryCount() == 0)
            return true;
        return false;
    }

    public static boolean checkGameOver() {
        if (getVaccinesMapCount() > 0)
            return false;

        if (getVaccineInventoryCount() > 0)
            return false;
        // Has less than 5 heros
        // collected and used all vaccines 5
        if (heroes.size() < 5 || getVaccinesMapCount() == 0 && getVaccineInventoryCount() == 0)
            return true;
        if (getVaccinesMapCount() == 0 && getVaccineInventoryCount() == 0 && !zombies.isEmpty())
            return true;
        // OR
        // All heros dead by zombies
        if (heroes.size() == 0)
            return true;
        return false;
    }

    public static void endTurn() throws InvalidTargetException, NotEnoughActionsException {
        // All zombies check adjacent cells for heros law la2a ye attack bas yeattack
        // wahed bas
        // Reset kol heros actions w special w target
        // nemsek kol hero w nekhaly el adjacent cells tb2a visible
        // randomly spawn a zombie f makan fadi
        for (Zombie zombie : zombies) {
            int x = (int) zombie.getLocation().getX();
            int y = (int) zombie.getLocation().getY();

            ArrayList<Cell> adjacents = getAdjacentCells(x, y);
            while (adjacents.size() != 0) {
                Cell c = adjacents.remove(generateRandomNumber(adjacents.size()));
                if (c instanceof CharacterCell) {
                    CharacterCell c1 = (CharacterCell) c;
                    if (c1.getCharacter() != null && c1.getCharacter() instanceof Hero) {
                        zombie.setTarget(c1.getCharacter());
                        zombie.attack();
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j].setVisible(false);
            }
        }

        for (Hero hero : heroes) {
            hero.setActionsAvailable(hero.getMaxActions());
            hero.setSpecialAction(false);
            hero.setTarget(null);
            Point p = hero.getLocation();
            ArrayList<Cell> adjacents = getAdjacentCells(p.x, p.y);
            Game.map[p.x][p.y].setVisible(true);
            for (Cell cell : adjacents) {
                cell.setVisible(true);
            }
        }
        int x, y;
        while (true) {
            x = Game.generateRandomNumber(15);
            y = Game.generateRandomNumber(15);
            if (Game.map[x][y] instanceof CharacterCell) {
                CharacterCell c = (CharacterCell) Game.map[x][y];
                if (c.getCharacter() == null)
                    break;
            }
        }
        Zombie z = new Zombie();
        zombies.add(z);
        z.setLocation(new Point(x, y));
        CharacterCell c = (CharacterCell) Game.map[x][y];
        c.setCharacter(z);

        for (Zombie zombie : zombies) {
            zombie.setTarget(null);
        }
    }

    public static ArrayList<Cell> getAdjacentCells(int x, int y) {
        ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
        // right
        if (x + 1 < 15)
            adjacentCells.add(Game.map[x + 1][y]);
        // left
        if (x - 1 > -1)
            adjacentCells.add(Game.map[x - 1][y]);
        // up
        if (y + 1 < 15)
            adjacentCells.add(Game.map[x][y + 1]);
        // down
        if (y - 1 > -1)
            adjacentCells.add(Game.map[x][y - 1]);
        // right up
        if (x + 1 < 15 && y + 1 < 15)
            adjacentCells.add(Game.map[x + 1][y + 1]);
        // left up
        if (x - 1 > -1 && y + 1 < 15)
            adjacentCells.add(Game.map[x - 1][y + 1]);
        // right down
        if (y - 1 > -1 && x + 1 < 15)
            adjacentCells.add(Game.map[x + 1][y - 1]);
        // left down
        if (y - 1 > -1 && x - 1 > -1)
            adjacentCells.add(Game.map[x - 1][y - 1]);

        return adjacentCells;
    }

    public static void main(String[] args) throws Exception {
        loadHeroes("Heros.csv");
        startGame(availableHeroes.get(0));
        System.out.println(getVaccinesMapCount());
    }
}
