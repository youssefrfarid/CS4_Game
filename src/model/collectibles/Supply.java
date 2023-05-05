package model.collectibles;

import model.characters.Hero;

public class Supply implements Collectible {
    public Supply() {
    }

    public void pickUp(Hero h) {
        h.getSupplyInventory().add(this);
    }

    public void use(Hero h) {
        h.getSupplyInventory().remove(this);
    }
}
