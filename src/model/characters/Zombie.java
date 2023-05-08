package model.characters;

import java.awt.Point;

import engine.Game;
import model.world.CharacterCell;

public class Zombie extends Character {
    private static int ZOMBIES_COUNT = 0;

    public Zombie() {
        super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
    }

    public void onCharacterDeath() {
        Game.zombies.remove(this);
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
        Game.zombies.add(z);
        z.setLocation(new Point(x, y));
        CharacterCell c = (CharacterCell) Game.map[x][y];
        c.setCharacter(z);
    }

}