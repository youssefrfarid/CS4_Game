package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.Cell;
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
        CharacterCell c = (CharacterCell) Game.map[this.getLocation().x][this.getLocation().y];
        c.setCharacter(null);
    }

    public void attack() throws InvalidTargetException, NotEnoughActionsException {
        if (this.getTarget() == null && this instanceof Hero) {
            throw new InvalidTargetException("You dont have a target");
        }
        // Get Position of Character
        int x = getLocation().x;
        int y = getLocation().y;
        // Check whether the character calling the function is a zombie or a hero
        ArrayList<Cell> adjacentCells = Game.getAdjacentCells(x, y);

        for (int i = 0; i < adjacentCells.size(); i++) {
            if (adjacentCells.get(i) instanceof CharacterCell) {
                CharacterCell c = (CharacterCell) adjacentCells.get(i);
                if (c.getCharacter() == this.getTarget()
                        && ((this instanceof Hero && c.getCharacter() instanceof Zombie)
                                || (this instanceof Zombie && c.getCharacter() instanceof Hero))) {
                    c.getCharacter().setCurrentHp(c.getCharacter().getCurrentHp() - this.getAttackDmg());
                    c.getCharacter().defend(this);
                    if (c.getCharacter().getCurrentHp() == 0)
                        c.getCharacter().onCharacterDeath();
                    return;
                }
                if (this instanceof Zombie && c.getCharacter() instanceof Hero) {
                    c.getCharacter().setCurrentHp(c.getCharacter().getCurrentHp() - this.getAttackDmg());
                    c.getCharacter().defend(this);
                    if (c.getCharacter().getCurrentHp() == 0)
                        c.getCharacter().onCharacterDeath();
                    return;
                }
            }
        }

        if (this instanceof Hero) {
            if (Game.isAdjacent(this, this.getTarget()) && this.getTarget() instanceof Zombie) {
                this.getTarget().setCurrentHp(this.getTarget().getCurrentHp() - this.getAttackDmg());
                this.getTarget().defend(this);
                if (this.getTarget().getCurrentHp() == 0)
                    this.getTarget().onCharacterDeath();
                return;
            }
        }
        throw new InvalidTargetException("Cannot attack that character");
    }

    public void defend(Character c) {
        this.setTarget(c);
        c.setCurrentHp(c.getCurrentHp() - this.getAttackDmg() / 2);
        if (c.getCurrentHp() == 0)
            c.onCharacterDeath();
    }
}
