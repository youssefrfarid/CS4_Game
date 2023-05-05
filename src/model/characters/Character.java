package model.characters;

import java.awt.Point;

import engine.Game;
import model.world.CharacterCell;

public abstract class Character {
    private String name;
    private Point location;
    private int maxHp;
    private int currentHp;
    private int attackDmg;
    private Character target;

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp < 0)
            this.currentHp = 0;
        else if (currentHp <= this.maxHp)
            this.currentHp = currentHp;
        else
            this.currentHp = this.maxHp;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public Character getTarget() {
        return target;
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public Character(String name, int maxHp, int attackDmg) {
        this.name = name;
        this.maxHp = maxHp;
        this.attackDmg = attackDmg;
        this.currentHp = maxHp;
    }

    public void onCharacterDeath() {
        Game.map[this.getLocation().x][this.getLocation().y] = new CharacterCell(null);
        if (this instanceof Zombie) {

        } else {

        }
    }

    public void performAttack(CharacterCell c, boolean isZombie) {
        System.out.println(this);
        if (c.getCharacter() != null) {
            if (isZombie && c.getCharacter() instanceof Hero)
                c.getCharacter().setCurrentHp(c.getCharacter().getCurrentHp() - this.getAttackDmg());
            else if (!isZombie && c.getCharacter() instanceof Zombie)
                c.getCharacter().setCurrentHp(c.getCharacter().getCurrentHp() - this.getAttackDmg());
            else
                return;

            if (c.getCharacter().getCurrentHp() == 0) {
                c.getCharacter().onCharacterDeath();
            } else {
                c.getCharacter().defend(this);
            }
        }
    }

    public void attack() {
        // Get Position of Character
        int x = getLocation().x;
        int y = getLocation().y;

        // Check whether the character calling the function is a zombie or a hero
        boolean isZombie = false;
        if (this instanceof Zombie)
            isZombie = true;

        // Check all adjacent cells
        // Right
        if (x + 1 < 15 && Game.map[x + 1][y] instanceof CharacterCell && this.getCurrentHp() != 0) {
            CharacterCell c = (CharacterCell) Game.map[x + 1][y];
            performAttack(c, isZombie);
        }
        // Left
        if (x - 1 > -1 && Game.map[x - 1][y] instanceof CharacterCell && this.getCurrentHp() != 0) {
            CharacterCell c = (CharacterCell) Game.map[x - 1][y];
            performAttack(c, isZombie);
        }
        // Up
        if (y + 1 < 15 && Game.map[x][y + 1] instanceof CharacterCell && this.getCurrentHp() != 0) {
            CharacterCell c = (CharacterCell) Game.map[x][y + 1];
            performAttack(c, isZombie);
        }
        // Down
        if (y - 1 > -1 && Game.map[x][y - 1] instanceof CharacterCell && this.getCurrentHp() != 0) {
            CharacterCell c = (CharacterCell) Game.map[x][y - 1];
            performAttack(c, isZombie);
        }
        // Fo2 Yemen
        if (y + 1 < 15 && x + 1 < 15 && Game.map[x + 1][y + 1] instanceof CharacterCell && this.getCurrentHp() != 0) {
            CharacterCell c = (CharacterCell) Game.map[x + 1][y + 1];
            performAttack(c, isZombie);
        }
        // Fo2 Shemal
        if (y + 1 < 15 && x - 1 > -1 && Game.map[x - 1][y + 1] instanceof CharacterCell && this.getCurrentHp() != 0) {
            CharacterCell c = (CharacterCell) Game.map[x - 1][y + 1];
            performAttack(c, isZombie);
        }
        // taht yemen
        if (y - 1 > -1 && x + 1 < 15 && Game.map[x + 1][y - 1] instanceof CharacterCell && this.getCurrentHp() != 0) {
            CharacterCell c = (CharacterCell) Game.map[x + 1][y - 1];
            performAttack(c, isZombie);
        }
        // taht shemal
        if (y - 1 > -1 && x - 1 > -1 && Game.map[x - 1][y - 1] instanceof CharacterCell && this.getCurrentHp() != 0) {
            CharacterCell c = (CharacterCell) Game.map[x - 1][y - 1];
            performAttack(c, isZombie);
        }
    }

    public void defend(Character c) {
        c.setCurrentHp(c.getCurrentHp() - this.getAttackDmg() / 2);
        if (c.getCurrentHp() == 0) {
            c.onCharacterDeath();
        }
    }

    public static void main(String[] args) {
        Zombie x = new Zombie();
        Fighter h1 = new Fighter("H1", 200, 2, 10);
        h1.setLocation(new Point(14, 1));
        Fighter h2 = new Fighter("H2", 200, 2, 10);
        h2.setLocation(new Point(12, 1));
        Fighter h3 = new Fighter("H3", 2, 20, 10);
        h3.setLocation(new Point(13, 2));
        Fighter h4 = new Fighter("H4", 200, 2000, 10);
        h4.setLocation(new Point(13, 0));
        Fighter h5 = new Fighter("H5", 200, 20, 10);
        h5.setLocation(new Point(12, 0));

        Game.map[14][1] = new CharacterCell(h1, true);
        Game.map[12][1] = new CharacterCell(h2, true);
        Game.map[13][2] = new CharacterCell(h3, true);
        Game.map[13][0] = new CharacterCell(h4, true);
        Game.map[12][0] = new CharacterCell(h5, true);

        x.setLocation(new Point(13, 1));
        x.attack();
        CharacterCell x1 = (CharacterCell) Game.map[13][1];
        CharacterCell x2 = (CharacterCell) Game.map[13][2];

        System.out.println(x1.getCharacter());
        System.out.println(x2.getCharacter());
    }
}
