package model.collectibles;

import engine.Game;
import model.characters.Hero;

public class Vaccine implements Collectible {
    public Vaccine() {
    }

    public void pickUp(Hero h) {
        h.getVaccineInventory().add(this);
    }

    public void use(Hero h) {
        Game.zombies.remove(h.getTarget());
        h.getVaccineInventory().remove(this);
    }
}
