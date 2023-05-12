package model.collectibles;

import java.awt.Point;

import engine.Game;
import model.characters.Hero;
import model.world.CharacterCell;

public class Vaccine implements Collectible {
    public Vaccine() {
    }

    public void pickUp(Hero h) {
        h.getVaccineInventory().add(this);
    }

    public void use(Hero h) {
        Game.zombies.remove(h.getTarget());
        Point p = h.getTarget().getLocation();
        int randomIndex = Game.generateRandomNumber(Game.availableHeroes.size());
        Hero a = Game.availableHeroes.remove(randomIndex);
        Game.heroes.add(a);
        a.setLocation(p);
        Game.map[p.x][p.y] = new CharacterCell(a);
        Game.map[p.x][p.y].setVisible(true);
        h.getVaccineInventory().remove(this);
    }
}
