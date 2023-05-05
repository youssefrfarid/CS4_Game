package model.characters;

import java.util.ArrayList;

import model.collectibles.Supply;
import model.collectibles.Vaccine;

public abstract class Hero extends Character {
    private int actionsAvailable;
    private int maxActions;
    private boolean specialAction;
    private ArrayList<Vaccine> vaccineInventory;
    private ArrayList<Supply> supplyInventory;

    public int getActionsAvailable() {
        return actionsAvailable;
    }

    public void setActionsAvailable(int actionsAvailable) {
        this.actionsAvailable = actionsAvailable;
    }

    public int getMaxActions() {
        return maxActions;
    }

    public boolean isSpecialAction() {
        return specialAction;
    }

    public void setSpecialAction(boolean specialAction) {
        this.specialAction = specialAction;
    }

    public ArrayList<Vaccine> getVaccineInventory() {
        return vaccineInventory;
    }

    public ArrayList<Supply> getSupplyInventory() {
        return supplyInventory;
    }

    public Hero(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg);
        this.maxActions = maxActions;
        this.actionsAvailable = maxActions;
        this.supplyInventory = new ArrayList<Supply>();
        this.vaccineInventory = new ArrayList<Vaccine>();
    }

    public void move(Direction d) {
        // kaza direction
        // ne3raf rohna le anho cell
        // trap? supply? character?
        // all adjacent cells isVisible true
        // bena2as action
        // trap? yakhod el dmg wl trap te2leb character cell b null
        // supply? pickupSupply w te2leb character cell b null
        // CharacterCell ? law fadya yerohlaha w law msh fadya 8aleban msh hayrooh
    }

    public void useSpecial() {
        // Explorer: Allows the player to be able to see the entirety of the map for 1
        // turn whenever a supply is used.
        // • Medic: Can heal and restore health to other heroes or themselves, each
        // process of healing uses 1 supply.
        // • Fighter: Can attack as many times in a turn without costing action points,
        // for 1 turn whenever a supply is used.
        // when supply is used
    }

    public void cure() {
        // uses a vaccine
        // checks adjacent cells for zombie
        // remove el zombie from game w add a hero makano
    }
}
